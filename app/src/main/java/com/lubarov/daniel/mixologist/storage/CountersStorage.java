package com.lubarov.daniel.mixologist.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CountersStorage {
    private CountersStorage() {}

    /**
     * Create a counter if it doesn't exist, then increment it.
     */
    public static void incrementCounter(Context context, String name) {
        int previousCount = getCount(context, name);
        SQLiteDatabase database = MixologistOpenHelper.getSingleton(context).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("count", previousCount + 1);

        if (previousCount == 0) {
            values.put("name", name);
            database.insertWithOnConflict("counters", null, values, SQLiteDatabase.CONFLICT_IGNORE);
        } else
            database.update("counters", values, "name = ?", new String[] {name});
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
        int count = cursor.getInt(0);
        cursor.close();
        return count;
    }
}
