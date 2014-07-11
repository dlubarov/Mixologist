package com.lubarov.daniel.mixologist.fragments;

import com.lubarov.daniel.mixologist.model.Ingredient;
import com.lubarov.daniel.mixologist.model.Recipe;

import java.util.Comparator;
import java.util.Set;

/**
 * Sorts recipes starting with ones that can be made with the currently available ingredients.
 */
public class RecipeComparator implements Comparator<Recipe> {
    private final Set<Ingredient> availableIngredients;

    public RecipeComparator(Set<Ingredient> availableIngredients) {
        this.availableIngredients = availableIngredients;
    }

    @Override
    public int compare(Recipe a, Recipe b) {
        int aMissing = a.getMissingIngredients(availableIngredients).size();
        int bMissing = b.getMissingIngredients(availableIngredients).size();
        if (aMissing != bMissing)
            return aMissing - bMissing;
        else
            // As a tie-breaker, sort alphabetically.
            return a.getName().compareTo(b.getName());
    }
}
