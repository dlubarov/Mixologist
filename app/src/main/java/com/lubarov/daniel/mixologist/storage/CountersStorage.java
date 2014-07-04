package com.lubarov.daniel.mixologist.storage;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CountersStorage {
    private CountersStorage() {}

    /**
     * Create a counter if it doesn't exist, then increment it.
     */
    public static void incrementCounter(Context context, String name) {
        SQLiteDatabase database = MixologistOpenHelper.getSingleton(context).getReadableDatabase();
        database.rawQuery(
                "INSERT OR IGNORE INTO counters (name, count) VALUES (?, ?);",
                new String[] {name, "0"});
        database.rawQuery(
                "UPDATE counters SET count = count + 1 WHERE name = ?;",
                new String[] {name});
    }

    /**
     * Get the value of a counter, or 0 if it hasn't been created.
     */
    public static int getCount(Context context, String name) {
        Cursor cursor = MixologistOpenHelper.getSingleton(context).getReadableDatabase().rawQuery(
                "SELECT count FROM counters WHERE name = ?;",
                new String[] {name});
        if (!cursor.moveToFirst())
            return 0;
        return cursor.getInt(0);
    }
}
