package com.viauc.igift.ui.groups;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.viauc.igift.data.callbacks.CreateGroupCallback;
import com.viauc.igift.data.UserGroupsRepository;

public class GroupsViewModel extends AndroidViewModel {

    private final UserGroupsRepository userGroupsRepository;

    public GroupsViewModel(Application app) {
        super(app);
        userGroupsRepository = UserGroupsRepository.getInstance(app);

    }


    public void getUserCreatedGroups(CreateGroupCallback fetchedUserCreatedGroupsCallback) {
        userGroupsRepository.getUserCreatedGroupsLiveData(fetchedUserCreatedGroupsCallback);
    }


}