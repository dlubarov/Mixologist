package com.lubarov.daniel.mixologist.reviewnagger;

import android.content.Context;
import android.content.SharedPreferences;

import com.lubarov.daniel.mixologist.storage.CountersStorage;

import java.util.concurrent.TimeUnit;

/**
 * Decides whether to nag the user to rate the app.
 */
public class NagDecider {
    private NagDecider() {}

    public static boolean shouldNag(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("mixologist", 0);
        if (preferences.getBoolean("dont_nag_for_review", false))
            return false;

        long timeLastDelay = preferences.getLong("time_last_review_delay_ms", 0L);
        if (timeLastDelay != 0)
            return shouldNagAgain(System.currentTimeMillis() - timeLastDelay);
        else
            return shouldNagForFirstTime(context);
    }

    private static boolean shouldNagForFirstTime(Context context) {
        int recipesViewed = CountersStorage.getCount(context, "recipes_viewed");
        return recipesViewed >= 20;
    }

    private static boolean shouldNagAgain(long timeSinceLastDelayMs) {
        long days = TimeUnit.MILLISECONDS.toDays(timeSinceLastDelayMs);
        return days >= 7;
    }
}
