package com.lubarov.daniel.mixologist.quantities;

import android.content.Context;
import com.lubarov.daniel.mixologist.storage.PreferencesStorage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UnitConverter {
    private static final Pattern QUANTITY = Pattern.compile("([0-9]*.?[0-9]+) (cl|ml|oz) ");

    public static String convertUnits(Context context, String ingredient) {
        Matcher matcher = QUANTITY.matcher(ingredient);
        if (matcher.find()) {
            double amount = Double.parseDouble(matcher.group(1));
            Unit unit = Unit.valueOf(matcher.group(2).toUpperCase());
            Quantity quantity = Quantity.from(amount, unit);
            Unit preferredUnit = PreferencesStorage.getPreferredUnit(context);
            return matcher.replaceFirst(String.format("%.1f %s ",
                    quantity.get(preferredUnit), preferredUnit.name().toLowerCase()));
        }

        // Must be some non-specific quantity like "dash of bitters".
        return ingredient;
    }
}
