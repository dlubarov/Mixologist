package com.lubarov.daniel.mixologist.events;

import com.lubarov.daniel.mixologist.model.Recipe;

/**
 * A recipe was added to or removed from the favorites.
 */
public class FavoriteEvent {
    public static final EventManager<FavoriteEvent> MANAGER = new EventManager<>();

    private final Recipe recipe;
    private final boolean favorite;

    public FavoriteEvent(Recipe recipe, boolean favorite) {
        this.recipe = recipe;
        this.favorite = favorite;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public boolean isFavorite() {
        return favorite;
    }
}
