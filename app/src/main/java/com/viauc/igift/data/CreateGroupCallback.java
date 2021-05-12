package com.viauc.igift.data;

import com.viauc.igift.model.Group;

import java.util.List;

public interface CreateGroupCallback {
    void createdGroupsOnCallbackSuccess(List<Group> list);

    void createdGroupsOnCallbackNoResults();
}
