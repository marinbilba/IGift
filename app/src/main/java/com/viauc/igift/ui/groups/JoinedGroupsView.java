package com.viauc.igift.ui.groups;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.viauc.igift.R;
import com.viauc.igift.data.callbacks.OnGroupClickListener;
import com.viauc.igift.model.Group;

@Layout(R.layout.joined_groups_name_raw_layout)
public class JoinedGroupsView {
    private static String TAG ="ChildView";

    @View(R.id.group_name)
    TextView groupNameTextView;
    @View(R.id.groupsNameConstraintLayout)
    ConstraintLayout constraintLayout;
    @View(R.id.deleteJoinedGroupImageView)
    ImageView deleteJoinedGroupImageView;




    private Context mContext;
    private Group group;
    private OnGroupClickListener onGroupClickListener;

    public JoinedGroupsView(OnGroupClickListener onGroupClickListener, Group group) {
        this.group = group;
        this.onGroupClickListener=onGroupClickListener;
    }

    @Resolve
    private void onResolve(){
        groupNameTextView.setText(group.getGroupName());
        constraintLayout.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) { onGroupClickListener.onGroupClickCallback(group);
            }
        });
        deleteJoinedGroupImageView.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                onGroupClickListener.onDeleteJoinedGroupCallBack(group);
            }
        });
    }
}
