package com.viauc.igift.data;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.viauc.igift.data.callbacks.FetchUserCallback;
import com.viauc.igift.data.callbacks.FetchWishListCallback;
import com.viauc.igift.model.User;
import com.viauc.igift.model.WishItem;
import com.viauc.igift.model.WishList;
import com.viauc.igift.util.TAG;

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
    private String currentUserEmail;
    private ArrayList<WishList> userWishLists;


    private WishListsRepository(Application application) {
        this.application = application;
        // Initialize firebase  instance
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        currentUserEmail = Objects.requireNonNull(mAuth.getCurrentUser()).getEmail();
        authAppRepository = AuthAppRepository.getInstance(application);
    }

    public static WishListsRepository getInstance(Application app) {
        if (instance == null)
            instance = new WishListsRepository(app);
        return instance;
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


    public void getCurrentUserWishLists(FetchWishListCallback fetchWishListCallback) {
        if (mAuth.getUid() == null) {
            return;
        }
        fetchUserWishLists(fetchWishListCallback, mAuth.getUid());
    }

    public void getUserWishLists(FetchWishListCallback fetchWishListCallback, String userEmail) {

        FetchUserCallback fetchUserCallback = new FetchUserCallback() {
            @Override
            public void fetchUserOnSuccess(User user) {
                try {
                    String userId = user.getuID();
                    fetchUserWishLists(fetchWishListCallback, userId);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }

        };
        authAppRepository.getUserByEmail(userEmail, fetchUserCallback);
    }

    private void fetchUserWishLists(FetchWishListCallback fetchWishListCallback, String userId) {

        firebaseFirestore.collection("users").document(userId).collection("wishLists").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    userWishLists = new ArrayList<>();
                    for (DocumentSnapshot documentSnapshot : task.getResult()) {
                        Log.d(TAG.FIREBASE_STORAGE.toString(), documentSnapshot.getId() + " => " + documentSnapshot.getData());
                        WishList wishList = documentSnapshot.toObject(WishList.class);
                        if (wishList != null) {
                            // Get wish list items
                            try {
                                ArrayList<Map<String, Object>> wishItems = (ArrayList<Map<String, Object>>) documentSnapshot.get("wishItems");
                                ArrayList<WishItem> convertedWishItems = convertMapArrayToObjectArray(wishItems);
                                wishList.setWishItemsList(convertedWishItems);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            userWishLists.add(wishList);
                            fetchWishListCallback.fetchedWishListOnSuccess(userWishLists);
                        }
                    }
                } else {
                    Log.d(TAG.FIREBASE_STORAGE.toString(), "Error getting documents: ", task.getException());

                }
            }
        });
    }

    private ArrayList<WishItem> convertMapArrayToObjectArray(ArrayList<Map<String, Object>> wishItems) {
        ArrayList<WishItem> tempWishItems = new ArrayList<>();
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

        return tempWishItems;
    }
}
