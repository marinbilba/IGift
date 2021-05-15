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

public class WishListDisplayFragment extends Fragment {

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
        WishListDisplayFragmentArgs wishItemsFragmentArgs = WishListDisplayFragmentArgs.fromBundle(getArguments());
        String userEmail = wishItemsFragmentArgs.getUserEmail();
        wishListsDisplayViewModel.getUserWishLists(fetchWishListCallback, userEmail);

    }

    private void inflateRecyclerView() {
        if (!wishLists.isEmpty()) {
            MyListAdapter myAdapter = new MyListAdapter(wishLists, getContext(), onRecyclerViewPositionClickListener);
            recyclerView.setAdapter(myAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }
    }

    OnRecyclerViewPositionClickListener onRecyclerViewPositionClickListener = new OnRecyclerViewPositionClickListener() {
        @Override
        public void onRecyclerViewPositionCallback(int position) {
            WishList wishList = wishLists.get(position);
            WishListDisplayFragmentDirections.ActionWishListDisplayFragmentToWishItemsFragment action =
                    WishListDisplayFragmentDirections.actionWishListDisplayFragmentToWishItemsFragment(wishList);
            Navigation.findNavController(view).navigate(action);
        }
    };
    FetchWishListCallback fetchWishListCallback = new FetchWishListCallback() {
        @Override
        public void fetchedWishListOnSuccess(List<WishList> wishListList) {
            wishLists = (ArrayList<WishList>) wishListList;
            inflateRecyclerView();
        }
    };
}
