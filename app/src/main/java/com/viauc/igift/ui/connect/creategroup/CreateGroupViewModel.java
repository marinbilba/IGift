package com.viauc.igift.ui.connect.creategroup;

import android.app.Application;
import android.util.Pair;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.viauc.igift.data.UserGroupsRepository;

public class CreateGroupViewModel extends AndroidViewModel {
    private final UserGroupsRepository userGroupsRepository;
    public CreateGroupViewModel(@NonNull Application application) {
        super(application);
        userGroupsRepository = UserGroupsRepository.getInstance(application);
    }
    public void createGroup(String groupName){
        userGroupsRepository.createGroup(groupName);
    }

    public Pair<Boolean, String> validateGroupNameInputField(String groupName) {

        if(groupName.isEmpty()){
           return new Pair<Boolean,String>(false,"Field can not be empty");
        }
        else if(groupName.length()<6){
            return new Pair<Boolean,String>(false,"Minimum 6 characters");
        }
        return new Pair<Boolean,String>(true,"");

    }
}
