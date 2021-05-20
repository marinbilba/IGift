package com.viauc.igift.ui.mylists;

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
import com.viauc.igift.model.WishList;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.MyListAdapterViewHolder> {
    private ArrayList<WishList> wishLists;
    private Context context;
    private final OnRecyclerViewPositionClickListener onRecyclerViewPositionClickListener;
    private final OnDeleteRawCallback onDeleteRawCallback;

    public MyListAdapter(ArrayList<WishList> wishLists, Context context, OnDeleteRawCallback onDeleteRawCallback, OnRecyclerViewPositionClickListener onRecyclerViewPositionClickListener) {
        this.wishLists = wishLists;
        this.context = context;
        this.onRecyclerViewPositionClickListener = onRecyclerViewPositionClickListener;
        this.onDeleteRawCallback = onDeleteRawCallback;

    }

    @NonNull
    @NotNull
    @Override
    public MyListAdapterViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_raw_with_delete_layout, parent, false);
        return new MyListAdapter.MyListAdapterViewHolder(view, onDeleteRawCallback, onRecyclerViewPositionClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyListAdapterViewHolder holder, int position) {
        holder.wishListName.setText(wishLists.get(position).getListName());

    }

    @Override
    public int getItemCount() {
        return wishLists.size();
    }

    public static class MyListAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView wishListName;
        ConstraintLayout constraintLayout;
        ImageView deleteWishListImageView;

        OnRecyclerViewPositionClickListener onRecyclerViewPositionClickListener;
        OnDeleteRawCallback onDeleteRawCallback;

        public MyListAdapterViewHolder(@NonNull View itemView, OnDeleteRawCallback onDeleteRawCallback, OnRecyclerViewPositionClickListener listener) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.my_raw_ConstraintLayout);
            wishListName = itemView.findViewById(R.id.list_name_raw_text_view);
            deleteWishListImageView = itemView.findViewById(R.id.deleteRawImageView);
            deleteWishListImageView.setOnClickListener(this);
            constraintLayout.setOnClickListener(this);

            onRecyclerViewPositionClickListener = listener;
            this.onDeleteRawCallback = onDeleteRawCallback;


        }

        @Override
        public void onClick(View v) {
            if(v==constraintLayout){
                onRecyclerViewPositionClickListener.onRecyclerViewPositionCallback(getAbsoluteAdapterPosition());
            }
            if(v==deleteWishListImageView){
                onDeleteRawCallback.deleteRaw(getAbsoluteAdapterPosition());
            }

        }
    }
}
