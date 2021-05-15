package com.viauc.igift.ui.newitem;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.viauc.igift.R;
import com.viauc.igift.model.WishItem;
import com.viauc.igift.model.WishList;

public class NewItemFragment extends Fragment {

    private View view;
    private NewItemViewModel newItemViewModel;

    private WishList wishList;
    private EditText itemNameEditText;
    private EditText itemPriceEditText;
    private EditText itemWhereToBuyEditText;
    private EditText itemDescriptionEditText;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_new_item, container, false);
        newItemViewModel = new ViewModelProvider(this).get(NewItemViewModel.class);

        itemNameEditText = view.findViewById(R.id.itemName);
        itemPriceEditText = view.findViewById(R.id.priceEditText);
        itemWhereToBuyEditText = view.findViewById(R.id.whereToBuyEditText);
        itemDescriptionEditText = view.findViewById(R.id.descriptionEditText);

        Button button = view.findViewById(R.id.createWishItemButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createWishItem();
            }
        });
        return view;
    }

    private void createWishItem() {
        String itemName = itemNameEditText.getText().toString().trim();
        double itemPrice = Double.parseDouble(itemPriceEditText.getText().toString().trim());
        String itemWhereToBuy = itemWhereToBuyEditText.getText().toString().trim();
        String itemDescription = itemDescriptionEditText.getText().toString().trim();


        if(validateItemName(itemName)){
            WishItem wishItem=new WishItem(itemName,itemPrice,itemWhereToBuy,itemDescription);
            newItemViewModel.addNewWishItem(wishList,wishItem);
        }
    }

    private boolean validateItemName(String itemName) {
        Pair<Boolean, String> response=   newItemViewModel.validateItemName(itemName);
        if(!response.first){
            itemNameEditText.setError(response.second);
            itemNameEditText.requestFocus();
        return false;
        }
        return true;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        NewItemFragmentArgs newItemFragmentArgs = NewItemFragmentArgs.fromBundle(getArguments());
        wishList = newItemFragmentArgs.getWishList();

    }
}