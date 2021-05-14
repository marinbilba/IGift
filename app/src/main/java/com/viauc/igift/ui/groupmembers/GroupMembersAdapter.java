package com.viauc.igift.ui.groupmembers;

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

public class GroupMembersAdapter  extends RecyclerView.Adapter<GroupMembersAdapter.GroupMembersViewHolder>{


    @NonNull
    @NotNull
    @Override
    public GroupMembersViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull GroupMembersViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class GroupMembersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView groupName;
            Button joinGroupButton;
            OnRecyclerViewPositionClickListener onGroupMemberClickListener;

            public GroupMembersViewHolder(@NonNull View itemView, OnRecyclerViewPositionClickListener listener) {
                super(itemView);
                joinGroupButton = itemView.findViewById(R.id.joinGroupButton);
                groupName = itemView.findViewById(R.id.group_name);
                joinGroupButton.setOnClickListener(this);
                onGroupMemberClickListener = listener;


            }

            @Override
            public void onClick(View v) {
                onGroupMemberClickListener.onRecyclerViewPositionCallback(getAbsoluteAdapterPosition());
            }
        }

    }

