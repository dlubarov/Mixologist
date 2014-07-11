package com.lubarov.daniel.mixologist.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import com.lubarov.daniel.mixologist.GridSizer;
import com.lubarov.daniel.mixologist.R;
import com.lubarov.daniel.mixologist.RecipeButtonAdapter;
import com.lubarov.daniel.mixologist.model.Ingredient;
import com.lubarov.daniel.mixologist.model.Recipe;
import com.lubarov.daniel.mixologist.model.RecipeData;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment for browsing a collection of recipes. Takes an optional category.
 */
public class BrowseCategoryFragment extends Fragment {
    private final Ingredient ingredient;

    public BrowseCategoryFragment(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.browse_recipes_fragment, container, false);

        final List<Recipe> recipesToDisplay = new ArrayList<>();
        for (Recipe recipe : RecipeData.ALL_RECIPES)
            if (recipe.getIngredients().contains(ingredient))
                recipesToDisplay.add(recipe);

        GridView recipeGrid = (GridView) view.findViewById(R.id.recipes);
        recipeGrid.setNumColumns(GridSizer.getDesiredNumCols(getActivity().getWindowManager()));
        recipeGrid.setAdapter(new RecipeButtonAdapter(getActivity(), recipesToDisplay));
        recipeGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((ContainerFragment) getParentFragment()).pushFragment(
                        new ViewRecipeFragment(recipesToDisplay.get(position)));
            }
        });

        return view;
    }
}
