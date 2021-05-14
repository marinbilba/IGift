package com.viauc.igift.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.DocumentId;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

public class Group implements Parcelable {

    @DocumentId
    String uID;
    private String ownerEmail;
    private String groupName;
private ArrayList<String> usersConnected;
    public Group() {
    }

    protected Group(Parcel in) {
        uID = in.readString();
        ownerEmail = in.readString();
        groupName = in.readString();
        usersConnected = in.createStringArrayList();
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uID);
        dest.writeString(ownerEmail);
        dest.writeString(groupName);
        dest.writeStringList(usersConnected);
    }
    public static final Creator<Group> CREATOR = new Creator<Group>() {
        @Override
        public Group createFromParcel(Parcel in) {
            return new Group(in);
        }

        @Override
        public Group[] newArray(int size) {
            return new Group[size];
        }
    };

    public String getuID() {
        return uID;
    }

    public ArrayList<String> getUsersConnected() {
        return usersConnected;
    }

    public void setUsersConnected(ArrayList<String> usersConnected) {
        this.usersConnected = usersConnected;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public int describeContents() {
        return 0;
    }


}
