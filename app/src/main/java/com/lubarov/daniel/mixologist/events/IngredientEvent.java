package com.lubarov.daniel.mixologist.events;

import com.lubarov.daniel.mixologist.model.Ingredient;

public class IngredientEvent {
    public static final EventManager<IngredientEvent> MANAGER = new EventManager<>();

    private final Ingredient ingredient;
    private final boolean nowAvailable;

    public IngredientEvent(Ingredient ingredient, boolean nowAvailable) {
        this.ingredient = ingredient;
        this.nowAvailable = nowAvailable;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public boolean isNowAvailable() {
        return nowAvailable;
    }
}
