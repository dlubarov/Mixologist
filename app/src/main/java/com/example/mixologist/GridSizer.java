package com.example.mixologist;

import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Utilities for displaying recipes in a grid.
 */
public final class GridSizer {
    private static final int DESIRED_RECIPES_IN_SCREEN = 10;

    private GridSizer() {}

    public static int getDesiredNumCols(WindowManager windowManager) {
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);

        double displayWidthInches = dm.widthPixels / dm.xdpi;
        double displayHeightInches = dm.heightPixels / dm.xdpi;
        double displayAreaSquareInches = displayWidthInches * displayHeightInches;

        double desiredRecipeArea = displayAreaSquareInches / DESIRED_RECIPES_IN_SCREEN;
        double desiredRecipeWidthInches = Math.sqrt(desiredRecipeArea);

        return (int) Math.round(getScreenSizeInches(windowManager) / desiredRecipeWidthInches);
    }

    private static double getScreenSizeInches(WindowManager windowManager) {
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels / dm.xdpi;
    }
}
