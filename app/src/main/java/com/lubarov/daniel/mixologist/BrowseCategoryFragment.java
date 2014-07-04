package com.lubarov.daniel.mixologist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment for browsing a collection of recipes. Takes an optional category.
 */
public class BrowseCategoryFragment extends Fragment {
    private final Category category;

    public BrowseCategoryFragment(Category category) {
        this.category = category;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.browse_recipes_fragment, container, false);

        final List<Recipe> recipesToDisplay = new ArrayList<>();
        for (Recipe recipe : RecipeData.ALL_RECIPES)
            if (recipe.getCategories().contains(category))
                recipesToDisplay.add(recipe);

        GridView recipeGrid = (GridView) view.findViewById(R.id.recipes);
        recipeGrid.setNumColumns(GridSizer.getDesiredNumCols(getActivity().getWindowManager()));
        recipeGrid.setAdapter(new RecipeButtonAdapter(getActivity(), recipesToDisplay));
        recipeGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((ContainerFragment) getParentFragment()).replaceFragment(
                        new ViewRecipeFragment(recipesToDisplay.get(position)), true);
            }
        });

        return view;
    }
}
