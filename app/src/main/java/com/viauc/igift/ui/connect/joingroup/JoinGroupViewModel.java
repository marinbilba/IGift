package com.viauc.igift.ui.connect.joingroup;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.viauc.igift.data.callbacks.UserCreatedGroupsCallback;
import com.viauc.igift.data.UserGroupsRepository;
import com.viauc.igift.model.Group;

public class JoinGroupViewModel extends AndroidViewModel {
    private final UserGroupsRepository userGroupsRepository;
    public JoinGroupViewModel(@NonNull Application application) {
        super(application);
        userGroupsRepository = UserGroupsRepository.getInstance(application);
    }

    public void getUserCreatedGroupsByEmail(UserCreatedGroupsCallback fetchedUserCreatedGroupsCallback, String userEmail) {

        userGroupsRepository.getUserCreatedGroups(fetchedUserCreatedGroupsCallback,userEmail);

    }

    public void joinGroup(Group group) {
        userGroupsRepository.joinGroup(group);

    }
}
