package com.lubarov.daniel.mixologist;

import com.lubarov.daniel.mixologist.model.Ingredient;
import com.lubarov.daniel.mixologist.model.Recipe;
import com.lubarov.daniel.mixologist.model.RecipeData;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Utilities for efficiently searching for recipes.
 */
public class RecipeSearcher {
    private static final Map<Ingredient, Set<Recipe>> recipesByIngredient;

    static {
        recipesByIngredient = new HashMap<>();
        for (Ingredient ingredient : Ingredient.values())
            recipesByIngredient.put(ingredient, new HashSet<Recipe>());
        for (Recipe recipe : RecipeData.ALL_RECIPES) {
            for (Ingredient ingredient : recipe.getRequiredIngredients())
                recipesByIngredient.get(ingredient).add(recipe);
            for (Ingredient ingredient : recipe.getGarnishIngredients())
                recipesByIngredient.get(ingredient).add(recipe);
        }
    }

    /**
     * Find all recipes which require a particular ingredient.
     */
    public static Set<Recipe> findByIngredient(Ingredient ingredient) {
        return recipesByIngredient.get(ingredient);
    }
}
