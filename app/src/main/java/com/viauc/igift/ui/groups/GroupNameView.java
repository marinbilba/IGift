package com.viauc.igift.ui.groups;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.viauc.igift.R;
import com.viauc.igift.model.Group;

@Layout(R.layout.groups_name_raw_layout)
public class GroupNameView {
    private static String TAG ="ChildView";

    @View(R.id.group_name)
    TextView groupNameTextView;



    private Context mContext;
    private Group group;

    public GroupNameView(Context mContext, Group group) {
        this.mContext = mContext;
        this.group = group;
    }

    @Resolve
    private void onResolve(){
        groupNameTextView.setText(group.getGroupName());
        groupNameTextView.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Toast.makeText(mContext, group.getGroupName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
