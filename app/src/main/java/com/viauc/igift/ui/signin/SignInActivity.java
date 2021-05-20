package com.viauc.igift.ui.signin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.viauc.igift.ui.forgotpassword.ForgotPasswordActivity;
import com.viauc.igift.MainActivity;
import com.viauc.igift.R;
import com.viauc.igift.ui.signup.SignUpActivity;

public class SignInActivity extends AppCompatActivity {

    private SignInViewModel viewModel;

    private TextView emailEditText;
    private TextView passwordEditText;


    // Facebook
    private CallbackManager facebookCallbackManager;
    private LoginButton loginButton;
    private static final String TAG = "FacebookAuthentication";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        viewModel = new ViewModelProvider(this).get(SignInViewModel.class);
        facebookCallbackManager =viewModel.getFacebookCallbackManager();
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.signInPasswordEditText);

        viewModel.getCurrentUser().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if (firebaseUser != null) {
                    updateUiToMainActivity(firebaseUser);
                }
            }
        });

        loginButton = findViewById(R.id.facebook_login_button);
        loginButton.registerCallback(viewModel.getFacebookCallbackManager(), new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                viewModel.handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        facebookCallbackManager.onActivityResult(requestCode, resultCode, data);
    }



    private void updateUiToMainActivity(FirebaseUser myUserObj) {
        if (myUserObj != null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

    }

    public void signInUser(View view) {
        final String email = emailEditText.getText().toString().trim();
        final String password = passwordEditText.getText().toString().trim();

        if (loginFieldsValidation(email, password)) {
            viewModel.signIn(email, password);

        }
    }

    private boolean loginFieldsValidation(String email, String password) {
        if (!viewModel.validateEmail(email)) {
            emailEditText.setError(getString(R.string.invalid_email));
            emailEditText.requestFocus();
            return false;
        }
        Pair<Boolean, String> passwordFieldValidation = viewModel.emptyFieldValidation(password);
        if(!passwordFieldValidation.first){
            passwordEditText.setError(passwordFieldValidation.second);
            passwordEditText.requestFocus();
            return false;
        }

        return true;
    }

    public void signUpUser(View view) {
        startActivity(new Intent(this, SignUpActivity.class));
    }

    public void forgotPassword(View view) {
        startActivity(new Intent(this, ForgotPasswordActivity.class));
    }

}
