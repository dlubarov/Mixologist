package com.lubarov.daniel.mixologist.model;

import android.content.Context;

import java.util.Comparator;

/**
 * Sorts recipes starting with ones that can be made with the currently available ingredients.
 */
public class RecipeComparator implements Comparator<Recipe> {
    private final Context context;

    public RecipeComparator(Context context) {
        this.context = context;
    }

    @Override
    public int compare(Recipe a, Recipe b) {
        int aMissing = a.getMissingIngredients(context).size();
        int bMissing = b.getMissingIngredients(context).size();
        if (aMissing != bMissing)
            return aMissing - bMissing;
        else
            // As a tie-breaker, sort alphabetically.
            return a.getName().compareToIgnoreCase(b.getName());
    }
}
