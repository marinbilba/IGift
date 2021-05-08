package com.viauc.igift.data;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.viauc.igift.util.TAG;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UserDataRepository {
    private static UserDataRepository instance;

    private final Application application;
    private final FirebaseFirestore firebaseFirestore;
    private final FirebaseAuth mAuth;


    private UserDataRepository(Application application) {
        this.application = application;

        // Initialize firebase  instance
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

    }

    public static UserDataRepository getInstance(Application app) {
        if (instance == null)
            instance = new UserDataRepository(app);
        return instance;
    }

    public void createGroup(String groupName) {
        if (mAuth.getUid() == null) {
            return;
        }
        Map<String, Object> newGroup = new HashMap<>();
        newGroup.put("ownerEmail", Objects.requireNonNull(mAuth.getCurrentUser()).getEmail());
        newGroup.put("groupName", groupName);
        firebaseFirestore.collection("groups").add(newGroup).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(application, "Group created successfully",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
