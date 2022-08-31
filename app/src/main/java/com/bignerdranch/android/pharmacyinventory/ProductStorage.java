package com.bignerdranch.android.pharmacyinventory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bignerdranch.android.pharmacyinventory.database.ProductBaseHelper;
import com.bignerdranch.android.pharmacyinventory.database.ProductCursorWrapper;
import com.bignerdranch.android.pharmacyinventory.database.ProductDbSchema;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductStorage {
    private static ProductStorage sProductStorage;

    //private List<Product> mProducts;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static ProductStorage get(Context context) {
        if (sProductStorage == null) {
            sProductStorage = new ProductStorage(context);
        }
        return sProductStorage;
    }

    private ProductStorage(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new ProductBaseHelper(mContext).getWritableDatabase();
        //mProducts = new ArrayList<>();
        //for (int i = 0; i < 100; i++) {
           // Product product = new Product();
           // product.setTitle("Product #" + i);
           // product.setRestocked(i % 2 == 0);
           // mProducts.add(product);
        //}
    }

    public void addProduct(Product p) {
        //mProducts.add(p);
        ContentValues values = getContentValues(p);

        mDatabase.insert(ProductDbSchema.ProductTable.GROUP, null, values);
    }

    public List<Product> getProducts() {
        //return mProducts;
        //return new ArrayList<>();
        List<Product> products = new ArrayList<>();

        ProductCursorWrapper cursor = queryProducts(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                products.add(cursor.getProduct());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return products;
    }

    public Product getProduct(UUID id) {
        ///for (Product product : mProducts) {
            //if (product.getId().equals(id)) {
                 //return product;
            //}
       //}

        //return null;
        ProductCursorWrapper cursor = queryProducts(
                ProductDbSchema.ProductTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getProduct();
        } finally {
            cursor.close();
        }
    }

    public File getPhotoFile(Product product) {
        File filesDir = mContext.getFilesDir();
        return new File(filesDir, product.getPhotoFilename());
    }

    public void updateProduct(Product product) {
        String uuidString = product.getId().toString();
        ContentValues values = getContentValues(product);

        mDatabase.update(ProductDbSchema.ProductTable.GROUP, values,
                ProductDbSchema.ProductTable.Cols.UUID + " = ?",
                 new String[] { uuidString });
    }

    //private Cursor queryProducts(String whereClause, String[] whereArgs) {
    private ProductCursorWrapper queryProducts(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                ProductDbSchema.ProductTable.GROUP,
                null, // colums - null selects all columns
                whereClause,
                whereArgs,
                null, //groupBy
                null, //having
                null //orderBy
        );

        //return cursor;
        return new ProductCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Product product) {
        ContentValues values = new ContentValues();
        values.put(ProductDbSchema.ProductTable.Cols.UUID, product.getId().toString());
        values.put(ProductDbSchema.ProductTable.Cols.NAME, product.getProduct());
        values.put(ProductDbSchema.ProductTable.Cols.DATE, product.getDate().getTime());
        values.put(ProductDbSchema.ProductTable.Cols.RESTOCKED, product.isRestocked() ? 1 : 0);
        values.put(ProductDbSchema.ProductTable.Cols.SUPPLIER, product.getSupplier());

        return values;

    }
}
