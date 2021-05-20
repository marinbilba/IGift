package com.viauc.igift.ui.otherswishitems;

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

public class OthersWishItemsAdapter extends RecyclerView.Adapter<OthersWishItemsAdapter.OthersWishItemsViewHolder> {
    private final ArrayList<WishItem> wishItems;
    private final Context context;
    private final OnRecyclerViewPositionClickListener onRecyclerViewPositionClickListener;

    public OthersWishItemsAdapter(ArrayList<WishItem> wishItems, Context context, OnRecyclerViewPositionClickListener onRecyclerViewPositionClickListener) {
        this.wishItems = wishItems;
        this.context = context;
        this.onRecyclerViewPositionClickListener = onRecyclerViewPositionClickListener;
    }

    @NonNull
    @NotNull
    @Override
    public OthersWishItemsViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_raw_layout, parent, false);
        return new OthersWishItemsViewHolder(view, onRecyclerViewPositionClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull OthersWishItemsViewHolder holder, int position) {
        holder.wishItemName.setText(wishItems.get(position).getGiftName());

    }

    @Override
    public int getItemCount() {
        return wishItems.size();
    }

    public static class OthersWishItemsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView wishItemName;
        ConstraintLayout constraintLayout;

        OnRecyclerViewPositionClickListener onRecyclerViewPositionClickListener;

        public OthersWishItemsViewHolder(@NonNull View itemView, OnRecyclerViewPositionClickListener listener) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.my_raw_ConstraintLayout);
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
