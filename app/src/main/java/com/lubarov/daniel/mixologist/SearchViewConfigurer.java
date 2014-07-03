package com.lubarov.daniel.mixologist;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.provider.BaseColumns;
import android.support.v7.widget.SearchView;

public class SearchViewConfigurer {
    public static void configure(final Activity context, final SearchView searchView) {
        SearchManager searchManager = (SearchManager) context.getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(context.getComponentName()));
        searchView.setOnSuggestionListener(new android.support.v7.widget.SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionSelect(int i) {
                return false;
            }

            @Override
            public boolean onSuggestionClick(int i) {
                Recipe recipe = RecipeData.ALL_RECIPES.get(getSuggestion(i));
                context.startActivity(new Intent(context, ViewRecipeFragment.class)
                        .putExtra("recipe", recipe)
                        .putExtra("source", "search"));
                return true;
            }

            private int getSuggestion(int position) {
                Cursor cursor = (Cursor) searchView.getSuggestionsAdapter().getItem(position);
                return cursor.getInt(cursor.getColumnIndex(BaseColumns._ID));
            }
        });
    }
}
