package com.viauc.igift.ui.wishitemdisplay;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.viauc.igift.R;
import com.viauc.igift.model.WishItem;

public class WishItemDisplayFragment extends Fragment {

    private WishItemsDisplayViewModel wishItemsDisplayViewModel;
    private View view;
    private WishItem wishItem;
    private TextView itemNameTextView;
    private TextView itemPriceTextView;
    private TextView itemWhereToBuyTextView;
    private TextView itemDescriptionTextView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_wish_item_display, container, false);

        wishItemsDisplayViewModel = new ViewModelProvider(this).get(WishItemsDisplayViewModel.class);

        itemNameTextView = view.findViewById(R.id.giftNameTextView);
        itemPriceTextView = view.findViewById(R.id.priceTextView);
        itemWhereToBuyTextView = view.findViewById(R.id.whereToBuyTextView);
        itemDescriptionTextView = view.findViewById(R.id.descriptionTextView);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        WishItemDisplayFragmentArgs wishItemsDisplayFragmentArgs = WishItemDisplayFragmentArgs.fromBundle(getArguments());
        wishItem = wishItemsDisplayFragmentArgs.getWishItem();
        itemNameTextView.setText(wishItem.getGiftName());
        itemPriceTextView.setText(String.valueOf(wishItem.getPrice()));
        itemWhereToBuyTextView.setText(wishItem.getWhereToBuy());
        itemDescriptionTextView.setText(wishItem.getDescription());

    }

}