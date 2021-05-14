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
import com.viauc.igift.data.callbacks.UserCreatedGroupsCallback;
import com.viauc.igift.data.callbacks.UserJoinedGroupsCallback;
import com.viauc.igift.model.Group;

import java.util.List;

public class GroupsFragment extends Fragment  {

    private GroupsViewModel groupsViewModel;
    private ExpandablePlaceHolderView createdGroupsPlaceHolderView;
    private ExpandablePlaceHolderView groupsUserJoined;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        groupsViewModel =
                new ViewModelProvider(this).get(GroupsViewModel.class);
        View view = inflater.inflate(R.layout.fragment_groups, container, false);
        createdGroupsPlaceHolderView = (ExpandablePlaceHolderView) view.findViewById(R.id.expandableCreatedGroupsPlaceholder);
        groupsUserJoined = (ExpandablePlaceHolderView) view.findViewById(R.id.expandableJoinedGroupsPlaceholder);
        loadGroupData();
        return view;
    }

    private void createExpandablePlaceHolderViews() {
        createdGroupsPlaceHolderView.removeAllViews();
        groupsUserJoined.removeAllViews();
        createdGroupsPlaceHolderView.addView(new HeaderView(getContext(), "Groups I manage"));
        groupsUserJoined.addView(new HeaderView(getContext(), "Groups I Joined"));

    }
// todo can be optimized by caching locally the data
    private void loadGroupData() {
        groupsViewModel.getUserCreatedGroups(userCreatedGroupsCallback);
        groupsViewModel.getUserJoinedGroups(userJoinedGroupsCallback);
    }
    UserCreatedGroupsCallback userCreatedGroupsCallback =new UserCreatedGroupsCallback() {
        @Override
        public void createdGroupsOnCallbackSuccess(List<Group> list) {
            createExpandablePlaceHolderViews();

            for (Group group : list) {
                createdGroupsPlaceHolderView.addView(new GroupNameView(getContext(), group));
            }

        }

        @Override
        public void createdGroupsOnCallbackNoResults() {
            //todo dumb way of handling
//            createExpandablePlaceHolderViews();
//            Group testGroup=new Group();
//            testGroup.setGroupName("No managed groups");
//            createdGroupsPlaceHolderView.addView(new GroupNameView(getContext(), testGroup));
        }
    };
UserJoinedGroupsCallback userJoinedGroupsCallback=new UserJoinedGroupsCallback() {
    @Override
    public void joinedGroupsOnCallbackSuccess(List<Group> list) {

    }

    @Override
    public void joinedGroupsOnCallbackNoResults() {

    }
};

}