package com.viauc.igift.ui.mylists;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.viauc.igift.data.WishListsRepository;
import com.viauc.igift.model.WishList;

import java.util.ArrayList;

public class MyListsViewModel extends AndroidViewModel {

    private final WishListsRepository wishListsRepository;

    public MyListsViewModel(Application app) {
        super(app);
        wishListsRepository=new WishListsRepository(app);


    }



    public LiveData<ArrayList<WishList>> getUserWishLists() {
        wishListsRepository.getCurrentUserWishLists();
        return wishListsRepository.getUserWishListsLiveData();

    }

    public void deleteWishList(WishList wishList) {
        wishListsRepository.deleteWishList(wishList);
    }
}