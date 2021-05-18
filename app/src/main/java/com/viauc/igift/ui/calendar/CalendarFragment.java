package com.viauc.igift.ui.calendar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.allyants.notifyme.NotifyMe;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.Timestamp;
import com.viauc.igift.MainActivity;
import com.viauc.igift.R;
import com.viauc.igift.model.CalendarEvent;
import com.viauc.igift.model.CalendarEventList;
import com.viauc.igift.ui.groups.GroupsFragmentDirections;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.facebook.Profile.getCurrentProfile;

public class CalendarFragment extends Fragment {

    private CalendarViewModel calendarViewModel;
    com.facebook.Profile facebookProfile = getCurrentProfile();
    View view;
    CalendarView calendarView;
    FloatingActionButton floatingActionButton;
    EventDay selectedEventDay;
    ArrayList<CalendarEvent> calendarEvents;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        calendarViewModel =
                new ViewModelProvider(this).get(CalendarViewModel.class);
        view = inflater.inflate(R.layout.fragment_calendar, container, false);
        calendarView = view.findViewById(R.id.calendarView);
        floatingActionButton = view.findViewById(R.id.addEventFloatingActionButton);
        selectedEventDay = new EventDay(calendarView.getFirstSelectedDate());

        // current selected day
        calendarView.setOnDayClickListener(eventDay -> {
            selectedEventDay = eventDay;
            checkCalendarEvent(eventDay);
        });

        floatingActionButton.setOnClickListener(v -> {
            inflateAddEventFragment();
        });
        calendarViewModel.getUserCalendarEvents().observe(getViewLifecycleOwner(), new Observer<ArrayList<CalendarEvent>>() {
            @Override
            public void onChanged(ArrayList<CalendarEvent> calendarEvents) {
                insertUserCalendarEvents(calendarEvents);
            }
        });
        return view;

    }

    private void checkCalendarEvent(EventDay eventDay) {
        ArrayList<CalendarEvent> tempCalendarEvents = new ArrayList<>();
        for (CalendarEvent event : calendarEvents) {
            Date date = event.getTimestamp().toDate();
            Calendar firstDate = Calendar.getInstance();
            firstDate.setTime(date);
//todo "same day" is not as simple a concept as it sounds when different time zones can be involved.
// The code bellow will for both dates compute the day relative to the time zone used by the computer it is running on.
            boolean sameDay = firstDate.get(Calendar.DAY_OF_YEAR) == eventDay.getCalendar().get(Calendar.DAY_OF_YEAR) &&
                    firstDate.get(Calendar.YEAR) == eventDay.getCalendar().get(Calendar.YEAR);
            if (sameDay) {
                tempCalendarEvents.add(event);
            }
        }
        if (!CollectionUtils.isEmpty(tempCalendarEvents)) {
            openEventDisplay(tempCalendarEvents);
        }

    }

    private void openEventDisplay(ArrayList<CalendarEvent> tempCalendarEvents) {
        CalendarEventList calendarEventList = new CalendarEventList(tempCalendarEvents);
        CalendarFragmentDirections.ActionNavigationCalendarToCalendarEventFragment action =
                CalendarFragmentDirections.actionNavigationCalendarToCalendarEventFragment(calendarEventList);
        Navigation.findNavController(view).navigate(action);
    }

    private void insertUserCalendarEvents(ArrayList<CalendarEvent> calendarEvents) {
        this.calendarEvents = calendarEvents;
        List<EventDay> events = new ArrayList<>();
        for (CalendarEvent calendarEvent : calendarEvents) {
            Calendar calendar = Calendar.getInstance();
            Timestamp timestamp = calendarEvent.getTimestamp();
            calendar.setTime(timestamp.toDate());
            events.add(new EventDay(calendar, R.drawable.sample_three_icons, Color.parseColor("#228B22")));
        }

        calendarView.setEvents(events);
    }

    private void inflateAddEventFragment() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflaterAlert = this.getLayoutInflater();
        View dialogView = inflaterAlert.inflate(R.layout.fragment_add_event, null);
        builder.setView(dialogView);

        EditText editTextChangeDescription = dialogView.findViewById(R.id.eventEditText);
        TimePicker timePicker = dialogView.findViewById(R.id.eventTimePicker);
        CheckBox notifyMeCheckBox = dialogView.findViewById(R.id.notifyMeCheckBox);

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
            if (org.apache.commons.lang3.StringUtils.isEmpty(editTextChangeDescription.getText().toString())) {
                editTextChangeDescription.setError("This field is required");

            } else {
                selectedEventDay.getCalendar().set(Calendar.HOUR_OF_DAY, timePicker.getHour());
                selectedEventDay.getCalendar().set(Calendar.MINUTE, timePicker.getMinute());

                calendarViewModel.saveEvent(editTextChangeDescription, selectedEventDay);

                if (notifyMeCheckBox.isChecked()) {
                    registerNotification(editTextChangeDescription, selectedEventDay);
                }
                dialog.dismiss();
            }
        });
    }

    private void registerNotification(EditText editTextChangeDescription, EventDay selectedEventDay) {
        NotifyMe.Builder notifyMe = new NotifyMe.Builder(getContext());
        notifyMe.title("IGIFT");
        notifyMe.content(editTextChangeDescription.getText());
        notifyMe.time(selectedEventDay.getCalendar());
        notifyMe.large_icon(R.mipmap.ic_launcher_foreground);
        notifyMe.build();
    }

}