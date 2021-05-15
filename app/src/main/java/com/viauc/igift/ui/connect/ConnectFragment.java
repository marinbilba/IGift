package com.viauc.igift.ui.connect;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.viauc.igift.R;

public class ConnectFragment extends Fragment {

    private ConnectViewModel groupsViewModel;
    View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        groupsViewModel =
                new ViewModelProvider(this).get(ConnectViewModel.class);
        view = inflater.inflate(R.layout.fragment_connect, container, false);

        TableRow startGroupRow = (TableRow) view.findViewById(R.id.startGroupRow);

        startGroupRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_navigation_connect_to_newGroupFragment);
            }
        });
        TableRow joinGroupRow = (TableRow) view.findViewById(R.id.joinGroupRow);

        joinGroupRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_navigation_connect_to_joinGroupFragment);
            }
        });
        return view;
    }
    public void createGroup(View view){



//        // Get the FragmentManager.
//        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//        // Check to see if the fragment is already showing.
//        NewGroupFragment simpleFragment = (NewGroupFragment) fragmentManager
//                .findFragmentById(R.id.create_group_fragment_container);
//        if (simpleFragment != null) {
//            // Create and commit the transaction to remove the fragment.
//            FragmentTransaction fragmentTransaction =
//                    fragmentManager.beginTransaction();
//            fragmentTransaction.remove(simpleFragment).commit();
        }




    }

