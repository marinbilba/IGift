package com.viauc.igift.ui.signin;

import android.app.Application;
import android.text.TextUtils;
import android.util.Pair;
import android.util.Patterns;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.google.firebase.auth.FirebaseUser;
import com.viauc.igift.data.AuthAppRepository;
import com.viauc.igift.util.FieldValidation;

public class SignInViewModel extends AndroidViewModel {

    private final AuthAppRepository authAppRepository;
private final FieldValidation fieldValidation;

    public SignInViewModel(Application app){
        super(app);
fieldValidation=new FieldValidation();
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

    public boolean validateEmail(String email) {
        return fieldValidation.emailEmptyStringAndPatternValidation(email);
    }

    public Pair<Boolean, String> emptyFieldValidation(String password) {
        return fieldValidation.validateEmptyField(password);
    }
}
