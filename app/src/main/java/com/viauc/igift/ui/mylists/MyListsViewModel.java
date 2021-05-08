package com.viauc.igift.ui.mylists;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MyListsViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;

    public MyListsViewModel(Application app) {
        super(app);
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");

    }

    public LiveData<String> getText() {
        return mText;
    }


}