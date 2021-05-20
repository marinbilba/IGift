package com.viauc.igift.data.callbacks;

import com.viauc.igift.model.Group;

import java.util.List;

public interface OnGroupClickListener {
    void onCreatedGroupClickCallback(Group group);
    void onJoinGroupClickCallback(Group group);

    void onDeleteCreatedGroupCallBack(Group group);
    void onDeleteJoinedGroupCallBack(Group group);

}
