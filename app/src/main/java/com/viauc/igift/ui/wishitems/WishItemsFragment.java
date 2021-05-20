package com.viauc.igift.ui.wishitems;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.viauc.igift.R;
import com.viauc.igift.data.callbacks.OnDeleteRawCallback;
import com.viauc.igift.data.callbacks.OnRecyclerViewPositionClickListener;
import com.viauc.igift.model.WishItem;
import com.viauc.igift.model.WishList;

import java.util.ArrayList;

public class WishItemsFragment extends Fragment {

    private WishItemsViewModel wishItemsViewModel;
    private View view;
    private ArrayList<WishItem> wishItems;
    private RecyclerView recyclerView;
    private WishList wishList;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_my_wish_items, container, false);
        wishItemsViewModel = new ViewModelProvider(this).get(WishItemsViewModel.class);
        recyclerView = view.findViewById(R.id.wishItemsRecyclerView);
        FloatingActionButton floatingActionButton = view.findViewById(R.id.addItemFloatingActionButton);
        floatingActionButton.setOnClickListener(v -> {
            navigateToCreateNewItem();

        });
        return view;
    }

    private void inflateRecyclerView(WishList wishList) {
        wishItems = (ArrayList<WishItem>) wishList.getWishItemsList();
                if (!wishItems.isEmpty()) {
            WishItemsAdapter myAdapter = new WishItemsAdapter(wishItems, getContext(),onDeleteRawCallback, onRecyclerViewPositionClickListener);
            recyclerView.setAdapter(myAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }
    }



    private void navigateToCreateNewItem() {
        WishItemsFragmentDirections.ActionWishItemsFragmentToNewItemFragment action =
                WishItemsFragmentDirections.actionWishItemsFragmentToNewItemFragment(wishList);
        Navigation.findNavController(view).navigate(action);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        WishItemsFragmentArgs wishItemsFragmentArgs = WishItemsFragmentArgs.fromBundle(getArguments());
        wishList = wishItemsFragmentArgs.getWishList();
        wishItemsViewModel.getWishItemsOfWishList(wishList.getListName()).observe(getViewLifecycleOwner(), this::inflateRecyclerView);

    }


    OnRecyclerViewPositionClickListener onRecyclerViewPositionClickListener = new OnRecyclerViewPositionClickListener() {
        @Override
        public void onRecyclerViewPositionCallback(int position) {
            WishItem wishItem = wishItems.get(position);
            WishItemsFragmentDirections.ActionWishItemsFragmentToWishItemsDisplay action =
                    WishItemsFragmentDirections.actionWishItemsFragmentToWishItemsDisplay(wishItem);
            Navigation.findNavController(view).navigate(action);
        }
    };
    OnDeleteRawCallback onDeleteRawCallback=new OnDeleteRawCallback() {
        @Override
        public void deleteRaw(int position) {
            WishItem wishItem=wishItems.get(position);
            wishItemsViewModel.deleteWishItem(wishList.getListName(),wishItem);
        }
    };
}