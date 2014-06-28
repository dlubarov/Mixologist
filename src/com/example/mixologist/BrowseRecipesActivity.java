package com.example.mixologist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

/**
 * An activity for browsing a collection of recipes. Takes an optional category.
 */
public class BrowseRecipesActivity extends Activity {
    private static final int DESIRED_RECIPES_IN_SCREEN = 10;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse_recipes);

        Category category = null;
        boolean favoritesOnly = false;
        if (getIntent().getExtras() != null) {
            Bundle extras = getIntent().getExtras();
            category = (Category) extras.getSerializable("category");
            favoritesOnly = extras.getBoolean("favoritesOnly");
        }

        final List<Recipe> recipes = new ArrayList<>();
        for (Recipe recipe : RecipeData.getAll()) {
            boolean include = true;
            if (category != null)
                include &= recipe.getCategories().contains(category);
            if (favoritesOnly)
                ; // TODO
            if (include)
                recipes.add(recipe);
        }

        GridView recipeGrid = (GridView) findViewById(R.id.recipes);
        recipeGrid.setNumColumns(getDesiredNumCols());
        recipeGrid.setAdapter(new RecipeButtonAdapter(this, recipes));
        recipeGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(BrowseRecipesActivity.this, ViewRecipeActivity.class)
                        .putExtra("recipe", recipes.get(position));
                startActivity(intent);
            }
        });
    }

    private int getDesiredNumCols() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        double displayWidthInches = dm.widthPixels / dm.xdpi;
        double displayHeightInches = dm.heightPixels / dm.xdpi;
        double displayAreaSquareInches = displayWidthInches * displayHeightInches;

        double desiredRecipeArea = displayAreaSquareInches / DESIRED_RECIPES_IN_SCREEN;
        double desiredRecipeWidthInches = Math.sqrt(desiredRecipeArea);

        return (int) Math.round(getScreenSizeInches() / desiredRecipeWidthInches);
    }

    private double getScreenSizeInches() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels / dm.xdpi;
    }
}
