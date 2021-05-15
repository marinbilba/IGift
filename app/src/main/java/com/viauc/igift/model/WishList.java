package com.viauc.igift.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class WishList implements Parcelable {
    private String listName;
    private List<WishItem> wishItemsList;

    protected WishList(Parcel in) {
        listName = in.readString();
        wishItemsList=new ArrayList<>();
        in.readTypedList(wishItemsList,WishItem.CREATOR);
    }

    public static final Creator<WishList> CREATOR = new Creator<WishList>() {
        @Override
        public WishList createFromParcel(Parcel in) {
            return new WishList(in);
        }

        @Override
        public WishList[] newArray(int size) {
            return new WishList[size];
        }
    };

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public List<WishItem> getWishItemsList() {
        return wishItemsList;
    }

    public void setWishItemsList(List<WishItem> wishItemsList) {
        this.wishItemsList = wishItemsList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(listName);
        dest.writeList(wishItemsList);
    }
}
