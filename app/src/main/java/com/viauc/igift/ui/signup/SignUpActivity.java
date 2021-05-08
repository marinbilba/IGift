package com.viauc.igift.ui.signup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseUser;
import com.viauc.igift.MainActivity;
import com.viauc.igift.R;
import com.viauc.igift.ui.signin.SignInActivity;

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
                if(firebaseUser!=null){
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
            viewModel.signUp(email,password);
        }

    }

    private void updateUIToHomePage(FirebaseUser user) {
        if(user!=null){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
    }
    }

    private boolean allFieldsValidation(String email, String password, String confirmPassword) {

        if (!viewModel.isValidEmail(email)) {
            emailEditText.setError(getString(R.string.invalid_email));
            emailEditText.requestFocus();
            return false;
        }
        if (password.isEmpty()) {
            passwordEditText.setError(getString(R.string.required_field));
            passwordEditText.requestFocus();
            return false;
        } else if (password.length() < 6) {
            passwordEditText.setError(getString(R.string.password_greater_then_6_char));
            passwordEditText.requestFocus();
            return false;
        }
        if (confirmPassword.isEmpty()) {
            passwordConfirmEditText.setError(getString(R.string.required_field));
            passwordConfirmEditText.requestFocus();
            return false;
        }
        return true;
    }
}