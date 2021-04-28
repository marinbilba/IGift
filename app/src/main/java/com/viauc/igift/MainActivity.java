package com.viauc.igift;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private SignInViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(SignInViewModel.class);
        checkIfSignedIn();
        Intent myIntent = new Intent(this, NewUserMenuActivity.class);
        this.startActivity(myIntent);
    }
    private void checkIfSignedIn() {
        viewModel.getCurrentUser().observe(this, user -> {
            //todo check this
            if (user != null)
                goToSignInActivity();
        });
    }

    private void goToSignInActivity() {
        Intent myIntent = new Intent(this, SignInActivity.class);
        this.startActivity(myIntent);

       // startActivity(new Intent(this, SignInActivity.class));
        finish();
    }

}