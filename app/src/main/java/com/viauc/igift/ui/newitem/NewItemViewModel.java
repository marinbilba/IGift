package com.viauc.igift.ui.newitem;

import android.app.Application;
import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.viauc.igift.data.WishListsRepository;
import com.viauc.igift.model.WishItem;
import com.viauc.igift.model.WishList;
import com.viauc.igift.util.FieldValidation;

import org.jetbrains.annotations.NotNull;

public class NewItemViewModel extends AndroidViewModel {
    private final WishListsRepository wishListsRepository;
    private final FieldValidation fieldValidation;

    public NewItemViewModel(@NonNull @NotNull Application application) {
        super(application);
        wishListsRepository=WishListsRepository.getInstance(application);
        fieldValidation=new FieldValidation();

    }

    public Pair<Boolean, String> validateItemName(String itemName) {
       return fieldValidation.validateWishItemName(itemName);
    }

    public void addNewWishItem(WishList wishList, WishItem wishItem) {
        wishListsRepository.addWishItemToWishList(wishList,wishItem);
    }
}