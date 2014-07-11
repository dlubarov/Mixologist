package com.lubarov.daniel.mixologist;

import android.app.SearchManager;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.provider.BaseColumns;
import com.lubarov.daniel.mixologist.model.Recipe;
import com.lubarov.daniel.mixologist.model.RecipeData;

public class RecipeContentProvider extends ContentProvider {
    private static final int MAX_RESULTS = 5;

    @Override
    public boolean onCreate() {
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String query = uri.getLastPathSegment().toLowerCase();
        MatrixCursor cursor = new MatrixCursor(new String[] {
                BaseColumns._ID,
                SearchManager.SUGGEST_COLUMN_TEXT_1,
                SearchManager.SUGGEST_COLUMN_INTENT_DATA
        });

        // Add recipes which start with the query string. We want these to be displayed at the top.
        for (int i = 0; i < RecipeData.ALL_RECIPES.size() && cursor.getCount() < MAX_RESULTS; ++i) {
            Recipe recipe = RecipeData.ALL_RECIPES.get(i);
            if (recipe.getName().toLowerCase().startsWith(query))
                cursor.addRow(new Object[] {
                        i,
                        recipe.getName(),
                        Integer.toString(i)
                });
        }

        // Then, add recipes which don't start with the query string, but do contain it.
        for (int i = 0; i < RecipeData.ALL_RECIPES.size() && cursor.getCount() < MAX_RESULTS; ++i) {
            Recipe recipe = RecipeData.ALL_RECIPES.get(i);
            if (!recipe.getName().toLowerCase().startsWith(query) && recipe.getName().toLowerCase().contains(query))
                cursor.addRow(new Object[] {
                        i,
                        recipe.getName(),
                        Integer.toString(i)
                });
        }

        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return SearchManager.SUGGEST_MIME_TYPE;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException();
    }
}
