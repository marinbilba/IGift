package com.viauc.igift.ui.mylists;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.viauc.igift.data.WishListsRepository;
import com.viauc.igift.model.WishList;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class WishListsDisplayViewModel extends AndroidViewModel {
    private final WishListsRepository wishListsRepository;

    public WishListsDisplayViewModel(@NonNull @NotNull Application application) {
        super(application);
        wishListsRepository=new WishListsRepository(application);


    }
    public LiveData<ArrayList<WishList>> getUserWishLists(String userEmail) {
        wishListsRepository.getUserWishListsByEmail(userEmail);
      return  wishListsRepository.getUserWishListsLiveData();

    }
}
