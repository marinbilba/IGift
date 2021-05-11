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

import com.mindorks.placeholderview.ExpandablePlaceHolderView;
import com.viauc.igift.R;
import com.viauc.igift.model.Group;
import com.viauc.igift.ui.groups.GroupNameView;
import com.viauc.igift.ui.groups.HeaderView;
import com.viauc.igift.ui.signin.SignInActivity;

public class MyListsFragment extends Fragment {

    private MyListsViewModel myListsViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myListsViewModel = new ViewModelProvider(this).get(MyListsViewModel.class);
        View view = inflater.inflate(R.layout.fragment_my_list, container, false);

        return view;
    }

}