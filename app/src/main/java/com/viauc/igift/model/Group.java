package com.viauc.igift.model;

public class Group {

    public String ownerEmail;
    public String groupName;

    public Group() {
    }

    public Group(String ownerEmail, String groupName) {
        this.ownerEmail = ownerEmail;
        this.groupName = groupName;
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
