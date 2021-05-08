package com.viauc.igift.ui.connect.newgroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viauc.igift.R;

public class NewGroupFragment extends Fragment {
    NewGroupViewModel newGroupViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        newGroupViewModel = new ViewModelProvider(this).get(NewGroupViewModel.class);
        return inflater.inflate(R.layout.fragment_create_group, container, false);
    }

}