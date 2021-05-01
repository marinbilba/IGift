package com.viauc.igift.ui.home;

import android.app.Application;
import android.content.Intent;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseUser;
import com.viauc.igift.MainActivity;
import com.viauc.igift.data.UserRepository;

public class HomeViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;

    private UserRepository userRepository;
    public HomeViewModel(Application app) {
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