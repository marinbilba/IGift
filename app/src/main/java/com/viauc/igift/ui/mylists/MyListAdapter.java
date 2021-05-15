package com.viauc.igift.ui.mylists;

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
import com.viauc.igift.model.WishList;
import com.viauc.igift.ui.connect.joingroup.JoinGroupAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.MyListAdapterViewHolder>{
private ArrayList<WishList> wishLists;
    private Context context;
    private final OnRecyclerViewPositionClickListener onRecyclerViewPositionClickListener;

    public MyListAdapter(ArrayList<WishList> wishLists, Context context, OnRecyclerViewPositionClickListener onRecyclerViewPositionClickListener) {
        this.wishLists = wishLists;
        this.context = context;
        this.onRecyclerViewPositionClickListener = onRecyclerViewPositionClickListener;
    }

    @NonNull
    @NotNull
    @Override
    public MyListAdapterViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_wish_list_raw_layout, parent, false);
        return new MyListAdapter.MyListAdapterViewHolder(view, onRecyclerViewPositionClickListener);
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
        OnRecyclerViewPositionClickListener onRecyclerViewPositionClickListener;

        public MyListAdapterViewHolder(@NonNull View itemView, OnRecyclerViewPositionClickListener listener) {
            super(itemView);
            constraintLayout=itemView.findViewById(R.id.wish_list_name_ConstraintLayout);
            wishListName = itemView.findViewById(R.id.wish_list_name);
            constraintLayout.setOnClickListener(this);
            onRecyclerViewPositionClickListener = listener;


        }

        @Override
        public void onClick(View v) {
            onRecyclerViewPositionClickListener.onRecyclerViewPositionCallback(getAbsoluteAdapterPosition());
        }
    }
}
