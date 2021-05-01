package com.viauc.igift.ui.signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.viauc.igift.MainActivity;
import com.viauc.igift.R;
import com.viauc.igift.ui.signin.SignInActivity;

public class SignUpActivity extends AppCompatActivity {
    private SignUpViewModel viewModel;


    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText passwordConfirmEditText;

    private FirebaseAuth mAuth;

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

         mAuth= FirebaseAuth.getInstance();

    }
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), SignInActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

    public void signUpUser(View view) {
        final String email = emailEditText.getText().toString().trim();
        final String password = passwordEditText.getText().toString().trim();
        final String confirmPassword = passwordConfirmEditText.getText().toString().trim();

       if( allFieldsValidation(email,password,confirmPassword)) {

           mAuth.createUserWithEmailAndPassword(email, password)
                   .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if (task.isSuccessful()) {
                               // Sign in success, update UI with the signed-in user's information
                               FirebaseUser user = mAuth.getCurrentUser();
                               updateUIToHomePage(user);
                           } else {
                               // If signup fails, and user emial is already registered.
                               if (task.getException() instanceof FirebaseAuthUserCollisionException) {

                                   Toast.makeText(getApplicationContext(),"The email address is already in use by another account.", Toast.LENGTH_SHORT).show();

                               }
                               // If sign in fails, display a message to the user.
                               Log.w("Sing up", "signInWithEmail:failure", task.getException());
                               Toast.makeText(getApplicationContext(), "Authentication failed.",
                                       Toast.LENGTH_SHORT).show();
                           }
                       }
                   });
       }

    }




    private void updateUIToHomePage(FirebaseUser user) {
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
    }

    private boolean allFieldsValidation(String email, String password, String confirmPassword) {

     if (!viewModel.isValidEmail(email)){
            emailEditText.setError(getString(R.string.invalid_email));
            emailEditText.requestFocus();
            return false;
        }
        if (password.isEmpty()) {
            passwordEditText.setError(getString(R.string.required_field));
            passwordEditText.requestFocus();
            return false;
        }else if (password.length()<6){
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