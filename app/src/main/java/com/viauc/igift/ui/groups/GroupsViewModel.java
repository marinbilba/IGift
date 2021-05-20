package com.viauc.igift.ui.groups;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.viauc.igift.data.UserGroupsRepository;
import com.viauc.igift.model.Group;

import java.util.ArrayList;

public class GroupsViewModel extends AndroidViewModel {

    private final UserGroupsRepository userGroupsRepository;

    public GroupsViewModel(Application app) {
        super(app);
        userGroupsRepository = UserGroupsRepository.getInstance(app);

    }


    public LiveData<ArrayList<Group>> getUserCreatedGroups() {
     userGroupsRepository.getCurrentUserCreatedGroups();
       return userGroupsRepository.getUserCreatedGroupsLiveData();
    }


    public LiveData<ArrayList<Group>> getUserJoinedGroups() {
        userGroupsRepository.getUserJoinedGroups();
       return userGroupsRepository.getUserJoinedGroupsLiveData();
    }
//todo check if the calling user is the owner of the group
    public void deleteCreatedGroup(Group group) {

        userGroupsRepository.deleteCreatedGroup(group.getuID());

    }

    public void leaveJoinedGroup(Group group) {
        userGroupsRepository.leaveJoinedGroupCurrentUser(group);
    }
}