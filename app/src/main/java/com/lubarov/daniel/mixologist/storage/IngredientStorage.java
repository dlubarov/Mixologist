package com.lubarov.daniel.mixologist.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.lubarov.daniel.mixologist.model.Ingredient;

import java.util.HashSet;
import java.util.Set;

public class IngredientStorage {
    private static Set<Ingredient> cache;

    public static synchronized boolean isIngredientInStock(Context context, Ingredient ingredient) {
        init(context);
        return cache.contains(ingredient);
    }

    public static synchronized Set<Ingredient> getAllIngredientsInStock(Context context) {
        init(context);
        return cache;
    }

    public static synchronized void addIngredient(Context context, Ingredient ingredient) {
        init(context);
        SQLiteDatabase database = MixologistOpenHelper.getSingleton(context).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", ingredient.name());
        database.insert("ingredients", null, values);
        cache.add(ingredient);
    }

    public static synchronized void removeIngredient(Context context, Ingredient ingredient) {
        init(context);
        SQLiteDatabase database = MixologistOpenHelper.getSingleton(context).getWritableDatabase();
        database.delete("ingredients", "name = ?", new String[] {ingredient.name()});
        cache.remove(ingredient);
    }

    private static void init(Context context) {
        if (cache == null) {
            cache = new HashSet<>();
            SQLiteDatabase database = MixologistOpenHelper.getSingleton(context).getReadableDatabase();
            Cursor cursor = database.rawQuery("SELECT name FROM ingredients;", new String[0]);
            while (cursor.moveToNext())
                cache.add(Ingredient.valueOf(cursor.getString(0)));
        }
    }
}
