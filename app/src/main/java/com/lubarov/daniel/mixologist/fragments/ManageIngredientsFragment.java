package com.lubarov.daniel.mixologist.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lubarov.daniel.mixologist.R;
import com.lubarov.daniel.mixologist.events.IngredientEvent;
import com.lubarov.daniel.mixologist.model.Ingredient;
import com.lubarov.daniel.mixologist.storage.IngredientStorage;

import java.util.Arrays;

public class ManageIngredientsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Ingredient[] allIngredients = Ingredient.values();
        Arrays.sort(allIngredients, (a, b) ->
                a.getLongName(getActivity()).compareToIgnoreCase(b.getLongName(getActivity())));

        View view = inflater.inflate(R.layout.manage_inventory_fragment, container, false);
        final ListView ingredientsView = (ListView) view.findViewById(R.id.ingredients);
        final ArrayAdapter<Ingredient> adapter = new ArrayAdapter<Ingredient>(getActivity(),
                android.R.layout.simple_list_item_checked, allIngredients) {
            @NonNull
            @Override
            public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                text1.setText(getItem(position).getLongName(getContext()));
                return view;
            }
        };
        ingredientsView.setAdapter(adapter);

        // Check all ingredients which are already in the inventory.
        for (int i = 0; i < allIngredients.length; ++i)
            if (IngredientStorage.isIngredientInStock(getActivity(), allIngredients[i]))
                ingredientsView.setItemChecked(i, true);

        ingredientsView.setOnItemClickListener((parent, view1, position, id) -> {
            Ingredient ingredient = adapter.getItem(position);
            boolean nowAvailable = ingredientsView.isItemChecked(position);
            if (nowAvailable)
                IngredientStorage.addIngredient(getActivity(), ingredient);
            else
                IngredientStorage.removeIngredient(getActivity(), ingredient);
            IngredientEvent.MANAGER.notifyAll(new IngredientEvent(ingredient, nowAvailable));
        });

        return view;
    }
}
