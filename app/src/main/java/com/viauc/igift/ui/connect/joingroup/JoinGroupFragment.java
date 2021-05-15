package com.viauc.igift.ui.connect.joingroup;

import android.os.Bundle;
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
import com.viauc.igift.data.callbacks.UserCreatedGroupsCallback;
import com.viauc.igift.data.callbacks.OnRecyclerViewPositionClickListener;
import com.viauc.igift.model.Group;

import java.util.ArrayList;
import java.util.List;

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
        joinGroupViewModel.getUserCreatedGroupsByEmail(userCreatedGroupsCallback, userEmail);
    }

    UserCreatedGroupsCallback userCreatedGroupsCallback = new UserCreatedGroupsCallback() {
        @Override
        public void createdGroupsOnCallbackSuccess(List<Group> list) {
            fetchedGroups = (ArrayList<Group>) list;
            JoinGroupAdapter myAdapter = new JoinGroupAdapter((ArrayList<Group>) list, onRecyclerViewPositionClickListener, getContext());
            recyclerView.setAdapter(myAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }

        @Override
        public void createdGroupsOnCallbackNoResults() {

        }
    };
    OnRecyclerViewPositionClickListener onRecyclerViewPositionClickListener = new OnRecyclerViewPositionClickListener() {
        @Override
        public void onRecyclerViewPositionCallback(int position) {
            joinGroupViewModel.joinGroup(fetchedGroups.get(position));
        }
    };

}
