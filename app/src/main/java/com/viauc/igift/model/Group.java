package com.viauc.igift.model;

import com.google.firebase.firestore.DocumentId;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

public class Group {

    @DocumentId
    String uID;
    private String ownerEmail;
    private String groupName;
private ArrayList<String> usersConnected;
    public Group() {
    }

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
}
