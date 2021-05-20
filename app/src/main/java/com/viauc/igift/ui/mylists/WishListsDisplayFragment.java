package com.viauc.igift.ui.mylists;

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
import com.viauc.igift.data.callbacks.FetchWishListCallback;
import com.viauc.igift.data.callbacks.OnRecyclerViewPositionClickListener;
import com.viauc.igift.model.WishItem;
import com.viauc.igift.model.WishList;
import com.viauc.igift.ui.wishitems.WishItemsFragmentArgs;

import java.util.ArrayList;
import java.util.List;

public class WishListsDisplayFragment extends Fragment {

    private WishListsDisplayViewModel wishListsDisplayViewModel;
    private View view;
    private ArrayList<WishList> wishLists;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        wishListsDisplayViewModel = new ViewModelProvider(this).get(WishListsDisplayViewModel.class);
        view = inflater.inflate(R.layout.fragment_wish_list_display, container, false);
        recyclerView = view.findViewById(R.id.wishListRecyclerView);


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        WishListsDisplayFragmentArgs wishItemsFragmentArgs = WishListsDisplayFragmentArgs.fromBundle(getArguments());
        String userEmail = wishItemsFragmentArgs.getUserEmail();
        wishListsDisplayViewModel.getUserWishLists(userEmail).observe(getViewLifecycleOwner(), this::inflateRecyclerView);


    }

    private void inflateRecyclerView(ArrayList<WishList> wishLists) {
        if (!wishLists.isEmpty()) {
            this.wishLists=wishLists;
            WishListsDisplayAdapter myAdapter = new WishListsDisplayAdapter(wishLists, getContext(), onRecyclerViewPositionClickListener);
            recyclerView.setAdapter(myAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }
    }


    OnRecyclerViewPositionClickListener onRecyclerViewPositionClickListener = new OnRecyclerViewPositionClickListener() {
        @Override
        public void onRecyclerViewPositionCallback(int position) {
            WishList wishList = wishLists.get(position);
            WishListsDisplayFragmentDirections.ActionWishListDisplayFragmentToOthersWishItemsFragment action =
                    WishListsDisplayFragmentDirections.actionWishListDisplayFragmentToOthersWishItemsFragment(wishList);
            Navigation.findNavController(view).navigate(action);
        }
    };
}
