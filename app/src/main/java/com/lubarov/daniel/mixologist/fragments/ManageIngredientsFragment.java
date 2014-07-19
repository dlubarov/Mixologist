package com.lubarov.daniel.mixologist.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.lubarov.daniel.mixologist.R;
import com.lubarov.daniel.mixologist.events.IngredientEvent;
import com.lubarov.daniel.mixologist.model.Ingredient;
import com.lubarov.daniel.mixologist.storage.IngredientStorage;

import java.util.Arrays;
import java.util.Comparator;

public class ManageIngredientsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Ingredient[] allIngredients = Ingredient.values();
        Arrays.sort(allIngredients, new Comparator<Ingredient>() {
            @Override
            public int compare(Ingredient a, Ingredient b) {
                return a.getLongName().compareTo(b.getLongName());
            }
        });

        View view = inflater.inflate(R.layout.manage_inventory_fragment, container, false);
        final ListView ingredientsView = (ListView) view.findViewById(R.id.ingredients);
        final ArrayAdapter<Ingredient> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_checked, allIngredients);
        ingredientsView.setAdapter(adapter);

        // Check all ingredients which are already in the inventory.
        for (int i = 0; i < allIngredients.length; ++i)
            if (IngredientStorage.isIngredientInStock(getActivity(), allIngredients[i]))
                ingredientsView.setItemChecked(i, true);

        ingredientsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Ingredient ingredient = adapter.getItem(position);
                boolean nowAvailable = ingredientsView.isItemChecked(position);
                if (nowAvailable)
                    IngredientStorage.addIngredient(getActivity(), ingredient);
                else
                    IngredientStorage.removeIngredient(getActivity(), ingredient);
                IngredientEvent.MANAGER.notifyAll(new IngredientEvent(ingredient, nowAvailable));
            }
        });

        return view;
    }
}
