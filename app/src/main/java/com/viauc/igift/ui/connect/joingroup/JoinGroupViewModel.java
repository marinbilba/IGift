package com.viauc.igift.ui.connect.joingroup;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.viauc.igift.data.callbacks.CreateGroupCallback;
import com.viauc.igift.data.UserGroupsRepository;

public class JoinGroupViewModel extends AndroidViewModel {
    private final UserGroupsRepository userGroupsRepository;
    public JoinGroupViewModel(@NonNull Application application) {
        super(application);
        userGroupsRepository = UserGroupsRepository.getInstance(application);
    }

    public void getUserCreatedGroupsByEmail(CreateGroupCallback fetchedUserCreatedGroupsCallback, String userEmail) {

        userGroupsRepository.getUserCreatedGroupsLiveData(fetchedUserCreatedGroupsCallback,userEmail);

    }
}
