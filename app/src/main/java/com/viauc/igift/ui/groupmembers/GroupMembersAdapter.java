package com.viauc.igift.ui.groupmembers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.viauc.igift.R;
import com.viauc.igift.data.callbacks.OnGroupMemberClickListener;
import com.viauc.igift.data.callbacks.OnRecyclerViewPositionClickListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class GroupMembersAdapter  extends RecyclerView.Adapter<GroupMembersAdapter.GroupMembersViewHolder>{

    ArrayList<String> membersEmail;
    Context context;
    private final OnRecyclerViewPositionClickListener onRecyclerViewPositionClickListener;

    public GroupMembersAdapter(ArrayList<String> membersEmail, Context context, OnRecyclerViewPositionClickListener onRecyclerViewPositionClickListener) {
        this.membersEmail = membersEmail;
        this.context = context;
        this.onRecyclerViewPositionClickListener = onRecyclerViewPositionClickListener;
    }

    @NonNull
    @NotNull
    @Override
    public GroupMembersViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.group_members_raw_layout, parent, false);
        return new GroupMembersAdapter.GroupMembersViewHolder(view, onRecyclerViewPositionClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull GroupMembersViewHolder holder, int position) {
        holder.userEmail.setText(membersEmail.get(position));
    }

    @Override
    public int getItemCount() {
        return membersEmail.size();
    }

    public static class GroupMembersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView userEmail;
        Button seeWishList;
        OnRecyclerViewPositionClickListener onGroupMemberClickListener;

        public GroupMembersViewHolder(@NonNull View itemView, OnRecyclerViewPositionClickListener listener) {
            super(itemView);
            seeWishList = itemView.findViewById(R.id.seeWishListButton);
            userEmail = itemView.findViewById(R.id.groupMemberEmailAddress);
            seeWishList.setOnClickListener(this);
            onGroupMemberClickListener = listener;


        }

        @Override
        public void onClick(View v) {
            onGroupMemberClickListener.onRecyclerViewPositionCallback(getAbsoluteAdapterPosition());
        }
    }
    }

