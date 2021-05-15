package com.viauc.igift.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class WishItem implements Parcelable {
    private String giftName;
    private double price;
    private String whereToBuy;
    private String description;

    public WishItem() {
    }

    public WishItem(String giftName, double price, String whereToBuy, String description) {
        this.giftName = giftName;
        this.price = price;
        this.whereToBuy = whereToBuy;
        this.description = description;
    }

    protected WishItem(Parcel in) {

        giftName = in.readString();
        price = in.readDouble();
        whereToBuy = in.readString();
        description = in.readString();
    }

    public static final Creator<WishItem> CREATOR = new Creator<WishItem>() {
        @Override
        public WishItem createFromParcel(Parcel in) {
            return new WishItem(in);
        }

        @Override
        public WishItem[] newArray(int size) {
            return new WishItem[size];
        }
    };

    public WishItem(String giftName, String description, String whereToBuy) {
        this.giftName = giftName;
        this.whereToBuy = whereToBuy;
        this.description = description;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getWhereToBuy() {
        return whereToBuy;
    }

    public void setWhereToBuy(String whereToBuy) {
        this.whereToBuy = whereToBuy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(giftName);
        dest.writeDouble(price);
        dest.writeString(whereToBuy);
        dest.writeString(description);
    }
}
