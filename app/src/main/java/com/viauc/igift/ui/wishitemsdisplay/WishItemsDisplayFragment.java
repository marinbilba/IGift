package com.viauc.igift.ui.wishitemsdisplay;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.viauc.igift.R;
import com.viauc.igift.model.WishItem;
import com.viauc.igift.ui.wishitems.WishItemsFragmentArgs;

import java.util.ArrayList;

public class WishItemsDisplayFragment extends Fragment {

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
        view = inflater.inflate(R.layout.fragment_wish_items_display, container, false);

        wishItemsDisplayViewModel = new ViewModelProvider(this).get(WishItemsDisplayViewModel.class);

        itemNameTextView = view.findViewById(R.id.giftNameTextView);
        itemPriceTextView = view.findViewById(R.id.priceTextView);
        itemWhereToBuyTextView = view.findViewById(R.id.whereToBuyTextView);
        itemDescriptionTextView = view.findViewById(R.id.descriptionTextView);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        WishItemsDisplayFragmentArgs wishItemsDisplayFragmentArgs = WishItemsDisplayFragmentArgs.fromBundle(getArguments());
        wishItem = wishItemsDisplayFragmentArgs.getWishItem();
        itemNameTextView.setText(wishItem.getGiftName());
        itemPriceTextView.setText(String.valueOf(wishItem.getPrice()));
        itemWhereToBuyTextView.setText(wishItem.getWhereToBuy());
        itemDescriptionTextView.setText(wishItem.getDescription());

    }

}