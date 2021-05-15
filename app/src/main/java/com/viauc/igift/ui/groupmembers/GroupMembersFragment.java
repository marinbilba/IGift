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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mindorks.placeholderview.ExpandablePlaceHolderView;
import com.viauc.igift.R;
import com.viauc.igift.data.callbacks.OnRecyclerViewPositionClickListener;
import com.viauc.igift.model.Group;
import com.viauc.igift.ui.connect.joingroup.JoinGroupAdapter;
import com.viauc.igift.ui.connect.joingroup.JoinGroupFragmentArgs;
import com.viauc.igift.ui.groups.GroupsViewModel;

import java.util.ArrayList;

public class GroupMembersFragment extends Fragment {
    GroupMembersViewModel groupMembersViewModel;
    View view;
    RecyclerView recyclerView;
    ArrayList<String> connectedUsersEmail;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        groupMembersViewModel =
                new ViewModelProvider(this).get(GroupMembersViewModel.class);
         view = inflater.inflate(R.layout.fragment_group_members, container, false);
        recyclerView = view.findViewById(R.id.allGroupMembersRecyclerView);
        return view;
    }

    private void inflateRecyclerView() {
        GroupMembersAdapter myAdapter = new GroupMembersAdapter(connectedUsersEmail,getContext() ,onRecyclerViewPositionClickListener );
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        GroupMembersFragmentArgs groupMembersFragmentArgs =  GroupMembersFragmentArgs.fromBundle(getArguments());
      Group group= groupMembersFragmentArgs.getGroup();
      if(group.getUsersConnected()!=null){
          connectedUsersEmail=group.getUsersConnected();
          inflateRecyclerView();
      }
    }
    OnRecyclerViewPositionClickListener onRecyclerViewPositionClickListener=new OnRecyclerViewPositionClickListener() {
        @Override
        public void onRecyclerViewPositionCallback(int position) {
      String userEmail=connectedUsersEmail.get(position);
        }
    };
}
