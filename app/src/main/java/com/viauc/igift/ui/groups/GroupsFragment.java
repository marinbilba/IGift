package com.viauc.igift.ui.groups;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.mindorks.placeholderview.ExpandablePlaceHolderView;
import com.viauc.igift.R;
import com.viauc.igift.data.CreateGroupCallback;
import com.viauc.igift.model.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupsFragment extends Fragment  {

    private GroupsViewModel groupsViewModel;
    private ExpandablePlaceHolderView expandablePlaceHolderView;
    ArrayList<Group> userCreatedGroups;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        groupsViewModel =
                new ViewModelProvider(this).get(GroupsViewModel.class);
        View view = inflater.inflate(R.layout.fragment_groups, container, false);

        expandablePlaceHolderView = (ExpandablePlaceHolderView) view.findViewById(R.id.expandableCreatedGroupsPlaceholder);
        expandablePlaceHolderView.addView(new HeaderView(getContext(), "Groups I manage"));
        expandablePlaceHolderView.addView(new HeaderView(getContext(), "Groups I joined"));

        loadGroupData();
        return view;
    }

    private void loadGroupData() {
        groupsViewModel.getUserCreatedGroups(createGroupCallback);
    }
    CreateGroupCallback createGroupCallback =new CreateGroupCallback() {
        @Override
        public void createdGroupsOnCallbackSuccess(List<Group> list) {
            for (Group group : list) {
                expandablePlaceHolderView.addView(new GroupNameView(getContext(), group));
            }
        }

        @Override
        public void createdGroupsOnCallbackNoResults() {
            Group testGroup=new Group();
            testGroup.setGroupName("No managed groups");
            expandablePlaceHolderView.addView(new GroupNameView(getContext(), testGroup));

        }
    };


}