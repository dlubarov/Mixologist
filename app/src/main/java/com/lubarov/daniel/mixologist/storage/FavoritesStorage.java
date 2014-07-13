package com.lubarov.daniel.mixologist.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.lubarov.daniel.mixologist.model.Recipe;
import com.lubarov.daniel.mixologist.model.RecipeData;

import java.util.HashSet;
import java.util.Set;

/**
 * Utilities for accessing the persisted state of favorite recipes.
 */
public class FavoritesStorage {
    private static Set<Recipe> cache;

    private FavoritesStorage() {}

    public static synchronized boolean isInFavorites(Context context, Recipe recipe) {
        init(context);
        return cache.contains(recipe);
    }

    public static synchronized Set<Recipe> getAllFavorites(Context context) {
        init(context);
        return cache;
    }

    public static synchronized void addToFavorites(Context context, Recipe recipe) {
        init(context);
        cache.add(recipe);
        SQLiteDatabase database = MixologistOpenHelper.getSingleton(context).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", recipe.getName());
        database.insert("favorites", null, values);
    }

    public static synchronized void removeFromFavorites(Context context, Recipe recipe) {
        init(context);
        cache.remove(recipe);
        SQLiteDatabase database = MixologistOpenHelper.getSingleton(context).getWritableDatabase();
        database.delete("favorites", "name = ?", new String[] {recipe.getName()});
    }

    private static void init(Context context) {
        if (cache == null) {
            cache = new HashSet<>();
            SQLiteDatabase database = MixologistOpenHelper.getSingleton(context).getReadableDatabase();
            Cursor cursor = database.rawQuery("SELECT name FROM favorites ORDER BY name;", null);
            while (cursor.moveToNext())
                cache.add(RecipeData.getByName(cursor.getString(0)));
            cursor.close();
        }
    }
}
