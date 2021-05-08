package com.viauc.igift.ui.connect.joingroup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.viauc.igift.R;
import com.viauc.igift.ui.connect.ConnectViewModel;

public class JoinGroupFragment extends Fragment {

    private JoinGroupViewModel joinGroupViewModel;
    View view;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        joinGroupViewModel =
                new ViewModelProvider(this).get(JoinGroupViewModel.class);
        view = inflater.inflate(R.layout.fragment_join_group, container, false);
    return view;
    }
}
