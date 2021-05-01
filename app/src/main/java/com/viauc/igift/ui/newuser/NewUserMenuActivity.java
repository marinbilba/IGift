package com.viauc.igift.ui.newuser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.viauc.igift.R;

public class NewUserMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_menu);
    }

    public void startGroup(View view) {
        System.out.println("suca");
    }

    public void joinGroup(View view) {
    }
}