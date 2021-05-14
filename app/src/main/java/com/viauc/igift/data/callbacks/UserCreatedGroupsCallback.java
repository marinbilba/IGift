package com.viauc.igift.data.callbacks;

import com.viauc.igift.model.Group;

import java.util.List;

public interface UserCreatedGroupsCallback {
    void createdGroupsOnCallbackSuccess(List<Group> list);

    void createdGroupsOnCallbackNoResults();
}
