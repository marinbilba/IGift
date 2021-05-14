package com.viauc.igift.ui.groupmembers;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.viauc.igift.data.UserGroupsRepository;

import org.jetbrains.annotations.NotNull;

public class GroupMembersViewModel extends AndroidViewModel {
    private final UserGroupsRepository userGroupsRepository;
    public GroupMembersViewModel(@NonNull @NotNull Application application) {
        super(application);
        userGroupsRepository = UserGroupsRepository.getInstance(application);
    }
}
