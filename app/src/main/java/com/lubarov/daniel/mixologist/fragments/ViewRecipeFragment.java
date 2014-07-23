package com.lubarov.daniel.mixologist.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;
import com.lubarov.daniel.mixologist.R;
import com.lubarov.daniel.mixologist.events.EventListener;
import com.lubarov.daniel.mixologist.events.FavoriteEvent;
import com.lubarov.daniel.mixologist.model.Recipe;
import com.lubarov.daniel.mixologist.quantities.UnitConverter;
import com.lubarov.daniel.mixologist.storage.CountersStorage;
import com.lubarov.daniel.mixologist.storage.FavoritesStorage;

import java.util.List;

/**
 * An activity for viewing a particular recipe.
 */
public class ViewRecipeFragment extends Fragment implements EventListener<FavoriteEvent> {
    /**
     * The max height of the photo, as a fraction of the display's height.
     */
    private static final double MAX_PHOTO_HEIGHT = 0.50;

    private Recipe recipe;
    private MenuItem favoriteButton;

    public static ViewRecipeFragment create(Recipe recipe) {
        Bundle args = new Bundle();
        args.putParcelable("recipe", recipe);
        ViewRecipeFragment viewRecipeFragment = new ViewRecipeFragment();
        viewRecipeFragment.setArguments(args);
        return viewRecipeFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        recipe = getArguments().getParcelable("recipe");
        View view = inflater.inflate(R.layout.view_recipe_fragment, container, false);

        TextView titleView = (TextView) view.findViewById(R.id.title);
        titleView.setText(recipe.getName());

        ImageView imageView = (ImageView) view.findViewById(R.id.recipe_photo);
        imageView.setImageResource(recipe.getImageResource());
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        imageView.setMaxHeight((int) (dm.heightPixels * MAX_PHOTO_HEIGHT));

        TextView attributionView = (TextView) view.findViewById(R.id.photo_attribution);
        if (recipe.getImageAttribution() != null)
            attributionView.setText("Photo courtesy of " + recipe.getImageAttribution());
        else
            attributionView.setVisibility(View.GONE);
        TextView ingredientsView = (TextView) view.findViewById(R.id.ingredients);
        ingredientsView.setText(Html.fromHtml(getIngredientsHtml(recipe.getIngredientDescriptions())));

        TextView stepsView = (TextView) view.findViewById(R.id.steps);
        stepsView.setText(Html.fromHtml(getStepsHtml(recipe.getSteps())));

        setHasOptionsMenu(true);

        CountersStorage.incrementCounter(getActivity(), "recipes_viewed");

        FavoriteEvent.MANAGER.addListener(this);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.view_recipe_actions, menu);
        favoriteButton = menu.findItem(R.id.favorite);
        updateFavoriteIcon();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.favorite:
                boolean wasFavorite = FavoritesStorage.isInFavorites(getActivity(), recipe);
                if (wasFavorite)
                    FavoritesStorage.removeFromFavorites(getActivity(), recipe);
                else
                    FavoritesStorage.addToFavorites(getActivity(), recipe);
                FavoriteEvent.MANAGER.notifyAll(new FavoriteEvent(recipe, !wasFavorite));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void consume(FavoriteEvent event) {
        if (event.getRecipe().equals(recipe))
            updateFavoriteIcon(event.isFavorite());
    }

    private static String getIngredientsHtml(List<String> ingredients) {
        StringBuilder html = new StringBuilder();
        for (String ingredient : ingredients) {
            if (html.length() > 0)
                html.append("<br />");
            html.append("&#8226; ").append(UnitConverter.convertUnits(ingredient));
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
        updateFavoriteIcon(FavoritesStorage.isInFavorites(getActivity(), recipe));
    }

    private void updateFavoriteIcon(boolean isFavorite) {
        int resId = isFavorite
                ? R.drawable.btn_star_on_normal_holo_light
                : R.drawable.btn_star_off_normal_holo_light;
        favoriteButton.setIcon(resId);
    }
}