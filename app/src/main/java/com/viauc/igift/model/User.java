package com.viauc.igift.model;

import com.google.firebase.firestore.DocumentId;

public class User {
    @DocumentId
    String uID;
    private String  email;

    public User(){

    }

    public String getuID() {
        return uID;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
