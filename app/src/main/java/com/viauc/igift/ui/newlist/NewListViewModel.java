package com.viauc.igift.ui.newlist;

import android.app.Application;
import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.viauc.igift.data.WishListsRepository;
import com.viauc.igift.util.FieldValidation;

import org.jetbrains.annotations.NotNull;

public class NewListViewModel extends AndroidViewModel {
    private final FieldValidation fieldValidation;
    private final WishListsRepository wishListsRepository;
    public NewListViewModel(@NonNull @NotNull Application application) {
        super(application);
        fieldValidation=new FieldValidation();
        wishListsRepository=WishListsRepository.getInstance(application);
    }

    public Pair<Boolean, String> validateListNameInputField(String listName) {
        return fieldValidation.validateEmptyAndMinCharInputField(listName,6);

    }

    public void createList(String listName) {
        wishListsRepository.createNewList(listName);

    }
}