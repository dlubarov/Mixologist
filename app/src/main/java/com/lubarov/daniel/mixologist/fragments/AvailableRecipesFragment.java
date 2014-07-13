package com.lubarov.daniel.mixologist.fragments;

import android.graphics.Color;
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
import com.lubarov.daniel.mixologist.RecipeSearcher;
import com.lubarov.daniel.mixologist.events.EventListener;
import com.lubarov.daniel.mixologist.events.IngredientEvent;
import com.lubarov.daniel.mixologist.model.Ingredient;
import com.lubarov.daniel.mixologist.model.Recipe;
import com.lubarov.daniel.mixologist.model.RecipeComparator;
import com.lubarov.daniel.mixologist.model.RecipeData;
import com.lubarov.daniel.mixologist.storage.IngredientStorage;

import java.util.*;

public class AvailableRecipesFragment extends Fragment implements EventListener<IngredientEvent> {
    private TextView introView;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.available_recipes_fragment, container, false);

        introView = (TextView) view.findViewById(R.id.intro);
        updateIntro();

        ListView recipesView = (ListView) view.findViewById(R.id.recipes);
        final Adapter adapter = new Adapter();
        adapter.sort();

        recipesView.setAdapter(adapter);
        recipesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((ContainerFragment) getParentFragment()).pushFragment(
                        new ViewRecipeFragment(adapter.getItem(position)));
            }
        });

        IngredientEvent.MANAGER.addListener(this);
        return view;
    }

    @Override
    public void consume(IngredientEvent event) {
        updateIntro();
    }

    private void updateIntro() {
        if (IngredientStorage.getAllIngredientsInStock(getActivity()).isEmpty()) {
            introView.setText("You haven't added any ingredients yet. Please use the \"Inventory\" tab to indicate" +
                    " which ingredients you have available.");
            introView.setVisibility(View.VISIBLE);
        }
        else
            introView.setVisibility(View.GONE);
    }

    private class Adapter extends ArrayAdapter<Recipe> implements EventListener<IngredientEvent> {
        final Map<Recipe, View> recipeViews;

        Adapter() {
            super(getActivity(), android.R.layout.simple_list_item_2, android.R.id.text1,
                    new ArrayList<>(RecipeData.ALL_RECIPES));
            recipeViews = new HashMap<>();
            IngredientEvent.MANAGER.addListener(this);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Recipe recipe = getItem(position);
            View view = super.getView(position, convertView, parent);
            updateRecipeView(recipe, view);
            recipeViews.put(recipe, view);
            return view;
        }

        private void updateRecipeView(Recipe recipe, View view) {
            TextView text1 = (TextView) view.findViewById(android.R.id.text1);
            TextView text2 = (TextView) view.findViewById(android.R.id.text2);

            Set<Ingredient> missingIngredients = recipe.getMissingIngredients(
                    IngredientStorage.getAllIngredientsInStock(getActivity()));

            text1.setText(recipe.getName());
            if (missingIngredients.isEmpty()) {
                text2.setText(toCommaSeparatedList(recipe.getIngredients()));
                text2.setTextColor(Color.BLACK);
            } else {
                text2.setText("missing " + toCommaSeparatedList(missingIngredients));
                text2.setTextColor(Color.RED);
            }
        }

        @Override
        public void consume(IngredientEvent event) {
            for (Recipe recipe : RecipeSearcher.findByIngredient(event.getIngredient())) {
                View view = recipeViews.get(recipe);
                if (view != null)
                    updateRecipeView(recipe, view);
            }
            sort();
        }

        private void sort() {
            sort(new RecipeComparator(IngredientStorage.getAllIngredientsInStock(getActivity())));
        }
    }

    private static String toCommaSeparatedList(Collection<Ingredient> ingredients) {
        StringBuilder sb = new StringBuilder();
        for (Ingredient ingredient : ingredients) {
            if (sb.length() > 0)
                sb.append(", ");
            sb.append(ingredient.getShortName());
        }
        return sb.toString();
    }
}
