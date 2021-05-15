package com.viauc.igift.ui.connect.joingroup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.viauc.igift.R;
import com.viauc.igift.data.callbacks.OnRecyclerViewPositionClickListener;
import com.viauc.igift.model.Group;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class JoinGroupAdapter extends RecyclerView.Adapter<JoinGroupAdapter.JoinGroupViewHolder> {
    ArrayList<Group> groups;
    Context context;
    private final OnRecyclerViewPositionClickListener onRecyclerViewPositionClickListener;

    public JoinGroupAdapter(ArrayList<Group> groups, OnRecyclerViewPositionClickListener onRecyclerViewPositionClickListener, Context context) {
        this.groups = groups;
        this.context = context;
        this.onRecyclerViewPositionClickListener = onRecyclerViewPositionClickListener;
    }

    @NonNull
    @NotNull
    @Override
    public JoinGroupViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.groups_name_join_raw_layout, parent, false);
        return new JoinGroupViewHolder(view, onRecyclerViewPositionClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull JoinGroupViewHolder holder, int position) {
        holder.groupName.setText(groups.get(position).getGroupName());

    }

    @Override
    public int getItemCount() {
        return groups.size();
    }


    public static class JoinGroupViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView groupName;
        Button joinGroupButton;
        OnRecyclerViewPositionClickListener onRecyclerViewPositionClickListener;

        public JoinGroupViewHolder(@NonNull View itemView, OnRecyclerViewPositionClickListener listener) {
            super(itemView);
            joinGroupButton = itemView.findViewById(R.id.joinGroupButton);
            groupName = itemView.findViewById(R.id.group_name);
            joinGroupButton.setOnClickListener(this);
            onRecyclerViewPositionClickListener = listener;


        }

        @Override
        public void onClick(View v) {
            onRecyclerViewPositionClickListener.onRecyclerViewPositionCallback(getAbsoluteAdapterPosition());
        }
    }
}
