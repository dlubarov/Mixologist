package com.lubarov.daniel.mixologist.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MixologistOpenHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static MixologistOpenHelper singleton;

    private static final String CREATE_TABLE_FAVORITES = "CREATE TABLE favorites (name TEXT PRIMARY KEY);";
    private static final String CREATE_TABLE_COUNTERS = "CREATE TABLE counters (name TEXT PRIMARY KEY, count INTEGER);";
    private static final String CREATE_TABLE_INGREDIENTS = "CREATE TABLE ingredients (name TEXT PRIMARY KEY);";

    private MixologistOpenHelper(Context context) {
        super(context, "mixologist", null, DATABASE_VERSION);
    }

    public static synchronized MixologistOpenHelper getSingleton(Context context) {
        if (singleton == null)
            singleton = new MixologistOpenHelper(context);
        return singleton;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_FAVORITES);
        db.execSQL(CREATE_TABLE_COUNTERS);
        db.execSQL(CREATE_TABLE_INGREDIENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
