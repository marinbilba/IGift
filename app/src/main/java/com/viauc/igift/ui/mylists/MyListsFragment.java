package com.viauc.igift.ui.mylists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.viauc.igift.R;
import com.viauc.igift.data.callbacks.FetchWishListCallback;
import com.viauc.igift.data.callbacks.OnRecyclerViewPositionClickListener;
import com.viauc.igift.model.WishList;

import java.util.ArrayList;
import java.util.List;

public class MyListsFragment extends Fragment {

    private MyListsViewModel myListsViewModel;
    private View view;
    private ArrayList<WishList> wishLists;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myListsViewModel = new ViewModelProvider(this).get(MyListsViewModel.class);
        view = inflater.inflate(R.layout.fragment_my_list, container, false);
        recyclerView = view.findViewById(R.id.wishListRecyclerView);


        FloatingActionButton floatingActionButton = view.findViewById(R.id.addListFloatingActionButton);
        floatingActionButton.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_navigation_my_list_to_newListFragment);

        });
        loadUserWishLists();
        return view;
    }

    private void loadUserWishLists() {
        myListsViewModel.getUserWishLists(fetchWishListCallback);
    }

    private void inflateRecyclerView() {
        if (!wishLists.isEmpty()) {
            MyListAdapter myAdapter = new MyListAdapter(wishLists, getContext(), onRecyclerViewPositionClickListener);
            recyclerView.setAdapter(myAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }

    }

    FetchWishListCallback fetchWishListCallback = new FetchWishListCallback() {
        @Override
        public void fetchedWishListOnSuccess(List<WishList> wishListList) {
            wishLists = (ArrayList<WishList>) wishListList;
            inflateRecyclerView();
        }
    };

    OnRecyclerViewPositionClickListener onRecyclerViewPositionClickListener=new OnRecyclerViewPositionClickListener() {
        @Override
        public void onRecyclerViewPositionCallback(int position) {
            WishList wishList=wishLists.get(position);
            MyListsFragmentDirections.ActionNavigationMyListToWishItemsFragment action =
                    MyListsFragmentDirections.actionNavigationMyListToWishItemsFragment(wishList);
            Navigation.findNavController(view).navigate(action);
        }
    };

}