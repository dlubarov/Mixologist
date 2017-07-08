package com.lubarov.daniel.mixologist;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lubarov.daniel.mixologist.model.Recipe;

import java.util.List;

public class RecipeButtonAdapter extends ArrayAdapter<Recipe> {
    public RecipeButtonAdapter(Context context, List<Recipe> recipes) {
        super(context, android.R.layout.simple_list_item_1, recipes);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        View view;
        if (convertView != null) {
            view = convertView;
        } else {
            view = LayoutInflater.from(getContext()).inflate(R.layout.favorite_recipe, null);
        }

        Recipe recipe = RecipeButtonAdapter.this.getItem(position);
        Bitmap thumbnail = ThumbnailCache.LARGE.getThumbnail(view.getResources(), recipe.getImageResource());

        ImageView iconView = (ImageView) view.findViewById(R.id.icon);
        iconView.setBackground(new BitmapDrawable(view.getResources(), thumbnail));

        TextView nameView = (TextView) view.findViewById(R.id.name);
        nameView.setText(recipe.getName());
        nameView.setShadowLayer(2f, 0f, 0f, 0xFF000000); // TODO xml-ify
        return view;
    }
}
