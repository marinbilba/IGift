package com.viauc.igift.ui.signup;

import android.app.Application;
import android.text.TextUtils;
import android.util.Patterns;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;
import com.viauc.igift.data.AuthAppRepository;

public class SignUpViewModel extends AndroidViewModel {
    private final AuthAppRepository authAppRepository;

    public SignUpViewModel(Application app){
        super(app);
        authAppRepository = AuthAppRepository.getInstance(app);
    }

    public LiveData<FirebaseUser> getCurrentUser(){
        return authAppRepository.getCurrentUser();
    }

    public  boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public void signUp(String email, String password){
        authAppRepository.signUp(email,password);
    }


}
