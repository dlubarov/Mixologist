package com.lubarov.daniel.mixologist.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lubarov.daniel.mixologist.R;
import com.lubarov.daniel.mixologist.RecipePrinter;
import com.lubarov.daniel.mixologist.events.EventListener;
import com.lubarov.daniel.mixologist.events.FavoriteEvent;
import com.lubarov.daniel.mixologist.events.PreferredUnitEvent;
import com.lubarov.daniel.mixologist.model.Recipe;
import com.lubarov.daniel.mixologist.quantities.UnitConverter;
import com.lubarov.daniel.mixologist.storage.CountersStorage;
import com.lubarov.daniel.mixologist.storage.FavoritesStorage;

import java.util.List;

/**
 * An activity for viewing a particular recipe.
 */
public class ViewRecipeActivity extends AppCompatActivity {
    /**
     * The max height of the photo, as a fraction of the display's height.
     */
    private static final double MAX_PHOTO_HEIGHT = 0.50;

    private Recipe recipe;
    private MenuItem favoriteButton;
    private TextView ingredientsView;

    private final FavoriteEventListener favoriteEventListener = new FavoriteEventListener();
    private final PreferredUnitEventListener preferredUnitEventListener = new PreferredUnitEventListener();

    public static Intent getIntent(Context context, Recipe recipe) {
        Intent intent = new Intent(context, ViewRecipeActivity.class);
        intent.putExtra("recipe", recipe);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recipe = getIntent().getParcelableExtra("recipe");
        if (recipe == null) {
            throw new IllegalStateException("ViewRecipeActivity created without recipe extra");
        }

        setContentView(R.layout.view_recipe_activity);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle(recipe.getName());
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView imageView = (ImageView) findViewById(R.id.recipe_photo);
        imageView.setImageResource(recipe.getImageResource());
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        imageView.setMaxHeight((int) (dm.heightPixels * MAX_PHOTO_HEIGHT));

        TextView attributionView = (TextView) findViewById(R.id.photo_attribution);
        if (recipe.getImageAttribution() != null) {
            attributionView.setText(getString(R.string.photo_credit, recipe.getImageAttribution()));
        } else {
            attributionView.setVisibility(View.GONE);
        }
        ingredientsView = (TextView) findViewById(R.id.ingredients);
        ingredientsView.setText(Html.fromHtml(getIngredientsHtml(recipe.getIngredientDescriptions())));

        TextView stepsView = (TextView) findViewById(R.id.steps);
        stepsView.setText(Html.fromHtml(getStepsHtml(recipe.getSteps())));

        if (savedInstanceState == null) {
            CountersStorage.incrementCounter(this, "recipes_viewed");
        }

        FavoriteEvent.MANAGER.addListener(favoriteEventListener);
        PreferredUnitEvent.MANAGER.addListener(preferredUnitEventListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FavoriteEvent.MANAGER.removeListener(favoriteEventListener);
        PreferredUnitEvent.MANAGER.removeListener(preferredUnitEventListener);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.view_recipe_actions, menu);
        favoriteButton = menu.findItem(R.id.favorite);
        updateFavoriteIcon();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.favorite:
                boolean wasFavorite = FavoritesStorage.isInFavorites(this, recipe);
                if (wasFavorite)
                    FavoritesStorage.removeFromFavorites(this, recipe);
                else
                    FavoritesStorage.addToFavorites(this, recipe);
                FavoriteEvent.MANAGER.notifyAll(new FavoriteEvent(recipe, !wasFavorite));
                return true;
            case R.id.print:
                RecipePrinter.print(recipe, this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private String getIngredientsHtml(List<String> ingredients) {
        StringBuilder html = new StringBuilder();
        for (String ingredient : ingredients) {
            if (html.length() > 0)
                html.append("<br />");
            html.append("&#8226; ").append(UnitConverter.convertUnits(this, ingredient));
        }
        return html.toString();
    }

    private static String getStepsHtml(List<String> steps) {
        StringBuilder html = new StringBuilder();
        int number = 1;
        for (String ingredient : steps) {
            if (html.length() > 0)
                html.append("<br />");
            html.append(number++).append(". ").append(ingredient);
        }
        return html.toString();
    }

    private void updateFavoriteIcon() {
        updateFavoriteIcon(FavoritesStorage.isInFavorites(this, recipe));
    }

    private void updateFavoriteIcon(boolean isFavorite) {
        int resId = isFavorite
                ? R.drawable.ic_favorite_black_24dp
                : R.drawable.ic_favorite_border_black_24dp;
        favoriteButton.setIcon(resId);
    }

    private class FavoriteEventListener implements EventListener<FavoriteEvent> {
        @Override
        public void consume(FavoriteEvent event) {
            if (event.getRecipe().equals(recipe))
                updateFavoriteIcon(event.isFavorite());
        }
    }

    private class PreferredUnitEventListener implements EventListener<PreferredUnitEvent> {
        @Override
        public void consume(PreferredUnitEvent event) {
            ingredientsView.setText(Html.fromHtml(getIngredientsHtml(recipe.getIngredientDescriptions())));
        }
    }
}
