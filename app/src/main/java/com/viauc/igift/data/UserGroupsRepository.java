package com.viauc.igift.data;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
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


    private UserGroupsRepository(Application application ) {



        this.application = application;
        userCreatedGroups=new ArrayList<>();
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

    public MutableLiveData<ArrayList<Group>> getUserCreatedGroupsLiveData() {
        MutableLiveData<ArrayList<Group>> userCreatedGroupsLiveData = new MutableLiveData<>();

        getUserCreatedGroups();
        userCreatedGroupsLiveData.setValue(userCreatedGroups);
        LiveData<ArrayList<Group>> as=userCreatedGroupsLiveData;
        return userCreatedGroupsLiveData;
    }

    public void getUserCreatedGroups() {
        firebaseFirestore.collection("groups")
                //  .whereEqualTo("ownerEmail",mAuth.getCurrentUser().getEmail())
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                    userCreatedGroups=new ArrayList<>();
                    for (DocumentSnapshot documentSnapshot : list) {
                        Log.d(TAG.FIREBASE_STORAGE.toString(), documentSnapshot.getId() + " => " + documentSnapshot.getData());
                        Group group = documentSnapshot.toObject(Group.class);

                        userCreatedGroups.add(group);


                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG.FIREBASE_STORAGE.toString(), "Error getting documents: ", e);

            }
        });

//        firebaseFirestore.collection("groups")
//                //  .whereEqualTo("ownerEmail",mAuth.getCurrentUser().getEmail())
//                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if(task.isSuccessful()){
//
//
//                }else {
//
//                }
//            }
//        });
    }
}
