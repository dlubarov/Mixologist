package com.lubarov.daniel.mixologist;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.lubarov.daniel.mixologist.model.Recipe;

import java.util.List;

public class RecipeButtonAdapter extends ArrayAdapter<Recipe> {
    public RecipeButtonAdapter(Context context, List<Recipe> recipes) {
        super(context, android.R.layout.simple_list_item_1, recipes);
    }

    @Override
    // setBackgroundDrawable is used because setBackground requires API level 16.
    @SuppressWarnings("deprecation")
    public View getView(final int position, View convertView, ViewGroup parent) {
        return new TextView(getContext()) {
            {
                Recipe recipe = RecipeButtonAdapter.this.getItem(position);
                setText(recipe.getName());
                setGravity(Gravity.BOTTOM | Gravity.RIGHT);
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
                Bitmap thumbnail = ThumbnailCache.LARGE.getThumbnail(getResources(), recipe.getImageResource());
                setBackgroundDrawable(new BitmapDrawable(getResources(), thumbnail));
                setTextColor(0xFFFFFFFF);
                setShadowLayer(2f, 0f, 0f, 0xFF000000);
            }

            @Override
            @SuppressWarnings("all")
            public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
                // Force a square shape.
                super.onMeasure(widthMeasureSpec, widthMeasureSpec);
            }
        };
    }
}
