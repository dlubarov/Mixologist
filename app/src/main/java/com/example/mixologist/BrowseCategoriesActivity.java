package com.example.mixologist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * A recipe for browsing all categories of recipes.
 */
public class BrowseCategoriesActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.browse_categories);

        ListView categoryList = (ListView) findViewById(R.id.categories);
        categoryList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Category.values()));
        categoryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(BrowseCategoriesActivity.this, BrowseRecipesActivity.class)
                        .putExtra("category", Category.values()[position]);
                startActivity(intent);
            }
        });
    }
}