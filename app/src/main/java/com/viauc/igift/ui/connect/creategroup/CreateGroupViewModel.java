package com.viauc.igift.ui.connect.creategroup;

import android.app.Application;
import android.util.Pair;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.viauc.igift.data.UserGroupsRepository;
import com.viauc.igift.util.FieldValidation;

public class CreateGroupViewModel extends AndroidViewModel {
    private final UserGroupsRepository userGroupsRepository;
    private final FieldValidation fieldValidation;
    public CreateGroupViewModel(@NonNull Application application) {
        super(application);
        userGroupsRepository = UserGroupsRepository.getInstance(application);
        fieldValidation=new FieldValidation();
    }
    public void createGroup(String groupName){
        userGroupsRepository.createGroup(groupName);
    }

    public Pair<Boolean, String> validateGroupNameInputField(String groupName) {
return fieldValidation.validateGroupName(groupName);

    }
}
