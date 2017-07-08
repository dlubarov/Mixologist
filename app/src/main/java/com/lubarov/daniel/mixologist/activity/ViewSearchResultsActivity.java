package com.lubarov.daniel.mixologist.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.lubarov.daniel.mixologist.R;
import com.lubarov.daniel.mixologist.model.Recipe;
import com.lubarov.daniel.mixologist.model.RecipeData;

import java.util.ArrayList;
import java.util.List;

/**
 * An activity which lists search results.
 */
public class ViewSearchResultsActivity extends AppCompatActivity {
    private String query;

    public static Intent getIntent(Context context, String query) {
        Intent intent = new Intent(context, ViewSearchResultsActivity.class);
        intent.putExtra("query", query);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        query = getIntent().getStringExtra("query");
        final List<Recipe> matchingRecipes = getMatchingRecipes();

        setContentView(R.layout.search_results_activity);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        if (matchingRecipes.isEmpty()) {
            myToolbar.setTitle(R.string.no_recipes_found);
        } else {
            myToolbar.setTitle(String.format("Search results for \"%s\":", query));
        }
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListView resultsView = (ListView) findViewById(R.id.results);

        if (!matchingRecipes.isEmpty()) {
            resultsView.setAdapter(new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, matchingRecipes));
            resultsView.setOnItemClickListener((parent, view, position, id) ->
                    startActivity(ViewRecipeActivity.getIntent(
                            ViewSearchResultsActivity.this, matchingRecipes.get(position))));
        }
    }

    private List<Recipe> getMatchingRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        for (Recipe recipe : RecipeData.ALL_RECIPES)
            if (recipe.getName().toLowerCase().contains(query.toLowerCase()))
                recipes.add(recipe);
        return recipes;
    }
}
