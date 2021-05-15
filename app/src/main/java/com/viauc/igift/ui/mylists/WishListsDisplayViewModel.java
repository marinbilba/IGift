package com.viauc.igift.ui.mylists;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.viauc.igift.data.WishListsRepository;
import com.viauc.igift.data.callbacks.FetchWishListCallback;

import org.jetbrains.annotations.NotNull;

public class WishListsDisplayViewModel extends AndroidViewModel {
    private final WishListsRepository wishListsRepository;

    public WishListsDisplayViewModel(@NonNull @NotNull Application application) {
        super(application);
        wishListsRepository=WishListsRepository.getInstance(application);

    }
    public void getUserWishLists(FetchWishListCallback fetchWishListCallback,String userEmail) {
        wishListsRepository.getUserWishLists(fetchWishListCallback,userEmail);

    }
}
