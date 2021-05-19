package com.viauc.igift.ui.forgotpassword;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.viauc.igift.R;
import com.viauc.igift.data.callbacks.ForgotPasswordCallback;
import com.viauc.igift.ui.signin.SignInActivity;
import com.viauc.igift.ui.signup.SignUpViewModel;

public class ForgotPasswordActivity extends AppCompatActivity {
    private ForgotPasswordViewModel viewModel;
    EditText emailEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        emailEditText = findViewById(R.id.emailEditText);
        viewModel = new ViewModelProvider(this).get(ForgotPasswordViewModel.class);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), SignInActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }


    public void resetPassword(View view) {
        final String email = emailEditText.getText().toString().trim();
        if (viewModel.emailValidation(email)) {
            viewModel.sendPasswordResetEmail(emailEditText.getText().toString(), forgotPasswordCallback);
        } else {
            emailEditText.setError(getString(R.string.invalid_email));
            emailEditText.requestFocus();
        }
    }

    ForgotPasswordCallback forgotPasswordCallback = new ForgotPasswordCallback() {
        @Override
        public void displayForgotPasswordAlertDialog() {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    ForgotPasswordActivity.this);

            // set title
            alertDialogBuilder.setTitle("Reset Password");

            // set dialog message
            alertDialogBuilder
                    .setMessage("A Reset Password Link Is Sent To Your Registered Email")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            ForgotPasswordActivity.this.finish();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

        }
    };

}
