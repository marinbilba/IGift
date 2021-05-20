package com.viauc.igift.data;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.viauc.igift.data.callbacks.UserCreatedGroupsCallback;
import com.viauc.igift.model.Group;
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

    private MutableLiveData<ArrayList<Group>> userCreatedGroupsMutableLiveData;
    private LiveData<ArrayList<Group>> userCreatedGroupsLiveData;

    private MutableLiveData<ArrayList<Group>> userJoinedGroupsMutableLiveData;
    private LiveData<ArrayList<Group>> userJoinedGroupsLiveData;


    private UserGroupsRepository(Application application) {


        this.application = application;
        userCreatedGroups = new ArrayList<>();
        // Initialize firebase  instance
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        currentUserEmail = Objects.requireNonNull(mAuth.getCurrentUser()).getEmail();

        userCreatedGroupsMutableLiveData = new MutableLiveData<>();
        userCreatedGroupsLiveData = userCreatedGroupsMutableLiveData;
        userJoinedGroupsMutableLiveData = new MutableLiveData<>();
        userJoinedGroupsLiveData = userJoinedGroupsMutableLiveData;
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


    public void getCurrentUserCreatedGroups() {
        firebaseFirestore.collection("groups")
                .whereEqualTo("ownerEmail", currentUserEmail).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable @org.jetbrains.annotations.Nullable QuerySnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
                userCreatedGroups = new ArrayList<>();
                if (value != null) {
                    for (QueryDocumentSnapshot document : value) {
                        Log.d(TAG.FIREBASE_STORAGE.toString(), document.getId() + " => " + document.getData());
                        Group group = document.toObject(Group.class);
                        // Get users connected to this group
                        try {
                            ArrayList<String> connectedUsers = (ArrayList<String>) document.get("connectedUsers");
                            group.setUsersConnected(connectedUsers);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        userCreatedGroups.add(group);
                    }
                }
                userCreatedGroupsMutableLiveData.postValue(userCreatedGroups);
                userCreatedGroupsLiveData = userCreatedGroupsMutableLiveData;
            }
        });
    }

    public void getUserCreatedGroupsByEmail(UserCreatedGroupsCallback userCreatedGroupsCallback, String userEmail) {
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

        group.setUsersConnected(joinGroupUser);
        // Convert to hash set to avoid duplicates
        HashSet<String> stringHashSet = new HashSet<>(joinGroupUser);
        ArrayList<String> joinGroupUserToStore = new ArrayList<>(stringHashSet);

        firebaseFirestore.collection("groups").document(group.getuID()).update("connectedUsers", joinGroupUserToStore).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<Void> task) {
                Toast.makeText(application, "Joined group successfully",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getUserJoinedGroups() {

        firebaseFirestore.collection("groups")
                .whereArrayContains("connectedUsers", currentUserEmail).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable @org.jetbrains.annotations.Nullable QuerySnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
                ArrayList<Group> tempUserJoinedGroups=new ArrayList();
                if (value != null) {
                    for (QueryDocumentSnapshot document : value) {
                        Log.d(TAG.FIREBASE_STORAGE.toString(), document.getId() + " => " + document.getData());
                        Group group = document.toObject(Group.class);
                        // Get users connected to this group
                        try {
                            ArrayList<String> connectedUsers = (ArrayList<String>) document.get("connectedUsers");
                            group.setUsersConnected(connectedUsers);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        tempUserJoinedGroups.add(group);
                    }
                }
                userJoinedGroupsMutableLiveData.postValue(tempUserJoinedGroups);
                userJoinedGroupsLiveData = userJoinedGroupsMutableLiveData;
            }
            });
    }




    public void deleteCreatedGroup(String getuID) {
        firebaseFirestore.collection("groups").document(getuID)
                .delete();

    }

    public LiveData<ArrayList<Group>> getUserCreatedGroupsLiveData() {
        return userCreatedGroupsLiveData;
    }

    public LiveData<ArrayList<Group>> getUserJoinedGroupsLiveData() {
        return userJoinedGroupsLiveData;
    }

    public void leaveJoinedGroupCurrentUser(Group group) {

        firebaseFirestore.collection("groups").document(group.getuID()).update("connectedUsers",FieldValue.arrayRemove(currentUserEmail));
    }
}
