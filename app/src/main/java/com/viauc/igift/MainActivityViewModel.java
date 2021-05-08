package com.viauc.igift;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;
import com.viauc.igift.data.AuthAppRepository;

public class MainActivityViewModel extends AndroidViewModel {
    private final AuthAppRepository authAppRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        authAppRepository = AuthAppRepository.getInstance(application);

    }


    public void signOut() {
        authAppRepository.signOut();

    }
    public LiveData<FirebaseUser> getCurrentUser(){
        return authAppRepository.getCurrentUser();
    }

}
