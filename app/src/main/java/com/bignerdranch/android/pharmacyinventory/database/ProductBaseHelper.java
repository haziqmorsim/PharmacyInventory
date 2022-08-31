package com.bignerdranch.android.pharmacyinventory.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bignerdranch.android.pharmacyinventory.database.ProductDbSchema.ProductTable;

public class ProductBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "productBase.db";

    public ProductBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ProductDbSchema.ProductTable.GROUP + "(" + " _id integer primary key autoincrement, " +
                ProductTable.Cols.UUID + ", " +
                ProductTable.Cols.NAME + ", " +
                ProductTable.Cols.DATE + ", " +
                ProductTable.Cols.RESTOCKED + ", " +
                ProductTable.Cols.SUPPLIER +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
