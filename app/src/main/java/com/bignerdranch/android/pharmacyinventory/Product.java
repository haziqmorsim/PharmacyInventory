package com.bignerdranch.android.pharmacyinventory;

import java.util.Date;
import java.util.UUID;

public class Product {

    private UUID mId;
    private String mProduct;
    private String mTitle;
    private Date mDate;
    private boolean mRestocked;
    private String mSupplier;

    public Product() {
        this(UUID.randomUUID());
        //mId = UUID.randomUUID();
        //mDate = new Date();
    }

    public Product(UUID id) {
        mId = id;
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getProduct() {
        return mProduct;
    }

    public void setProduct(String product) {
        mProduct = product;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isRestocked() {
        return mRestocked;
    }

    public void setRestocked(boolean restocked) {
        mRestocked = restocked;
    }

    public String getSupplier() {
        return mSupplier;
    }

    public void setSupplier(String supplier) {
        mSupplier = supplier;
    }

    public String getPhotoFilename() {
        return "IMG_" + getId().toString() + ".jpg";
    }
}
