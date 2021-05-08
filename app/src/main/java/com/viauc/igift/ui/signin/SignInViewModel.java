package com.viauc.igift.ui.signin;

import android.app.Application;
import android.text.TextUtils;
import android.util.Patterns;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.google.firebase.auth.FirebaseUser;
import com.viauc.igift.data.AuthAppRepository;

public class SignInViewModel extends AndroidViewModel {

    private final AuthAppRepository authAppRepository;


    public SignInViewModel(Application app){
        super(app);

        authAppRepository = AuthAppRepository.getInstance(app);
    }


    public boolean isValidEmail(String target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());

    }
    public void signIn(String email, String password){
        authAppRepository.signIn(email,password);
    }

    public LiveData<FirebaseUser> getCurrentUser() {
        return authAppRepository.getCurrentUser();
    }



    public void handleFacebookAccessToken(AccessToken accessToken) {
        authAppRepository.handleFacebookAccessToken(accessToken);

    }

    public CallbackManager getFacebookCallbackManager() {
        return authAppRepository.getFacebookCallbackManager();
    }
}
