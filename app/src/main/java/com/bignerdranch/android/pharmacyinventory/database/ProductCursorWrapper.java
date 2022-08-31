package com.bignerdranch.android.pharmacyinventory.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.bignerdranch.android.pharmacyinventory.Product;

import java.util.Date;
import java.util.UUID;

public class ProductCursorWrapper extends CursorWrapper {
    public ProductCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Product getProduct() {
        String uuidString = getString(getColumnIndex(ProductDbSchema.ProductTable.Cols.UUID));
        String name = getString(getColumnIndex(ProductDbSchema.ProductTable.Cols.NAME));
        long date = getLong(getColumnIndex(ProductDbSchema.ProductTable.Cols.DATE));
        int isRestocked = getInt(getColumnIndex(ProductDbSchema.ProductTable.Cols.RESTOCKED));
        String supplier = getString(getColumnIndex(ProductDbSchema.ProductTable.Cols.SUPPLIER));

        Product product = new Product(UUID.fromString(uuidString));
        product.setProduct(name);
        product.setDate(new Date(date));
        product.setRestocked(isRestocked != 0);
        product.setSupplier(supplier);

        return product;
        //return null;
    }
}
