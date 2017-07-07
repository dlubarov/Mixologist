package com.lubarov.daniel.mixologist.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.lubarov.daniel.mixologist.R;
import com.lubarov.daniel.mixologist.RecipeSearcher;
import com.lubarov.daniel.mixologist.ThumbnailCache;
import com.lubarov.daniel.mixologist.activity.ContainerFragment;
import com.lubarov.daniel.mixologist.activity.ViewRecipeActivity;
import com.lubarov.daniel.mixologist.events.EventListener;
import com.lubarov.daniel.mixologist.events.GarnishOptionalEvent;
import com.lubarov.daniel.mixologist.events.IngredientEvent;
import com.lubarov.daniel.mixologist.model.Ingredient;
import com.lubarov.daniel.mixologist.model.Recipe;
import com.lubarov.daniel.mixologist.model.RecipeComparator;
import com.lubarov.daniel.mixologist.model.RecipeData;
import com.lubarov.daniel.mixologist.storage.IngredientStorage;

import java.util.*;

/**
 * Browse all recipes, ordered starting with the ones that can be made with available ingredients.
 */
public class AvailableRecipesFragment extends Fragment {
    private TextView introView;
    private Adapter adapter;
    private final IngredientEventListener ingredientEventListener;
    private final GarnishOptionalEventListener garnishOptionalEventListener;

    public AvailableRecipesFragment() {
        this.ingredientEventListener = new IngredientEventListener();
        this.garnishOptionalEventListener = new GarnishOptionalEventListener();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.available_recipes_fragment, container, false);

        introView = (TextView) view.findViewById(R.id.intro);
        updateIntro();

        ListView recipesView = (ListView) view.findViewById(R.id.recipes);
        recipesView.setAdapter(adapter = new Adapter());
        recipesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), ViewRecipeActivity.class);
                intent.putExtra("recipe", adapter.getItem(position));
                startActivity(intent);
            }
        });

        IngredientEvent.MANAGER.addListener(ingredientEventListener);
        GarnishOptionalEvent.MANAGER.addListener(garnishOptionalEventListener);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        IngredientEvent.MANAGER.removeListener(ingredientEventListener);
        GarnishOptionalEvent.MANAGER.removeListener(garnishOptionalEventListener);
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

    private class Adapter extends ArrayAdapter<Recipe> {
        final Map<Recipe, View> recipeViews;

        Adapter() {
            super(getActivity(), R.layout.list_item_2_with_icon, R.id.text1,
                    new ArrayList<>(RecipeData.ALL_RECIPES));
            recipeViews = new HashMap<>();
            sort();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = super.getView(position, convertView, parent);
            Recipe recipe = getItem(position);
            updateRecipeView(recipe, view);
            recipeViews.put(recipe, view);
            return view;
        }

        private void updateRecipeView(Recipe recipe, View view) {
            ImageView imageView = (ImageView) view.findViewById(R.id.thumbnail);
            Bitmap thumbnail = ThumbnailCache.LARGE.getThumbnail(getResources(), recipe.getImageResource());
            imageView.setImageDrawable(new BitmapDrawable(getResources(), thumbnail));
            TextView text1 = (TextView) view.findViewById(R.id.text1);
            TextView text2 = (TextView) view.findViewById(R.id.text2);

            Set<Ingredient> missingIngredients = recipe.getMissingIngredients(getActivity());

            text1.setText(recipe.getName());
            if (missingIngredients.isEmpty()) {
                text2.setText(toCommaSeparatedList(recipe.getRequiredIngredients()));
                text2.setTextColor(Color.BLACK);
            } else {
                text2.setText("missing " + toCommaSeparatedList(missingIngredients));
                text2.setTextColor(Color.RED);
            }
        }

        public void consume(IngredientEvent event) {
            for (Recipe recipe : RecipeSearcher.findByIngredient(event.getIngredient())) {
                View view = recipeViews.get(recipe);
                if (view != null)
                    updateRecipeView(recipe, view);
            }
            sort();
        }

        public void consume(GarnishOptionalEvent event) {
            for (Recipe recipe : RecipeData.ALL_RECIPES) {
                if (!recipe.getGarnishIngredients().isEmpty()) {
                    View view = recipeViews.get(recipe);
                    if (view != null)
                        updateRecipeView(recipe, view);
                }
            }
            sort();
        }

        private void sort() {
            sort(new RecipeComparator(getActivity()));
        }
    }

    private String toCommaSeparatedList(Collection<Ingredient> ingredients) {
        StringBuilder sb = new StringBuilder();
        for (Ingredient ingredient : ingredients) {
            if (sb.length() > 0)
                sb.append(", ");
            sb.append(ingredient.getShortName(getActivity()));
        }
        return sb.toString();
    }

    private class IngredientEventListener implements EventListener<IngredientEvent> {
        @Override
        public void consume(IngredientEvent event) {
            updateIntro();
            adapter.consume(event);
        }
    }

    private class GarnishOptionalEventListener implements EventListener<GarnishOptionalEvent> {
        @Override
        public void consume(GarnishOptionalEvent event) {
            adapter.consume(event);
        }
    }
}
