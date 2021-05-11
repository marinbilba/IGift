package com.viauc.igift.ui.groups;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.mindorks.placeholderview.ExpandablePlaceHolderView;
import com.viauc.igift.R;
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
        expandablePlaceHolderView.addView(new HeaderView(getContext(), "My groups"));


        groupsViewModel.getUserCreatedGroups().observe(getViewLifecycleOwner(), new Observer<List<Group>>() {
            @Override
            public void onChanged(List<Group> groups) {
                if (!groups.isEmpty()) {
                    userCreatedGroups = (ArrayList<Group>) groups;
                    for (Group group : groups) {
                        expandablePlaceHolderView.addView(new GroupNameView(getContext(), group));
                    }
                }
            }
        });

        return view;
    }


}