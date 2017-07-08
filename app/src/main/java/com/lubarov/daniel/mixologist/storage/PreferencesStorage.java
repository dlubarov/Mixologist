package com.lubarov.daniel.mixologist.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.lubarov.daniel.mixologist.quantities.Unit;

/**
 * Utilities for getting and setting user preferences.
 */
public class PreferencesStorage {
    private static SharedPreferences preferences;
    private static Unit preferredUnit;
    private static boolean garnishOptional;

    public static synchronized Unit getPreferredUnit(Context context) {
        init(context);
        return preferredUnit;
    }

    public static synchronized void setPreferredUnit(Context context, Unit newPreferredUnit) {
        init(context);
        preferredUnit = newPreferredUnit;
        preferences.edit().putString("preferred_unit", newPreferredUnit.name()).commit();
    }

    public static synchronized boolean getGarnishOptional(Context context) {
        init(context);
        return garnishOptional;
    }

    public static synchronized void setGarnishOptional(Context context, boolean newGarnishOptional) {
        init(context);
        garnishOptional = newGarnishOptional;
        preferences.edit().putBoolean("garnish_optional", newGarnishOptional).commit();
    }

    private static void init(Context context) {
        if (preferences == null) {
            preferences = context.getSharedPreferences("mixologist", 0);
            preferredUnit = Unit.valueOf(preferences.getString("preferred_unit", "CL"));
            garnishOptional = preferences.getBoolean("garnish_optional", true);
        }
    }
}
