package com.lubarov.daniel.mixologist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * An activity for viewing a particular recipe.
 */
public class ViewRecipeFragment extends Fragment {
    /**
     * The max height of the photo, as a fraction of the display's height.
     */
    private static final double MAX_PHOTO_HEIGHT = 0.50;

    private final Recipe recipe;

    public ViewRecipeFragment(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_recipe, container, false);

        ImageView imageView = (ImageView) view.findViewById(R.id.recipe_photo);
        imageView.setImageResource(recipe.getImageResource());
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        imageView.setMaxHeight((int) (dm.heightPixels * MAX_PHOTO_HEIGHT));

        TextView attributionView = (TextView) view.findViewById(R.id.photo_attribution);
        if (recipe.getImageAttribution() != null)
            attributionView.setText("Photo courtesy of " + recipe.getImageAttribution());
        else
            attributionView.setVisibility(View.GONE);
        TextView ingredientsView = (TextView) view.findViewById(R.id.ingredients);
        ingredientsView.setText(Html.fromHtml(getIngredientsHtml(recipe.getIngredients())));

        TextView stepsView = (TextView) view.findViewById(R.id.steps);
        stepsView.setText(Html.fromHtml(getStepsHtml(recipe.getSteps())));

        return view;
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