package com.viauc.igift.ui.connect.creategroup;

import android.app.Application;
import android.util.Pair;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.viauc.igift.R;
import com.viauc.igift.data.UserDataRepository;
import com.viauc.igift.model.Group;

public class CreateGroupViewModel extends AndroidViewModel {
    private final UserDataRepository userDataRepository;

    public CreateGroupViewModel(@NonNull Application application) {
        super(application);
        userDataRepository = UserDataRepository.getInstance(application);
    }
    public void createGroup(String groupName){
        userDataRepository.createGroup(groupName);
    }

    public Pair<Boolean, String> validateGroupNameInputField(String groupName, View view) {

        if(groupName.isEmpty()){
           return new Pair<Boolean,String>(false,"Field can not be empty");
        }
        else if(groupName.length()<6){
            return new Pair<Boolean,String>(false,"Minimum 6 characters");
        }
        return new Pair<Boolean,String>(true,"");

    }
}
