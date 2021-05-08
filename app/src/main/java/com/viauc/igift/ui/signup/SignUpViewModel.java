package com.viauc.igift.ui.signup;

import android.app.Application;
import android.text.TextUtils;
import android.util.Patterns;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.auth.FirebaseUser;
import com.viauc.igift.data.AuthAppRepository;
import com.viauc.igift.data.UserRepository;

public class SignUpViewModel extends AndroidViewModel {
    private final UserRepository userRepository;
    private AuthAppRepository authAppRepository;

    public SignUpViewModel(Application app){
        super(app);
        userRepository = UserRepository.getInstance(app);
        authAppRepository = AuthAppRepository.getInstance(app);
    }

    public LiveData<FirebaseUser> getCurrentUser(){
        return userRepository.getCurrentUser();
    }

    public  boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public void signUp(String email, String password){
        authAppRepository.signUp(email,password);
    }


}
