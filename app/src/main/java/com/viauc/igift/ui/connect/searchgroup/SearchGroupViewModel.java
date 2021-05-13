package com.viauc.igift.ui.connect.searchgroup;

import android.app.Application;
import android.util.Pair;

import androidx.lifecycle.AndroidViewModel;

import com.viauc.igift.data.callbacks.CreateGroupCallback;
import com.viauc.igift.data.UserGroupsRepository;

public class SearchGroupViewModel extends AndroidViewModel {
    private final UserGroupsRepository userGroupsRepository;

    public SearchGroupViewModel(Application app) {
        super(app);
        userGroupsRepository = UserGroupsRepository.getInstance(app);

    }

    public Pair<Boolean, String> validateJoinGroupNameInputField(String groupName) {

        if(groupName.isEmpty()){
            return new Pair<Boolean,String>(false,"Field can not be empty");
        }
        else if(groupName.length()<6){
            return new Pair<Boolean,String>(false,"Minimum 6 characters");
        }
        return new Pair<Boolean,String>(true,"");
    }

    public void getUserCreatedGroupsByEmail(CreateGroupCallback fetchedUserCreatedGroupsCallback, String userEmail) {

      userGroupsRepository.getUserCreatedGroupsLiveData(fetchedUserCreatedGroupsCallback,userEmail);

    }
}
