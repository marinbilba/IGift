package com.viauc.igift.ui.groups;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.mindorks.placeholderview.ExpandablePlaceHolderView;
import com.viauc.igift.R;
import com.viauc.igift.data.callbacks.OnGroupClickListener;
import com.viauc.igift.model.Group;

import java.util.ArrayList;

public class GroupsFragment extends Fragment {

    private GroupsViewModel groupsViewModel;
    private ExpandablePlaceHolderView createdGroupsPlaceHolderView;
    private ExpandablePlaceHolderView groupsUserJoinedPlaceHolderView;
    private View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        groupsViewModel =
                new ViewModelProvider(this).get(GroupsViewModel.class);
        view = inflater.inflate(R.layout.fragment_groups, container, false);
        createdGroupsPlaceHolderView = (ExpandablePlaceHolderView) view.findViewById(R.id.expandableCreatedGroupsPlaceholder);
        groupsUserJoinedPlaceHolderView = (ExpandablePlaceHolderView) view.findViewById(R.id.expandableJoinedGroupsPlaceholder);

        groupsViewModel.getUserCreatedGroups().observe(getViewLifecycleOwner(), this::inflateCreatedUserGroupsExpandablePlaceHolderView);
        groupsViewModel.getUserJoinedGroups().observe(getViewLifecycleOwner(), this::inflateUserJoinedGroupsExpandablePlaceHolderView);

        return view;
    }

    private void inflateUserJoinedGroupsExpandablePlaceHolderView(ArrayList<Group> groups) {
        groupsUserJoinedPlaceHolderView.removeAllViews();
        groupsUserJoinedPlaceHolderView.addView(new HeaderView(getContext(), "Groups I joined"));
        for (Group group : groups) {
            groupsUserJoinedPlaceHolderView.addView(new JoinedGroupsView(onGroupClickListener, group));
        }
    }

    private void inflateCreatedUserGroupsExpandablePlaceHolderView(ArrayList<Group> groups) {
        createdGroupsPlaceHolderView.removeAllViews();
        createdGroupsPlaceHolderView.addView(new HeaderView(getContext(), "Groups I manage"));
        for (Group group : groups) {
            createdGroupsPlaceHolderView.addView(new ManagedGroupsView(onGroupClickListener, group));
        }
    }


    OnGroupClickListener onGroupClickListener = new OnGroupClickListener() {
        @Override
        public void onGroupClickCallback(Group group) {
            GroupsFragmentDirections.ActionNavigationGroupsToGroupMembersFragment action =
                    GroupsFragmentDirections.actionNavigationGroupsToGroupMembersFragment(group);
            Navigation.findNavController(view).navigate(action);
        }
        @Override
        public void onDeleteGroupCallBack(Group group) {
            groupsViewModel.deleteGroup(group);
        }
    };

}