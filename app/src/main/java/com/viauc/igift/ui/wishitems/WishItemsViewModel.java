package com.viauc.igift.ui.wishitems;

import android.app.Application;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.viauc.igift.data.WishListsRepository;
import com.viauc.igift.model.Group;
import com.viauc.igift.model.WishList;
import com.viauc.igift.ui.groupmembers.GroupMembersFragmentArgs;

import org.jetbrains.annotations.NotNull;

public class WishItemsViewModel extends AndroidViewModel {
    private final WishListsRepository wishListsRepository;

    public WishItemsViewModel(@NonNull @NotNull Application application) {
        super(application);
        wishListsRepository= WishListsRepository.getInstance(application);
    }


}