package com.example.mixologist;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * A recipe for a drink.
 */
public class Recipe implements Parcelable {
    private final String name;
    private final List<String> ingredients;
    private final List<Category> categories;
    private final List<String> steps;
    private final int imageResource;
    private final String imageAttribution;

    public static final Parcelable.Creator<Recipe> CREATOR = new Parcelable.Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in.readString(), in.createStringArrayList(), fromNames(in.createStringArrayList()),
                    in.createStringArrayList(), in.readInt(), in.readString());
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public Recipe(String name, List<String> ingredients, List<Category> categories, List<String> steps,
                  int imageResource, String imageAttribution) {
        this.name = name;
        this.ingredients = ingredients;
        this.categories = categories;
        this.steps = steps;
        this.imageResource = imageResource;
        this.imageAttribution = imageAttribution;
    }

    public String getName() {
        return name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public List<Category> getCategories() {
        return categories;
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
        dest.writeStringList(ingredients);
        dest.writeStringList(toNames(categories));
        dest.writeStringList(steps);
        dest.writeInt(imageResource);
        dest.writeString(imageAttribution);
    }

    private static List<String> toNames(List<Category> categories) {
        List<String> ordinals = new ArrayList<>();
        for (Category category : categories)
            ordinals.add(category.name());
        return ordinals;
    }

    private static List<Category> fromNames(List<String> names) {
        List<Category> categories = new ArrayList<>();
        for (String name : names)
            categories.add(Category.valueOf(name));
        return categories;
    }
}
