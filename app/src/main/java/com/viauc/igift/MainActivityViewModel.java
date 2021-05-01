package com.viauc.igift;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;
import com.viauc.igift.data.UserRepository;

public class MainActivityViewModel extends AndroidViewModel {
    private final UserRepository userRepository;

    public MainActivityViewModel(Application app){
        super(app);
        userRepository = UserRepository.getInstance(app);
    }

public void signOutUser(){
        userRepository.signOut();
}
    public LiveData<FirebaseUser> getCurrentUser(){
        return userRepository.getCurrentUser();
    }
}
