package com.viauc.igift.ui.groups;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.viauc.igift.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class GroupsAdapter extends RecyclerView.Adapter<GroupsAdapter.JoinGroupViewHolder> {
    ArrayList<String> groupNames;
    Context context;

    public GroupsAdapter(ArrayList<String> groupNames, Context context) {
        this.groupNames = groupNames;
        this.context = context;
    }

    @NotNull
    @Override
    public JoinGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.groups_name_raw_layout, parent, false);
        return new JoinGroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull JoinGroupViewHolder holder, int position) {
        holder.groupName.setText(groupNames.get(position));
    }

    @Override
    public int getItemCount() {
        return groupNames.size();
    }

    public static class JoinGroupViewHolder extends RecyclerView.ViewHolder {
        TextView groupName;

        public JoinGroupViewHolder(@NonNull View itemView) {
            super(itemView);
            groupName = itemView.findViewById(R.id.group_name);


        }
    }
}
