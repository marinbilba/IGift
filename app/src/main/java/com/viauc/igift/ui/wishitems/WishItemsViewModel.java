package com.viauc.igift.ui.wishitems;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.viauc.igift.data.WishListsRepository;
import com.viauc.igift.model.WishItem;
import com.viauc.igift.model.WishList;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class WishItemsViewModel extends AndroidViewModel {
    private final WishListsRepository wishListsRepository;

    public WishItemsViewModel(@NonNull @NotNull Application application) {
        super(application);
        wishListsRepository=new WishListsRepository(application);

    }


    public void deleteWishItem(String listName, WishItem wishItem) {
        wishListsRepository.deleteWishItem(listName,wishItem);
    }

    public  LiveData<ArrayList<WishList>> getUserWishLists() {
      return   wishListsRepository.getUserWishListsLiveData();
    }

    public LiveData<WishList> getWishItemsOfWishList(String listName) {
    wishListsRepository.getWishItemsOfWishList(listName);
    return wishListsRepository.getUserWishListLiveData();
    }
}