package com.lubarov.daniel.mixologist.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.lubarov.daniel.mixologist.R;
import com.lubarov.daniel.mixologist.activity.ContainerFragment;
import com.lubarov.daniel.mixologist.model.Recipe;
import com.lubarov.daniel.mixologist.model.RecipeData;

import java.util.ArrayList;
import java.util.List;

public class ViewSearchResultsFragment extends Fragment {
    private String query;

    public static ViewSearchResultsFragment create(String query) {
        Bundle args = new Bundle();
        args.putString("query", query);
        ViewSearchResultsFragment viewSearchResultsFragment = new ViewSearchResultsFragment();
        viewSearchResultsFragment.setArguments(args);
        return viewSearchResultsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        query = getArguments().getString("query");
        View view = inflater.inflate(R.layout.search_results_fragment, container, false);

        TextView messageView = (TextView) view.findViewById(R.id.message);
        ListView resultsView = (ListView) view.findViewById(R.id.results);

        // TODO: Display query in a TextView, or in title?

        final List<Recipe> matchingRecipes = getMatchingRecipes();
        if (matchingRecipes.isEmpty()) {
            messageView.setText("No recipes found.");
        } else {
            messageView.setText(String.format("Results for \"%s\":", query));
            resultsView.setAdapter(new ArrayAdapter<>(getActivity(),
                    android.R.layout.simple_list_item_1, matchingRecipes));
            resultsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ((ContainerFragment) getParentFragment()).pushFragment(
                            ViewRecipeFragment.create(matchingRecipes.get(position)));
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
