package com.viauc.igift.data;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.viauc.igift.model.Group;
import com.viauc.igift.util.TAG;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class UserGroupsRepository {
    private static UserGroupsRepository instance;

    private final Application application;
    private final FirebaseFirestore firebaseFirestore;
    private final FirebaseAuth mAuth;
    private ArrayList<Group> userCreatedGroups;
//    private OnCreatedGroupTaskComplete onCreatedGroupTaskComplete;


    private UserGroupsRepository(Application application) {


        this.application = application;
        userCreatedGroups = new ArrayList<>();
        // Initialize firebase  instance
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

    }


    public static UserGroupsRepository getInstance(Application app) {
        if (instance == null)
            instance = new UserGroupsRepository(app);
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

    public void getUserCreatedGroupsLiveData(CreateGroupCallback fetchedUserCreatedGroupsCallback) {
        getUserCreatedGroups(fetchedUserCreatedGroupsCallback);
    }
    public void getUserCreatedGroupsLiveData(CreateGroupCallback fetchedUserCreatedGroupsCallback, String userEmail) {
        getUserCreatedGroups(fetchedUserCreatedGroupsCallback,userEmail);
    }



    private void getUserCreatedGroups(CreateGroupCallback fetchedUserCreatedGroupsCallback) {
        firebaseFirestore.collection("groups")
                .whereEqualTo("ownerEmail", mAuth.getCurrentUser().getEmail())
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                userCreatedGroups = new ArrayList<>();
                if (task.isSuccessful()) {
                    for (DocumentSnapshot documentSnapshot : task.getResult()) {
                        Log.d(TAG.FIREBASE_STORAGE.toString(), documentSnapshot.getId() + " => " + documentSnapshot.getData());
                        Group group = documentSnapshot.toObject(Group.class);
                        userCreatedGroups.add(group);
                        fetchedUserCreatedGroupsCallback.createdGroupsOnCallbackSuccess(userCreatedGroups);
                    }
                    fetchedUserCreatedGroupsCallback.createdGroupsOnCallbackNoResults();
                } else {
                    Log.d(TAG.FIREBASE_STORAGE.toString(), "Error getting documents: ", task.getException());

                }

            }
        });

    }

    private void getUserCreatedGroups(CreateGroupCallback createGroupCallback, String userEmail) {
        firebaseFirestore.collection("groups")
                .whereEqualTo("ownerEmail", userEmail)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    userCreatedGroups = new ArrayList<>();
                    for (DocumentSnapshot documentSnapshot : task.getResult()) {
                        Log.d(TAG.FIREBASE_STORAGE.toString(), documentSnapshot.getId() + " => " + documentSnapshot.getData());
                        Group group = documentSnapshot.toObject(Group.class);
                        userCreatedGroups.add(group);
                        createGroupCallback.createdGroupsOnCallbackSuccess(userCreatedGroups);
                    }
                    createGroupCallback.createdGroupsOnCallbackNoResults();
                } else {
                    Log.d(TAG.FIREBASE_STORAGE.toString(), "Error getting documents: ", task.getException());

                }

            }
        });
    }

}
