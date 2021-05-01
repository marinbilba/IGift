package com.viauc.igift;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class SignInActivity extends AppCompatActivity {

    private SignInViewModel viewModel;

    private TextView emailEditText;
    private TextView passwordEditText;

    private FirebaseAuth mAuth;
    private FirebaseUser user;

    // Facebook
    private CallbackManager mCallbackManager;
   private LoginButton loginButton;
    private static final String TAG="FacebookAuthentication";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        viewModel = new ViewModelProvider(this).get(SignInViewModel.class);

        mAuth=FirebaseAuth.getInstance();

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        // Initialize Facebook Login button
        mCallbackManager = CallbackManager.Factory.create();
        loginButton = findViewById(R.id.facebook_login_button);
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
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
        mCallbackManager.onActivityResult(requestCode, resultCode, data);



    }
    private void handleFacebookAccessToken(AccessToken accessToken) {

        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Facebook", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUiOnSingIn(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Facebook", "signInWithCredential:failure", task.getException());
                            Toast.makeText(SignInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUiOnSingIn(null);
                        }
                    }
                });
    }


    private void updateUiOnSingIn(FirebaseUser myUserObj) {
        if(myUserObj!=null){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

    }

    public void signInUser(View view) {
//        List<AuthUI.IdpConfig> providers = Arrays.asList(
//                new AuthUI.IdpConfig.EmailBuilder().build(),
//                new AuthUI.IdpConfig.FacebookBuilder().build());

        final String email = emailEditText.getText().toString().trim();
        final String password = passwordEditText.getText().toString().trim();

        if(loginFieldsValidation(email,password)) {
  mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUiOnSingIn(user);
                                finish();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(getApplicationContext(), "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                updateUiOnSingIn(null);
                            }
                        }
                    });
        }
    }
    private boolean loginFieldsValidation(String email, String password) {

        if (!viewModel.isValidEmail(email)){
            emailEditText.setError(getString(R.string.invalid_email));
            emailEditText.requestFocus();
            return false;
        }
        if (password.isEmpty()) {
            passwordEditText.setError(getString(R.string.required_field));
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