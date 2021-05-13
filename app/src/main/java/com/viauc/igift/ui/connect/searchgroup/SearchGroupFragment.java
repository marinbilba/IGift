package com.viauc.igift.ui.connect.searchgroup;

import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.viauc.igift.R;
import com.viauc.igift.data.callbacks.CreateGroupCallback;
import com.viauc.igift.model.Group;

import java.util.List;

public class SearchGroupFragment extends Fragment {

    private SearchGroupViewModel searchGroupViewModel;
    View view;
    String searchUserEmail;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        searchGroupViewModel =
                new ViewModelProvider(this).get(SearchGroupViewModel.class);
        view = inflater.inflate(R.layout.fragment_search_group, container, false);

        EditText userEmailToSearch = view.findViewById(R.id.inputTextFindGroupName);

        Button searchGroupButton = (Button) view.findViewById(R.id.searchGroup);
        searchGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 searchUserEmail = userEmailToSearch.getText().toString().trim();
                Pair<Boolean, String> response = searchGroupViewModel.validateJoinGroupNameInputField(searchUserEmail);
                if (!response.first) {
                    userEmailToSearch.setError(response.second);
                    userEmailToSearch.requestFocus();
                }else{
                    searchGroupViewModel.getUserCreatedGroupsByEmail(createGroupCallback, searchUserEmail);
                }
            }
        });

        return view;
    }
    CreateGroupCallback createGroupCallback =new CreateGroupCallback() {
        @Override
        public void createdGroupsOnCallbackSuccess(List<Group> list) {
            SearchGroupFragmentDirections.ActionSearchGroupFragmentToJoinGroupFragment action =
                    SearchGroupFragmentDirections.actionSearchGroupFragmentToJoinGroupFragment(searchUserEmail);
            Navigation.findNavController(view).navigate(action);
        }

        @Override
        public void createdGroupsOnCallbackNoResults() {
            Toast.makeText(view.getContext(), "This User doesn't have any created groups.", Toast.LENGTH_SHORT).show();

        }
    };

}
