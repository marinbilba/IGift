package com.viauc.igift.ui.forgotpassword;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
import com.viauc.igift.ui.signin.SignInActivity;

public class ForgotPasswordActivity extends AppCompatActivity {
    private static final String TAG = "ForgotPasswordActivity";

    public FirebaseAuth mAuth;
    EditText emailEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        emailEditText = findViewById(R.id.emailEditText);
        mAuth = FirebaseAuth.getInstance();
}

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent = new Intent(getApplicationContext(), SignInActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

    private boolean emailValidation(String email) {

        if (!isValidEmail(email)) {
            emailEditText.setError(getString(R.string.invalid_email));
            emailEditText.requestFocus();
            return false;
        }
        return true;
    }

    public boolean isValidEmail(String target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());

    }

    public void resetPassword(View view) {
        final String email = emailEditText.getText().toString().trim();
        if (emailValidation(email)) {


            mAuth.sendPasswordResetEmail(emailEditText.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) Log.d(TAG, "Email sent.");


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
                    });

        }
    }

}
