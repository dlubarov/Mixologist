package com.lubarov.daniel.mixologist.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.lubarov.daniel.mixologist.Recipe;
import com.lubarov.daniel.mixologist.RecipeData;

import java.util.ArrayList;
import java.util.List;

/**
 * Utilities for accessing the persisted state of favorite recipes.
 */
public class FavoritesStorage {
    private FavoritesStorage() {}

    public static boolean isInFavorites(Context context, Recipe recipe) {
        SQLiteDatabase database = MixologistOpenHelper.getSingleton(context).getReadableDatabase();
        Cursor cursor = database.rawQuery(
                "SELECT name FROM favorites WHERE name = ?;",
                new String[] {recipe.getName()});
        boolean exists = cursor.moveToFirst();
        cursor.close();
        return exists;
    }

    public static List<Recipe> getAllFavorites(Context context) {
        SQLiteDatabase database = MixologistOpenHelper.getSingleton(context).getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT name FROM favorites ORDER BY name;", null);
        List<Recipe> favorites = new ArrayList<>();
        while (cursor.moveToNext())
            favorites.add(RecipeData.getByName(cursor.getString(0)));
        cursor.close();
        return favorites;
    }

    public static void addToFavorites(Context context, Recipe recipe) {
        SQLiteDatabase database = MixologistOpenHelper.getSingleton(context).getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", recipe.getName());
        database.insert("favorites", null, contentValues);
    }

    public static void removeFromFavorites(Context context, Recipe recipe) {
        SQLiteDatabase database = MixologistOpenHelper.getSingleton(context).getWritableDatabase();
        database.delete("favorites", "name = ?", new String[] {recipe.getName()});
    }
}
