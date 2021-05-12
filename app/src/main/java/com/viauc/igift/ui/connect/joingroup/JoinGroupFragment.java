package com.viauc.igift.ui.connect.joingroup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.viauc.igift.R;
import com.viauc.igift.ui.connect.searchgroup.SearchGroupViewModel;

public class JoinGroupFragment extends Fragment {
    JoinGroupViewModel joinGroupViewModel;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_group, container, false);
        joinGroupViewModel =
                new ViewModelProvider(this).get(JoinGroupViewModel.class);
        return view;
    }
}
