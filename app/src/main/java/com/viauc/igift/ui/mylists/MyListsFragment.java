package com.viauc.igift.ui.mylists;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.viauc.igift.R;
import com.viauc.igift.ui.signin.SignInActivity;

public class MyListsFragment extends Fragment {

    private MyListsViewModel myListsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myListsViewModel = new ViewModelProvider(this).get(MyListsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_my_list, container, false);

        final TextView textView = root.findViewById(R.id.text_home);
        myListsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        Button button = (Button) root.findViewById(R.id.signOut);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListsViewModel.signOut();
                checkIfSignedIn();
            }
        });

        return root;
    }

    private void checkIfSignedIn() {
        myListsViewModel.getCurrentUser().observe(this, user -> {
            if (user == null) {
                Intent intent = new Intent(getActivity(), SignInActivity.class);
                startActivity(intent);
            }
        });
    }
}