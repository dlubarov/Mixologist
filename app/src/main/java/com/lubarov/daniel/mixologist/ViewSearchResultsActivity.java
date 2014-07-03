package com.lubarov.daniel.mixologist;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ViewSearchResultsActivity extends ActionBarActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_search_results);

        TextView messageView = (TextView) findViewById(R.id.message);
        ListView resultsView = (ListView) findViewById(R.id.results);

        if (!Intent.ACTION_SEARCH.equals(getIntent().getAction()))
            throw new IllegalStateException("Should only arrive via SEARCH; got " + getIntent().getAction());
        String query = getIntent().getStringExtra(SearchManager.QUERY);
        setTitle(String.format("Results for \"%s\"", query));

        final List<Recipe> matchingRecipes = getMatchingRecipes(query);
        if (matchingRecipes.isEmpty()) {
            messageView.setText(String.format("No recipes found."));
            messageView.setVisibility(View.VISIBLE);
        } else {
            resultsView.setAdapter(new ArrayAdapter<Recipe>(this,
                    android.R.layout.simple_list_item_1, matchingRecipes));
            resultsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(ViewSearchResultsActivity.this, ViewRecipeFragment.class)
                            .putExtra("recipe", matchingRecipes.get(position))
                            .putExtra("source", "search");
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.view_search_results_activity_actions, menu);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchViewConfigurer.configure(this, searchView);

        return super.onCreateOptionsMenu(menu);
    }

    private static List<Recipe> getMatchingRecipes(String query) {
        query = query.toLowerCase();
        List<Recipe> recipes = new ArrayList<Recipe>();
        for (Recipe recipe : RecipeData.ALL_RECIPES)
            if (recipe.getName().toLowerCase().contains(query))
                recipes.add(recipe);
        return recipes;
    }
}
