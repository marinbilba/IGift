package com.viauc.igift.ui.newlist;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.viauc.igift.R;
import com.viauc.igift.data.callbacks.CreateWishListCallback;
import com.viauc.igift.ui.mylists.WishListsDisplayFragmentDirections;

public class NewListFragment extends Fragment {

    private NewListViewModel newListViewModel;
    private View view;
    private TextView newListNameTextView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_new_list, container, false);
        Button createNewListButton = view.findViewById(R.id.createNewListButton);
        newListNameTextView =view.findViewById(R.id.inputTextCreateNewListName);
        createNewListButton.setOnClickListener(v -> {
            createNewList();

        });
        return view;
    }

    private void createNewList() {
        String listName = newListNameTextView.getText().toString().trim();
        Pair<Boolean, String> response = newListViewModel.validateListNameInputField(listName);
        if (!response.first) {
            newListNameTextView.setError(response.second);
            newListNameTextView.requestFocus();
        } else {
            newListViewModel.createList(listName);
            Navigation.findNavController(view).navigate(R.id.action_newListFragment_to_navigation_my_list);

        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        newListViewModel = new ViewModelProvider(this).get(NewListViewModel.class);
    }


}