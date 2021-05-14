package com.viauc.igift.data;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.viauc.igift.data.callbacks.UserCreatedGroupsCallback;
import com.viauc.igift.data.callbacks.UserJoinedGroupsCallback;
import com.viauc.igift.model.Group;
import com.viauc.igift.model.User;
import com.viauc.igift.util.TAG;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

public class UserGroupsRepository {
    private static UserGroupsRepository instance;

    private final Application application;
    private final FirebaseFirestore firebaseFirestore;
    private final FirebaseAuth mAuth;
    private ArrayList<Group> userCreatedGroups;
    private String currentUserEmail;
//    private OnCreatedGroupTaskComplete onCreatedGroupTaskComplete;


    private UserGroupsRepository(Application application) {


        this.application = application;
        userCreatedGroups = new ArrayList<>();
        // Initialize firebase  instance
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        currentUserEmail = Objects.requireNonNull(mAuth.getCurrentUser()).getEmail();

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
        newGroup.put("ownerEmail", currentUserEmail);
        newGroup.put("groupName", groupName);
        firebaseFirestore.collection("groups").add(newGroup).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(application, "Group created successfully",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void getUserCreatedGroups(UserCreatedGroupsCallback fetchedUserCreatedGroupsCallback) {
        firebaseFirestore.collection("groups")
                .whereEqualTo("ownerEmail", currentUserEmail)
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

    public void getUserCreatedGroups(UserCreatedGroupsCallback userCreatedGroupsCallback, String userEmail) {
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
                        if (group != null) {
                            // Get users connected to this group
                            try {
                                ArrayList<String> connectedUsers = (ArrayList<String>) documentSnapshot.get("connectedUsers");
                                group.setUsersConnected(connectedUsers);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            userCreatedGroups.add(group);
                            userCreatedGroupsCallback.createdGroupsOnCallbackSuccess(userCreatedGroups);
                        }


                    }
                    userCreatedGroupsCallback.createdGroupsOnCallbackNoResults();
                } else {
                    Log.d(TAG.FIREBASE_STORAGE.toString(), "Error getting documents: ", task.getException());

                }

            }
        });
    }
//todo the part of checking duplicated can be optimized by checking if you are alredy a member of the group in another method
    public void joinGroup(Group group) {
//        Map<String, Object> joinGroupUser = new HashMap<>();
//        joinGroupUser.put("connectedUserEmail", currentUserEmail);
        ArrayList<String> joinGroupUser;
        if (group.getUsersConnected() != null) {
            joinGroupUser = group.getUsersConnected();
        } else {
            joinGroupUser = new ArrayList<>();
        }
        joinGroupUser.add(currentUserEmail);
        joinGroupUser.add("pidar");

        group.setUsersConnected(joinGroupUser);
        // Convert to hash set to avoid duplicates
        HashSet<String> stringHashSet=new HashSet<>(joinGroupUser);
        ArrayList<String> joinGroupUserToStore = new ArrayList<>(stringHashSet);

        firebaseFirestore.collection("groups").document(group.getuID()).update("connectedUsers", joinGroupUserToStore).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<Void> task) {
                Toast.makeText(application, "Joined group successfully",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getUserJoinedGroups(UserJoinedGroupsCallback userJoinedGroupsCallback) {
        firebaseFirestore.collection("groups")
                .whereArrayContains("connectedUsers", currentUserEmail)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    userCreatedGroups = new ArrayList<>();
                    for (DocumentSnapshot documentSnapshot : task.getResult()) {
                        Log.d(TAG.FIREBASE_STORAGE.toString(), documentSnapshot.getId() + " => " + documentSnapshot.getData());
                        Group group = documentSnapshot.toObject(Group.class);
                        userCreatedGroups.add(group);
                        userJoinedGroupsCallback.joinedGroupsOnCallbackSuccess(userCreatedGroups);
                    }
                    userJoinedGroupsCallback.joinedGroupsOnCallbackNoResults();
                } else {
                    Log.d(TAG.FIREBASE_STORAGE.toString(), "Error getting documents: ", task.getException());

                }

            }
        });

    }
}
