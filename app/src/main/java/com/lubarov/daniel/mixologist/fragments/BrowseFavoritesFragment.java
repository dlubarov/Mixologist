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
import com.lubarov.daniel.mixologist.activity.ContainerFragment;
import com.lubarov.daniel.mixologist.events.EventListener;
import com.lubarov.daniel.mixologist.events.FavoriteEvent;
import com.lubarov.daniel.mixologist.model.Recipe;
import com.lubarov.daniel.mixologist.storage.FavoritesStorage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A fragment for browsing a collection of recipes. Takes an optional category.
 */
public class BrowseFavoritesFragment extends Fragment implements EventListener<FavoriteEvent> {
    private RecipeButtonAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.browse_recipes_fragment, container, false);

        GridView recipeGrid = (GridView) view.findViewById(R.id.recipes);
        recipeGrid.setNumColumns(GridSizer.getDesiredNumCols(getActivity().getWindowManager()));

        final List<Recipe> recipesToDisplay = new ArrayList<>(FavoritesStorage.getAllFavorites(getActivity()));
        Collections.sort(recipesToDisplay, new Comparator<Recipe>() {
            @Override
            public int compare(Recipe a, Recipe b) {
                return a.getName().compareTo(b.getName());
            }
        });

        recipeGrid.setAdapter(adapter = new RecipeButtonAdapter(getActivity(), recipesToDisplay));
        recipeGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((ContainerFragment) getParentFragment()).pushFragment(
                        ViewRecipeFragment.create(recipesToDisplay.get(position)));
            }
        });

        FavoriteEvent.MANAGER.addListener(this);
        return view;
    }

    @Override
    public void consume(FavoriteEvent event) {
        if (event.isFavorite())
            adapter.add(event.getRecipe());
        else
            adapter.remove(event.getRecipe());
    }
}