package com.viauc.igift.ui.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.facebook.Profile;
import com.viauc.igift.R;

import static com.facebook.Profile.getCurrentProfile;

public class CalendarFragment extends Fragment {

    private CalendarViewModel calendarViewModel;
 com.facebook.Profile facebookProfile=getCurrentProfile();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        calendarViewModel =
                new ViewModelProvider(this).get(CalendarViewModel.class);
        View root = inflater.inflate(R.layout.fragment_calendar, container, false);

        return root;

    }

}