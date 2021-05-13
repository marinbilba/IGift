package com.viauc.igift.ui.groups;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.mindorks.placeholderview.ExpandablePlaceHolderView;
import com.viauc.igift.R;
import com.viauc.igift.data.callbacks.CreateGroupCallback;
import com.viauc.igift.model.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupsFragment extends Fragment  {

    private GroupsViewModel groupsViewModel;
    private ExpandablePlaceHolderView createdGroupsPlaceHolderView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        groupsViewModel =
                new ViewModelProvider(this).get(GroupsViewModel.class);
        View view = inflater.inflate(R.layout.fragment_groups, container, false);
        createdGroupsPlaceHolderView = (ExpandablePlaceHolderView) view.findViewById(R.id.expandableCreatedGroupsPlaceholder);
        createdGroupsPlaceHolderView.addView(new HeaderView(getContext(), "Groups I manage"));

        loadGroupData();
        return view;
    }
    RecyclerView recyclerView;
    private void loadGroupData() {
        groupsViewModel.getUserCreatedGroups(createGroupCallback);
    }
    CreateGroupCallback createGroupCallback =new CreateGroupCallback() {
        @Override
        public void createdGroupsOnCallbackSuccess(List<Group> list) {
            ArrayList<String> gr=new ArrayList();

            for (Group group : list) {
                gr.add(group.groupName);

            }
            createdGroupsPlaceHolderView.addView(new GroupsAdapter(gr,getContext()));

        }

        @Override
        public void createdGroupsOnCallbackNoResults() {
//            Group testGroup=new Group();
//            testGroup.setGroupName("No managed groups");
//            createdGroupsPlaceHolderView.addView(new GroupNameView(getContext(), testGroup));

        }
    };


}