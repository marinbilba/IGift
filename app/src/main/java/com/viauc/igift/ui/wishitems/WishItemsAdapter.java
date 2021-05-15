package com.viauc.igift.ui.wishitems;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.viauc.igift.R;
import com.viauc.igift.data.callbacks.OnRecyclerViewPositionClickListener;
import com.viauc.igift.model.WishItem;
import com.viauc.igift.model.WishList;
import com.viauc.igift.ui.mylists.MyListAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class WishItemsAdapter extends RecyclerView.Adapter<WishItemsAdapter.WishItemsViewHolder>{
    private ArrayList<WishItem> wishItems;
    private Context context;
    private final OnRecyclerViewPositionClickListener onRecyclerViewPositionClickListener;

    public WishItemsAdapter(ArrayList<WishItem> wishItems, Context context, OnRecyclerViewPositionClickListener onRecyclerViewPositionClickListener) {
        this.wishItems = wishItems;
        this.context = context;
        this.onRecyclerViewPositionClickListener = onRecyclerViewPositionClickListener;
    }

    @NonNull
    @NotNull
    @Override
    public WishItemsViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_raw_layout, parent, false);
        return new WishItemsAdapter.WishItemsViewHolder(view, onRecyclerViewPositionClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull WishItemsViewHolder holder, int position) {
        holder.wishItemName.setText(wishItems.get(position).getGiftName());

    }

    @Override
    public int getItemCount() {
        return wishItems.size();
    }

    public static class WishItemsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView wishItemName;
        ConstraintLayout constraintLayout;
        OnRecyclerViewPositionClickListener onRecyclerViewPositionClickListener;

        public WishItemsViewHolder(@NonNull View itemView, OnRecyclerViewPositionClickListener listener) {
            super(itemView);
            constraintLayout=itemView.findViewById(R.id.my_raw_ConstraintLayout);
            wishItemName = itemView.findViewById(R.id.list_name_raw_text_view);
            constraintLayout.setOnClickListener(this);
            onRecyclerViewPositionClickListener = listener;


        }

        @Override
        public void onClick(View v) {
            onRecyclerViewPositionClickListener.onRecyclerViewPositionCallback(getAbsoluteAdapterPosition());
        }
    }
}
