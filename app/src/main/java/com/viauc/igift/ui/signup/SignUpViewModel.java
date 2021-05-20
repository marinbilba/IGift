package com.viauc.igift.ui.signup;

import android.app.Application;
import android.text.TextUtils;
import android.util.Pair;
import android.util.Patterns;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseUser;
import com.viauc.igift.data.AuthAppRepository;
import com.viauc.igift.util.FieldValidation;

public class SignUpViewModel extends AndroidViewModel {
    private final AuthAppRepository authAppRepository;
private final FieldValidation fieldValidation;
    public SignUpViewModel(Application app){
        super(app);
        authAppRepository = AuthAppRepository.getInstance(app);
    fieldValidation=new FieldValidation();
    }

    public LiveData<FirebaseUser> getCurrentUser(){
        return authAppRepository.getCurrentUser();
    }

    public void signUp(String email, String password){
        authAppRepository.signUp(email,password);
    }


    public boolean validateEmail(String email) {
        return   fieldValidation.emailEmptyStringAndPatternValidation(email);
    }

    public Pair<Boolean, String> passwordFieldValidation(String password) {
        return fieldValidation.passwordFieldValidation(password);
    }

    public Pair<Boolean, String> confirmPasswordValidation(String password, String confirmPassword) {
        return fieldValidation.confirmPasswordValidation(password,confirmPassword);
    }
}
