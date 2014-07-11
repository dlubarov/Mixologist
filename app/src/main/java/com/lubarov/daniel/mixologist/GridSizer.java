package com.lubarov.daniel.mixologist;

import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Utilities for displaying recipes in a grid.
 */
public final class GridSizer {
    private static final double DESIRED_RECIPE_SIZE_INCHES = 1.2;

    private GridSizer() {}

    public static int getDesiredNumCols(WindowManager windowManager) {
        int numCols = (int) Math.round(getDisplayWidthInches(windowManager) / DESIRED_RECIPE_SIZE_INCHES);

        // Just as a sanity check, we want at least 2 columns, and no more than 4.
        numCols = Math.max(numCols, 2);
        numCols = Math.min(numCols, 4);

        return numCols;
    }

    private static double getDisplayWidthInches(WindowManager windowManager) {
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels / dm.xdpi;
    }
}
