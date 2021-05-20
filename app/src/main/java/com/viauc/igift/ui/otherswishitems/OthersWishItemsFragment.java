package com.viauc.igift.ui.otherswishitems;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.viauc.igift.R;
import com.viauc.igift.data.callbacks.OnDeleteRawCallback;
import com.viauc.igift.data.callbacks.OnRecyclerViewPositionClickListener;
import com.viauc.igift.model.WishItem;
import com.viauc.igift.model.WishList;
import com.viauc.igift.ui.wishitems.WishItemsFragmentArgs;
import com.viauc.igift.ui.wishitems.WishItemsFragmentDirections;

import java.util.ArrayList;

public class OthersWishItemsFragment extends Fragment {

    private OthersWishItemsViewModel wishItemsViewModel;
    private View view;
    private ArrayList<WishItem> wishItems;
    private RecyclerView recyclerView;
    private WishList wishList;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_others_wish_items, container, false);
        wishItemsViewModel = new ViewModelProvider(this).get(OthersWishItemsViewModel.class);
        recyclerView = view.findViewById(R.id.wishItemsRecyclerView);
        return view;
    }


    //todo Wish list live data is a better choice
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        OthersWishItemsFragmentArgs othersWishItemsFragmentArgs = OthersWishItemsFragmentArgs.fromBundle(getArguments());
        wishList = othersWishItemsFragmentArgs.getWishList();
        wishItems = (ArrayList<WishItem>) wishList.getWishItemsList();
        inflateRecyclerView();

    }

    private void inflateRecyclerView() {
        if (!wishItems.isEmpty()) {
            OthersWishItemsAdapter myAdapter = new OthersWishItemsAdapter(wishItems, getContext(), onRecyclerViewPositionClickListener);
            recyclerView.setAdapter(myAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }
    }

    OnRecyclerViewPositionClickListener onRecyclerViewPositionClickListener = new OnRecyclerViewPositionClickListener() {
        @Override
        public void onRecyclerViewPositionCallback(int position) {
            WishItem wishItem = wishItems.get(position);
            OthersWishItemsFragmentDirections.ActionOthersWishItemsFragmentToWishItemsDisplay action =
                    OthersWishItemsFragmentDirections.actionOthersWishItemsFragmentToWishItemsDisplay(wishItem);
            Navigation.findNavController(view).navigate(action);
        }
    };

}