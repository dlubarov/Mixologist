package com.example.mixologist;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * An activity for viewing a particular recipe.
 */
public class ViewRecipeActivity extends Activity {
    /**
     * The max height of the photo, as a fraction of the display's height.
     */
    private static final double MAX_PHOTO_HEIGHT = 0.50;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_recipe);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Recipe recipe = getIntent().getExtras().getParcelable("recipe");

        TextView titleView = (TextView) findViewById(R.id.title);
        titleView.setText(recipe.getName());

        ImageView imageView = (ImageView) findViewById(R.id.recipe_photo);
        imageView.setImageResource(recipe.getImageResource());
        imageView.setMaxHeight((int) (dm.heightPixels * MAX_PHOTO_HEIGHT));

        TextView attributionView = (TextView) findViewById(R.id.photo_attribution);
        if (recipe.getImageAttribution() != null) {
            attributionView.setText("Photo courtesy of " + recipe.getImageAttribution());
            attributionView.setVisibility(View.VISIBLE);
        } else {
            Log.w("ViewRecipeActivity", "No attribution for " + recipe.getName());
            attributionView.setVisibility(View.GONE);
        }
        TextView ingredientsView = (TextView) findViewById(R.id.ingredients);
        ingredientsView.setText(Html.fromHtml(getIngredientsHtml(recipe.getIngredients())));

        TextView stepsView = (TextView) findViewById(R.id.steps);
        stepsView.setText(Html.fromHtml(getStepsHtml(recipe.getSteps())));
    }

    private static String getIngredientsHtml(List<String> ingredients) {
        StringBuilder html = new StringBuilder();
        for (String ingredient : ingredients) {
            if (html.length() > 0)
                html.append("<br />");
            html.append("&#8226; ").append(ingredient);
        }
        return html.toString();
    }

    private static String getStepsHtml(List<String> steps) {
        StringBuilder html = new StringBuilder();
        int number = 1;
        for (String ingredient : steps) {
            if (html.length() > 0)
                html.append("<br />");
            html.append(number++).append(". ").append(ingredient);
        }
        return html.toString();
    }
}