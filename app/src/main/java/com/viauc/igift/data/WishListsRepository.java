package com.viauc.igift.data;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.viauc.igift.data.callbacks.FetchUserCallback;
import com.viauc.igift.model.User;
import com.viauc.igift.model.WishItem;
import com.viauc.igift.model.WishList;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class WishListsRepository {
    private static WishListsRepository instance;

    private final AuthAppRepository authAppRepository;

    private final Application application;
    private final FirebaseFirestore firebaseFirestore;
    private final FirebaseAuth mAuth;
    private final String currentUserEmail;
    private ArrayList<WishList> userWishLists;

    private final MutableLiveData<ArrayList<WishList>> userWishListsMutableLiveData;
    private LiveData<ArrayList<WishList>> userWishListsLiveData;

    private final MutableLiveData<WishList> userWishListMutableLiveData;
    private LiveData<WishList> userWishListLiveData;

    public WishListsRepository(Application application) {
        this.application = application;
        // Initialize firebase  instance
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        currentUserEmail = Objects.requireNonNull(mAuth.getCurrentUser()).getEmail();
        authAppRepository = AuthAppRepository.getInstance(application);

        userWishListsMutableLiveData = new MutableLiveData<>();
        userWishListsLiveData = userWishListsMutableLiveData;

        userWishListMutableLiveData = new MutableLiveData<>();
        userWishListLiveData = userWishListMutableLiveData;
    }



    public void createNewList(String listName) {
        if (mAuth.getUid() == null) {
            return;
        }
        Map<String, Object> newList = new HashMap<>();
        newList.put("listName", listName);

        firebaseFirestore.collection("users").document(mAuth.getUid()).collection("wishLists").document(listName).set(newList);

    }

    public void addWishItemToWishList(WishList wishList, WishItem itemToAdd) {
        if (mAuth.getUid() == null) {
            return;
        }

        firebaseFirestore.collection("users").document(mAuth.getUid()).collection("wishLists").document(wishList.getListName()).update("wishItems", FieldValue.arrayUnion(itemToAdd)).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<Void> task) {
                System.out.println();
            }
        });
    }


    public void getCurrentUserWishLists() {
        if (mAuth.getUid() == null) {
            return;
        }
        fetchUserWishLists(mAuth.getUid());
    }

    public void getUserWishListsByEmail( String userEmail) {

        FetchUserCallback fetchUserCallback = new FetchUserCallback() {
            @Override
            public void fetchUserOnSuccess(User user) {
                try {
                    String userId = user.getuID();
                    fetchUserWishLists(userId);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }

        };
        authAppRepository.getUserByEmail(userEmail, fetchUserCallback);
    }

    private void fetchUserWishLists(String userId) {

        firebaseFirestore.collection("users").document(userId).collection("wishLists").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable @org.jetbrains.annotations.Nullable QuerySnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
                userWishLists=new ArrayList<>();
                if (value != null) {
                    for (QueryDocumentSnapshot document : value) {
                        WishList wishList = document.toObject(WishList.class);
                            // Get wish list items
                            try {
                                ArrayList<Map<String, Object>> wishItems = (ArrayList<Map<String, Object>>) document.get("wishItems");
                                ArrayList<WishItem> convertedWishItems = convertMapArrayToObjectArray(wishItems);
                                wishList.setWishItemsList(convertedWishItems);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            userWishLists.add(wishList);
                    }
                    userWishListsMutableLiveData.postValue(userWishLists);
                    userWishListsLiveData =userWishListsMutableLiveData;
                }
            }
        });
    }

    private ArrayList<WishItem> convertMapArrayToObjectArray(ArrayList<Map<String, Object>> wishItems) {
        ArrayList<WishItem> tempWishItems = new ArrayList<>();
       if(wishItems!=null){
        for (Map<String, Object> item : wishItems) {
            WishItem wishItem;
            try {
                double itemPrice = Double.parseDouble(item.get("price").toString());
                wishItem = new WishItem(item.get("giftName").toString(), itemPrice, item.get("description").toString(), item.get("whereToBuy").toString());
                tempWishItems.add(wishItem);

            } catch (Exception e) {
                wishItem = new WishItem(item.get("giftName").toString(), item.get("description").toString(), item.get("whereToBuy").toString());
                tempWishItems.add(wishItem);
            }

        }
        }

        return tempWishItems;
    }

    public LiveData<ArrayList<WishList>> getUserWishListsLiveData() {
        return userWishListsLiveData;
    }
    //todo Delete this data is possible only through Callable Cloud Function
    public void deleteWishList(WishList wishList) {
//        Map<String, Object> data = new HashMap<>();
//        data.put("path", wishList.getListName());
        Toast.makeText(application, "Coming soon :))",
                Toast.LENGTH_SHORT).show();
    }

    public void deleteWishItem(String listName, WishItem wishItem) {
        if(mAuth.getUid()==null){
            return;
        }
      firebaseFirestore.collection("users").document(mAuth.getUid()).collection("wishLists").document(listName).update("wishItems",FieldValue.arrayRemove(wishItem));

    }

    public void getWishItemsOfWishList(String listName) {
        firebaseFirestore.collection("users").document(mAuth.getUid()).collection("wishLists").document(listName).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable @org.jetbrains.annotations.Nullable DocumentSnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
                if (value != null) {
                    WishList wishList = value.toObject(WishList.class);
                        // Get wish list items
                        try {
                            ArrayList<Map<String, Object>> wishItems = (ArrayList<Map<String, Object>>) value.get("wishItems");
                            ArrayList<WishItem> convertedWishItems = convertMapArrayToObjectArray(wishItems);
                            if (wishList != null) {
                                wishList.setWishItemsList(convertedWishItems);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    userWishListMutableLiveData.postValue(wishList);
                    userWishListLiveData =userWishListMutableLiveData;
                }
            }
        });
    }

    public LiveData<WishList> getUserWishListLiveData() {
        return userWishListLiveData;
    }
}
