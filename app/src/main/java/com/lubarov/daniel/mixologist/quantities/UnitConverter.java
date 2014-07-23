package com.lubarov.daniel.mixologist.quantities;

import android.content.Context;
import android.content.SharedPreferences;
import com.lubarov.daniel.mixologist.events.EventListener;
import com.lubarov.daniel.mixologist.events.PreferredUnitChangeEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UnitConverter {
    private static final Pattern QUANTITY = Pattern.compile("([0-9]*.?[0-9]+) (cl|ml|oz) ");
    private static Unit preferredUnit = Unit.OZ; // TODO: Load from preferences.

    static {
        PreferredUnitChangeEvent.MANAGER.addListener(new EventListener<PreferredUnitChangeEvent>() {
            @Override
            public void consume(PreferredUnitChangeEvent event) {
                preferredUnit = event.getPreferredUnit();
            }
        });
    }

    public static void init(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("mixologist", 0);
        preferredUnit = Unit.valueOf(preferences.getString("preferred_unit", "CL"));
    }

    public static String convertUnits(String ingredient) {
        Matcher matcher = QUANTITY.matcher(ingredient);
        if (matcher.find()) {
            double amount = Double.parseDouble(matcher.group(1));
            Unit unit = Unit.valueOf(matcher.group(2).toUpperCase());
            Quantity quantity = Quantity.from(amount, unit);
            return matcher.replaceFirst(String.format("%.1f %s ",
                    quantity.get(preferredUnit), preferredUnit.name().toLowerCase()));
        }

        // Must be some non-specific quantity like "dash of bitters".
        return ingredient;
    }
}
