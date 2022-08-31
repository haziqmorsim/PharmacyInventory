package com.bignerdranch.android.pharmacyinventory.database;

public class ProductDbSchema {
    public static final class ProductTable {
        public static final String GROUP = "products";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String NAME = "name";
            public static final String DATE = "date";
            public static final String RESTOCKED = "restocked";
            public static final String SUPPLIER = "supplier";
        }
    }
}
