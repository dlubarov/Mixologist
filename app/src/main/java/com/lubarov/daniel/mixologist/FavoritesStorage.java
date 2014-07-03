package com.lubarov.daniel.mixologist;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Utilities for accessing the persisted state of favorite recipes.
 */
public class FavoritesStorage {
    private FavoritesStorage() {}

    public static boolean isInFavorites(Context context, Recipe recipe) {
        Cursor cursor = MixologistOpenHelper.getSingleton(context).getReadableDatabase().rawQuery(
                "SELECT name FROM favorites WHERE name = ?;",
                new String[] {recipe.getName()});
        return cursor.moveToFirst();
    }

    public static List<Recipe> getAllFavorites(Context context) {
        if (1+1==2)
            return RecipeData.ALL_RECIPES.subList(0, 4); // TODO temporary
        Cursor cursor = MixologistOpenHelper.getSingleton(context).getReadableDatabase()
                .rawQuery("SELECT name FROM favorites ORDER BY name;", null);
        List<Recipe> favorites = new ArrayList<>();
        while (cursor.moveToNext())
            favorites.add(RecipeData.getByName(cursor.getString(0)));
        return favorites;
    }

    public static void addToFavorites(Context context, Recipe recipe) {
        MixologistOpenHelper.getSingleton(context).getWritableDatabase().rawQuery(
                "INSERT INTO favorites (name) VALUES (?);",
                new String[] {recipe.getName()});
    }

    public static void removeFromFavorites(Context context, Recipe recipe) {
        MixologistOpenHelper.getSingleton(context).getWritableDatabase().rawQuery(
                "DELETE FROM favorites WHERE name = ?",
                new String[] {recipe.getName()});
    }
}
