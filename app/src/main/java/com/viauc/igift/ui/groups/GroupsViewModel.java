package com.viauc.igift.ui.groups;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.viauc.igift.data.UserGroupsRepository;
import com.viauc.igift.model.Group;

import java.util.ArrayList;

public class GroupsViewModel extends AndroidViewModel {

    private final UserGroupsRepository userGroupsRepository;

    public GroupsViewModel(Application app) {
        super(app);
        userGroupsRepository = UserGroupsRepository.getInstance(app);

    }


    public MutableLiveData<ArrayList<Group>> getUserCreatedGroups() {

        return userGroupsRepository.getUserCreatedGroupsLiveData();
    }


}