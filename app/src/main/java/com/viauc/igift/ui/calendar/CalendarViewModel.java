package com.viauc.igift.ui.calendar;

import android.app.Application;
import android.widget.EditText;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.applandeo.materialcalendarview.EventDay;
import com.google.firebase.Timestamp;
import com.viauc.igift.data.CalendarEventsRepository;
import com.viauc.igift.model.CalendarEvent;

import java.util.ArrayList;

public class CalendarViewModel extends AndroidViewModel {

private CalendarEventsRepository calendarEventsRepository;
    public CalendarViewModel(Application app) {
        super(app);
        calendarEventsRepository=CalendarEventsRepository.getInstance(app);

    }


    public void saveEvent(EditText editTextChangeDescription, EventDay selectedEventDay) {
        Timestamp timestamp=new Timestamp(selectedEventDay.getCalendar().getTime());
        calendarEventsRepository.saveEventToCalendar(editTextChangeDescription.getText().toString(),timestamp);
    }

    public LiveData<ArrayList<CalendarEvent>> getUserCalendarEvents() {
        calendarEventsRepository.getUserCalendarEvents();
    return calendarEventsRepository.getCalendarEventLiveData();
    }
}