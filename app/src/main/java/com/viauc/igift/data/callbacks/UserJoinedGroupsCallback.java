package com.viauc.igift.data.callbacks;

import com.viauc.igift.model.Group;

import java.util.List;

public interface UserJoinedGroupsCallback {
    void joinedGroupsOnCallbackSuccess(List<Group> list);

    void joinedGroupsOnCallbackNoResults();
}
