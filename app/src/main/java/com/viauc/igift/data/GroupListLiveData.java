package com.viauc.igift.data;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.viauc.igift.model.Group;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GroupListLiveData extends LiveData<List<Group>> implements EventListener<DocumentSnapshot> {
    private DocumentReference documentReference;
    private List<Group> groupsTemp = new ArrayList<>();
    public MutableLiveData<List<Group>> shoppingList = new MutableLiveData<>();

    public GroupListLiveData(DocumentReference documentReference) {
        this.documentReference = documentReference;
    }

    @Override
    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
// 2
        if(documentSnapshot != null && documentSnapshot.exists()) {
            // 3
            Map<String, Object> shoppingListItems = documentSnapshot.getData();
            // 4
            groupsTemp.clear();
            // 5
            for (Map.Entry<String, Object> entry : shoppingListItems.entrySet()) {
                Group itemToAdd = new Group();
                itemToAdd.setGroupName(entry.getValue().toString());
                groupsTemp.add(itemToAdd);
            }
            // 6
            shoppingList.setValue(groupsTemp);
        } else {
            // 7
            Log.d("TAG", "error");
        }
    }
}
