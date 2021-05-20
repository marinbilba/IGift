package com.viauc.igift.ui.otherswishitems;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.viauc.igift.data.WishListsRepository;
import com.viauc.igift.model.WishItem;

import org.jetbrains.annotations.NotNull;

public class OthersWishItemsViewModel extends AndroidViewModel {
    private final WishListsRepository wishListsRepository;

    public OthersWishItemsViewModel(@NonNull @NotNull Application application) {
        super(application);
        wishListsRepository=new WishListsRepository(application);

    }


}