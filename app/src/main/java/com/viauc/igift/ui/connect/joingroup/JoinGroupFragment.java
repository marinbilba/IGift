package com.viauc.igift.ui.connect.joingroup;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.viauc.igift.R;
import com.viauc.igift.data.callbacks.CreateGroupCallback;
import com.viauc.igift.data.callbacks.OnJoinGroupClickListener;
import com.viauc.igift.model.Group;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JoinGroupFragment extends Fragment {
    ArrayList<Group> fetchedGroups = new ArrayList<>();
    JoinGroupViewModel joinGroupViewModel;
    View view;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_join_group, container, false);
        joinGroupViewModel =
                new ViewModelProvider(this).get(JoinGroupViewModel.class);
        recyclerView = view.findViewById(R.id.joinGroupRecyclerView);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        String userEmail = JoinGroupFragmentArgs.fromBundle(getArguments()).getUserEmail();
        joinGroupViewModel.getUserCreatedGroupsByEmail(createGroupCallback, userEmail);
    }

    CreateGroupCallback createGroupCallback = new CreateGroupCallback() {
        @Override
        public void createdGroupsOnCallbackSuccess(List<Group> list) {
fetchedGroups= (ArrayList<Group>) list;
            JoinGroupAdapter myAdapter = new JoinGroupAdapter((ArrayList<Group>) list, onJoinGroupClickListener, getContext());
            recyclerView.setAdapter(myAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }

        @Override
        public void createdGroupsOnCallbackNoResults() {

        }
    };
    OnJoinGroupClickListener onJoinGroupClickListener = new OnJoinGroupClickListener() {
        @Override
        public void onGroupPositionClicked(int position) {
fetchedGroups.get(position);
            Log.d("Press", "onGroupPositionClicked: "+fetchedGroups.get(position).getGroupName());
        }
    };

}
