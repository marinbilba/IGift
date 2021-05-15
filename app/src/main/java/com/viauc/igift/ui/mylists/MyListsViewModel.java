package com.viauc.igift.ui.mylists;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.viauc.igift.data.WishListsRepository;
import com.viauc.igift.data.callbacks.FetchWishListCallback;

public class MyListsViewModel extends AndroidViewModel {

    private final WishListsRepository wishListsRepository;

    public MyListsViewModel(Application app) {
        super(app);
        wishListsRepository=WishListsRepository.getInstance(app);


    }



    public void getUserWishLists(FetchWishListCallback fetchWishListCallback) {
        wishListsRepository.getCurrentUserWishLists(fetchWishListCallback);

    }
}