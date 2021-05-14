package com.viauc.igift.ui.groupmembers;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.mindorks.placeholderview.ExpandablePlaceHolderView;
import com.viauc.igift.R;
import com.viauc.igift.model.Group;
import com.viauc.igift.ui.connect.joingroup.JoinGroupFragmentArgs;
import com.viauc.igift.ui.groups.GroupsViewModel;

public class GroupMembersFragment extends Fragment {
    GroupMembersViewModel groupMembersViewModel;
    View view;
    RecyclerView recyclerView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        groupMembersViewModel =
                new ViewModelProvider(this).get(GroupMembersViewModel.class);
         view = inflater.inflate(R.layout.fragment_group_members, container, false);
        recyclerView = view.findViewById(R.id.allGroupMembersRecyclerView);

        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        GroupMembersFragmentArgs groupMembersFragmentArgs =  GroupMembersFragmentArgs.fromBundle(getArguments());
      Group group= groupMembersFragmentArgs.getGroup();

        Log.d("G", "onViewCreated: ");
    }
}
