package com.example.mixologist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

/**
 * An activity for browsing a collection of recipes. Takes an optional category.
 */
public class BrowseCategoryActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse_recipes);

        Category category = (Category) getIntent().getExtras().getSerializable("category");

        final List<Recipe> recipesToDisplay = new ArrayList<>();
        for (Recipe recipe : RecipeData.getAll())
            if (recipe.getCategories().contains(category))
                recipesToDisplay.add(recipe);

        GridView recipeGrid = (GridView) findViewById(R.id.recipes);
        recipeGrid.setNumColumns(GridSizer.getDesiredNumCols(getWindowManager()));
        recipeGrid.setAdapter(new RecipeButtonAdapter(this, recipesToDisplay));
        recipeGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(BrowseCategoryActivity.this, ViewRecipeActivity.class)
                        .putExtra("recipe", recipesToDisplay.get(position));
                startActivity(intent);
            }
        });
    }
}
