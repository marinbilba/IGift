package com.viauc.igift.ui.groups;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.viauc.igift.data.callbacks.UserCreatedGroupsCallback;
import com.viauc.igift.data.UserGroupsRepository;
import com.viauc.igift.data.callbacks.UserJoinedGroupsCallback;

public class GroupsViewModel extends AndroidViewModel {

    private final UserGroupsRepository userGroupsRepository;

    public GroupsViewModel(Application app) {
        super(app);
        userGroupsRepository = UserGroupsRepository.getInstance(app);

    }


    public void getUserCreatedGroups(UserCreatedGroupsCallback fetchedUserCreatedGroupsCallback) {
        userGroupsRepository.getUserCreatedGroups(fetchedUserCreatedGroupsCallback);
    }


    public void getUserJoinedGroups(UserJoinedGroupsCallback userJoinedGroupsCallback) {
        userGroupsRepository.getUserJoinedGroups(userJoinedGroupsCallback);
    }
}