package com.lubarov.daniel.mixologist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ViewSearchResultsFragment extends Fragment {
    private final String query;

    public ViewSearchResultsFragment(String query) {
        this.query = query;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_results_fragment, container, false);

        TextView messageView = (TextView) view.findViewById(R.id.message);
        ListView resultsView = (ListView) view.findViewById(R.id.results);

        // TODO: Display query in a TextView

        final List<Recipe> matchingRecipes = getMatchingRecipes();
        if (matchingRecipes.isEmpty()) {
            messageView.setText("No recipes found.");
        } else {
            messageView.setText(String.format("Results for \"%s\":", query));
            resultsView.setAdapter(new ArrayAdapter<Recipe>(getActivity(),
                    android.R.layout.simple_list_item_1, matchingRecipes));
            resultsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ((ContainerFragment) getParentFragment()).replaceFragment(
                            new ViewRecipeFragment(matchingRecipes.get(position)), true);
                }
            });
        }

        return view;
    }

    private List<Recipe> getMatchingRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        for (Recipe recipe : RecipeData.ALL_RECIPES)
            if (recipe.getName().toLowerCase().contains(query.toLowerCase()))
                recipes.add(recipe);
        return recipes;
    }
}
