package com.viauc.igift.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class WishItem implements Parcelable {
    private String WishWebLink;
    private Bitmap wishImage;
    private String giftName;
    private double price;
    private String ranking;
    private String description;

    protected WishItem(Parcel in) {
        WishWebLink = in.readString();
        wishImage = in.readParcelable(Bitmap.class.getClassLoader());
        giftName = in.readString();
        price = in.readDouble();
        ranking = in.readString();
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

    public String getWishWebLink() {
        return WishWebLink;
    }

    public void setWishWebLink(String wishWebLink) {
        WishWebLink = wishWebLink;
    }

    public Bitmap getWishImage() {
        return wishImage;
    }

    public void setWishImage(Bitmap wishImage) {
        this.wishImage = wishImage;
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

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
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
        dest.writeString(WishWebLink);
        dest.writeParcelable(wishImage, flags);
        dest.writeString(giftName);
        dest.writeDouble(price);
        dest.writeString(ranking);
        dest.writeString(description);
    }
}
