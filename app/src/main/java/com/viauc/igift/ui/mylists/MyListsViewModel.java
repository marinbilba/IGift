package com.viauc.igift.ui.mylists;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseUser;
import com.viauc.igift.data.UserRepository;

public class MyListsViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;

    private UserRepository userRepository;
    public MyListsViewModel(Application app) {
        super(app);
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
        userRepository = UserRepository.getInstance(app);

    }

    public LiveData<String> getText() {
        return mText;
    }

    public void signOut() {
        userRepository.signOut();

    }
    public LiveData<FirebaseUser> getCurrentUser(){
        return userRepository.getCurrentUser();
    }
}