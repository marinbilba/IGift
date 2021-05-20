package com.viauc.igift.ui.connect.searchgroup;

import android.app.Application;
import android.util.Pair;

import androidx.lifecycle.AndroidViewModel;

import com.viauc.igift.data.callbacks.UserCreatedGroupsCallback;
import com.viauc.igift.data.UserGroupsRepository;
import com.viauc.igift.util.FieldValidation;

public class SearchGroupViewModel extends AndroidViewModel {
    private final UserGroupsRepository userGroupsRepository;
private final FieldValidation fieldValidation;
    public SearchGroupViewModel(Application app) {
        super(app);
        userGroupsRepository = UserGroupsRepository.getInstance(app);
        fieldValidation=new FieldValidation();

    }

    public Pair<Boolean, String> validateJoinGroupNameInputField(String groupName) {

      return fieldValidation.validateGroupName(groupName);
    }

    public void getUserCreatedGroupsByEmail(UserCreatedGroupsCallback fetchedUserCreatedGroupsCallback, String userEmail) {

      userGroupsRepository.getUserCreatedGroupsByEmail(fetchedUserCreatedGroupsCallback,userEmail);

    }
}
