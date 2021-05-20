package com.viauc.igift.ui.signup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseUser;
import com.viauc.igift.MainActivity;
import com.viauc.igift.R;
import com.viauc.igift.ui.signin.SignInActivity;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {
    private SignUpViewModel viewModel;


    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText passwordConfirmEditText;

    String email;
    String password;
    String confirmPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        viewModel = new ViewModelProvider(this).get(SignUpViewModel.class);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        passwordConfirmEditText = findViewById(R.id.confirmPasswordEditText);

        viewModel.getCurrentUser().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if (firebaseUser != null) {
                    updateUIToHomePage(firebaseUser);
                }
            }
        });


    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), SignInActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

    public void signUpUser(View view) {
        email = emailEditText.getText().toString().trim();
        password = passwordEditText.getText().toString().trim();
        confirmPassword = passwordConfirmEditText.getText().toString().trim();


        if (allFieldsValidation(email, password, confirmPassword)) {
            viewModel.signUp(email, password);
        }

    }

    private void updateUIToHomePage(FirebaseUser user) {
        if (user != null) {
            Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
            startActivityForResult(myIntent, 0);
        }
    }

    private boolean allFieldsValidation(String email, String password, String confirmPassword) {
        if (!viewModel.validateEmail(email)) {
            emailEditText.setError(getString(R.string.invalid_email));
            emailEditText.requestFocus();
            return false;
        }
        Pair<Boolean, String> passwordFieldValidation = viewModel.passwordFieldValidation(password);
        if(!passwordFieldValidation.first){
            passwordEditText.setError(passwordFieldValidation.second);
            passwordEditText.requestFocus();
            return false;
        }
        Pair<Boolean, String> confirmPasswordValidation =viewModel.confirmPasswordValidation(password,confirmPassword);
        if (!confirmPasswordValidation.first) {
            passwordConfirmEditText.setError(confirmPasswordValidation.second);
            passwordConfirmEditText.requestFocus();
            return false;
        }
        return true;
    }
}