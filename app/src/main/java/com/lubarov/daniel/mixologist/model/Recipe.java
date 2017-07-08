package com.lubarov.daniel.mixologist.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.lubarov.daniel.mixologist.storage.IngredientStorage;
import com.lubarov.daniel.mixologist.storage.PreferencesStorage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A recipe for a drink.
 */
public class Recipe implements Parcelable {
    private final String name;
    private final List<String> ingredientDescriptions;
    private final List<Ingredient> requiredIngredients;
    private final List<Ingredient> garnishIngredients;
    private final List<String> steps;
    private final int imageResource;
    private final String imageAttribution;

    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in.readString(), in.createStringArrayList(), fromNames(in.createStringArrayList()),
                    fromNames(in.createStringArrayList()), in.createStringArrayList(), in.readInt(), in.readString());
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public Recipe(String name, List<String> ingredientDescriptions, List<Ingredient> requiredIngredients,
          List<Ingredient> garnishIngredients, List<String> steps, int imageResource, String imageAttribution) {
        this.name = name;
        this.ingredientDescriptions = ingredientDescriptions;
        this.requiredIngredients = requiredIngredients;
        this.garnishIngredients = garnishIngredients;
        this.steps = steps;
        this.imageResource = imageResource;
        this.imageAttribution = imageAttribution;
    }

    public String getName() {
        return name;
    }

    public List<String> getIngredientDescriptions() {
        return ingredientDescriptions;
    }

    public List<Ingredient> getRequiredIngredients() {
        return requiredIngredients;
    }

    public List<Ingredient> getGarnishIngredients() {
        return garnishIngredients;
    }

    public List<String> getSteps() {
        return steps;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getImageAttribution() {
        return imageAttribution;
    }

    public Set<Ingredient> getMissingIngredients(Context context) {
        Set<Ingredient> missingIngredients = new HashSet<>(requiredIngredients);
        if (!PreferencesStorage.getGarnishOptional(context)) {
            missingIngredients.addAll(garnishIngredients);
        }
        missingIngredients.removeAll(IngredientStorage.getAllIngredientsInStock(context));
        return missingIngredients;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Recipe && name.equals(((Recipe) o).name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeStringList(ingredientDescriptions);
        dest.writeStringList(toNames(requiredIngredients));
        dest.writeStringList(toNames(garnishIngredients));
        dest.writeStringList(steps);
        dest.writeInt(imageResource);
        dest.writeString(imageAttribution);
    }

    private static List<String> toNames(List<Ingredient> categories) {
        List<String> ordinals = new ArrayList<>();
        for (Ingredient ingredient : categories)
            ordinals.add(ingredient.name());
        return ordinals;
    }

    private static List<Ingredient> fromNames(List<String> names) {
        List<Ingredient> categories = new ArrayList<>();
        for (String name : names)
            categories.add(Ingredient.valueOf(name));
        return categories;
    }
}
