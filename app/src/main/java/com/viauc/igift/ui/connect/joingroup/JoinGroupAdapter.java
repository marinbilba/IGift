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
import com.viauc.igift.data.callbacks.OnJoinGroupClickListener;
import com.viauc.igift.model.Group;

import org.jetbrains.annotations.NotNull;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class JoinGroupAdapter extends RecyclerView.Adapter<JoinGroupAdapter.JoinGroupViewHolder> {
    ArrayList<Group> groups;
    Context context;
    private final OnJoinGroupClickListener onJoinGroupClickListener;

    public JoinGroupAdapter(ArrayList<Group> groups, OnJoinGroupClickListener onJoinGroupClickListener, Context context) {
        this.groups = groups;
        this.context = context;
        this.onJoinGroupClickListener = onJoinGroupClickListener;
    }

    @NonNull
    @NotNull
    @Override
    public JoinGroupViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.groups_name_join_layout, parent, false);
        return new JoinGroupViewHolder(view, onJoinGroupClickListener);
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
        OnJoinGroupClickListener onJoinGroupClickListener;

        public JoinGroupViewHolder(@NonNull View itemView, OnJoinGroupClickListener listener) {
            super(itemView);
            joinGroupButton = itemView.findViewById(R.id.joinGroupButton);
            groupName = itemView.findViewById(R.id.group_name);
            joinGroupButton.setOnClickListener(this);
            onJoinGroupClickListener = listener;


        }

        @Override
        public void onClick(View v) {
            onJoinGroupClickListener.onGroupPositionClicked(getAbsoluteAdapterPosition());
        }


    }
}
