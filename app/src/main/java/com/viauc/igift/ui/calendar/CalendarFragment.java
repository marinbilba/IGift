package com.viauc.igift.ui.calendar;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.facebook.Profile;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.zxing.common.StringUtils;
import com.viauc.igift.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.facebook.Profile.getCurrentProfile;

public class CalendarFragment extends Fragment {

    private CalendarViewModel calendarViewModel;
    com.facebook.Profile facebookProfile = getCurrentProfile();
    View view;
    CalendarView calendarView;
FloatingActionButton floatingActionButton;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        calendarViewModel =
                new ViewModelProvider(this).get(CalendarViewModel.class);
        view = inflater.inflate(R.layout.fragment_calendar, container, false);
        calendarView = view.findViewById(R.id.calendarView);
        floatingActionButton = view.findViewById(R.id.addEventFloatingActionButton);
        calendarView.setOnDayClickListener(new OnDayClickListener() {

            @Override
            public void onDayClick(EventDay eventDay) {
                Toast.makeText(getContext(),
                        eventDay.getCalendar().getTime().toString() + " "
                                + eventDay.isEnabled(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        //change description button
        floatingActionButton.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            LayoutInflater inflaterAlert = this.getLayoutInflater();
            View dialogView = inflaterAlert.inflate(R.layout.fragment_add_event, null);
            builder.setView(dialogView);

            EditText editTextChangeDescription = dialogView.findViewById(R.id.eventEditText);

            builder.setTitle("Add event");
            // This method will be overwritten
            builder.setPositiveButton("Add",
                    (dialog, which) -> {
                        //Do nothing here because we override this button later to change the close behaviour.
                    });


            builder.setNegativeButton("Cancel", (dialog, id) -> {

            });

            String defaultText = "";
//            if (!StringUtils.isEmpty(selectedList.description)) {
//                defaultText = selectedList.description;
//            }
//            editTextChangeDescription.setText(defaultText);



            AlertDialog dialog = builder.create();
            dialog.show();

            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v1 -> {
                if (org.apache.commons.lang3.StringUtils.isEmpty(editTextChangeDescription.getText().toString())){
                    editTextChangeDescription.setError("This field is required");
                } else {

                    dialog.dismiss();
                }
            });

        });
        return view;

    }

}