package com.viauc.igift.ui.connect.creategroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.viauc.igift.R;

public class CreateGroupFragment extends Fragment {
    CreateGroupViewModel createGroupViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//todo don't allow same user to create group with the same name
        View view = inflater.inflate(R.layout.fragment_create_group, container, false);

        EditText groupNameEditText = view.findViewById(R.id.inputTextCreateGroupName);

        createGroupViewModel =
                new ViewModelProvider(this).get(CreateGroupViewModel.class);

        Button createGroupButton=(Button) view.findViewById(R.id.createGroupButton);
        createGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String groupName = groupNameEditText.getText().toString().trim();
                Pair<Boolean, String> response=createGroupViewModel.validateGroupNameInputField(groupName);
                if(!response.first){
                    groupNameEditText.setError(response.second);
                    groupNameEditText.requestFocus();
                }else{
                    createGroupViewModel.createGroup(groupName);
                    //todo Check if group was actually created before going to a new view
                    //todo check for group name duplicates
                    Navigation.findNavController(v).navigate(R.id.action_newGroupFragment_to_navigation_groups);
                }


            }
        });

        return view;

    }

}








