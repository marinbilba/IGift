package com.viauc.igift.ui.forgotpassword;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.viauc.igift.data.AuthAppRepository;
import com.viauc.igift.data.callbacks.ForgotPasswordCallback;
import com.viauc.igift.util.FieldValidation;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

public class ForgotPasswordViewModel extends AndroidViewModel {
    private final AuthAppRepository authAppRepository;
private final FieldValidation fieldValidation;
    public ForgotPasswordViewModel(@NonNull @NotNull Application application) {
        super(application);
        authAppRepository = AuthAppRepository.getInstance(application);
        fieldValidation=new FieldValidation();

    }

    public boolean emailValidation(String email) {
        return fieldValidation.emailEmptyStringAndPatternValidation(email);
    }

    public void sendPasswordResetEmail(String userEmail, ForgotPasswordCallback forgotPasswordCallback) {
        authAppRepository.sendPasswordResetEmail(userEmail,forgotPasswordCallback);
    }
}
