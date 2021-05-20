package com.viauc.igift.ui.wishitems;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.viauc.igift.R;
import com.viauc.igift.data.callbacks.OnDeleteRawCallback;
import com.viauc.igift.data.callbacks.OnRecyclerViewPositionClickListener;
import com.viauc.igift.model.WishItem;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class WishItemsAdapter extends RecyclerView.Adapter<WishItemsAdapter.WishItemsViewHolder> {
    private final ArrayList<WishItem> wishItems;
    private final Context context;
    private final OnRecyclerViewPositionClickListener onRecyclerViewPositionClickListener;
    private final OnDeleteRawCallback onDeleteRawCallback;

    public WishItemsAdapter(ArrayList<WishItem> wishItems, Context context, OnDeleteRawCallback onDeleteRawCallback, OnRecyclerViewPositionClickListener onRecyclerViewPositionClickListener) {
        this.wishItems = wishItems;
        this.context = context;
        this.onRecyclerViewPositionClickListener = onRecyclerViewPositionClickListener;
        this.onDeleteRawCallback = onDeleteRawCallback;
    }

    @NonNull
    @NotNull
    @Override
    public WishItemsViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_raw_with_delete_layout, parent, false);
        return new WishItemsAdapter.WishItemsViewHolder(view,onDeleteRawCallback, onRecyclerViewPositionClickListener);
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
        ImageView deleteWishItemImageView;

        OnRecyclerViewPositionClickListener onRecyclerViewPositionClickListener;
        OnDeleteRawCallback onDeleteRawCallback;

        public WishItemsViewHolder(@NonNull View itemView, OnDeleteRawCallback onDeleteRawCallback, OnRecyclerViewPositionClickListener listener) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.my_raw_ConstraintLayout);
            wishItemName = itemView.findViewById(R.id.list_name_raw_text_view);
            deleteWishItemImageView = itemView.findViewById(R.id.deleteRawImageView);

            constraintLayout.setOnClickListener(this);
            deleteWishItemImageView.setOnClickListener(this);

            onRecyclerViewPositionClickListener = listener;
            this.onDeleteRawCallback = onDeleteRawCallback;
        }

        @Override
        public void onClick(View v) {
            if (v == constraintLayout) {
                onRecyclerViewPositionClickListener.onRecyclerViewPositionCallback(getAbsoluteAdapterPosition());
            }

            if (v == deleteWishItemImageView) {
                onDeleteRawCallback.deleteRaw(getAbsoluteAdapterPosition());
            }
        }
    }
}
