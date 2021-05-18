package com.viauc.igift.data;

import android.app.Application;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.viauc.igift.model.CalendarEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CalendarEventsRepository {
    private static CalendarEventsRepository instance;


    private final Application application;
    private final FirebaseFirestore firebaseFirestore;
    private final FirebaseAuth mAuth;
    MutableLiveData<ArrayList<CalendarEvent>> calendarEventMutableLiveData;
    private LiveData<ArrayList<CalendarEvent>> calendarEventLiveData;

    private CalendarEventsRepository(Application application) {
        this.application = application;
        // Initialize firebase  instance
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        calendarEventMutableLiveData = new MutableLiveData<>();
        calendarEventLiveData=calendarEventMutableLiveData;
    }


    public static CalendarEventsRepository getInstance(Application app) {
        if (instance == null)
            instance = new CalendarEventsRepository(app);
        return instance;
    }

    public void saveEventToCalendar(String eventDescription, Timestamp timestamp) {
        if (mAuth.getUid() == null) {
            return;
        }
        Map<String, Object> newEvent = new HashMap<>();
        newEvent.put("eventDescription", eventDescription);
        newEvent.put("timestamp", timestamp);
        firebaseFirestore.collection("users").document(mAuth.getUid()).collection("calendarEvents").add(newEvent);
    }



    public void getUserCalendarEvents() {
        firebaseFirestore.collection("users").document(mAuth.getUid()).collection("calendarEvents").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable @org.jetbrains.annotations.Nullable QuerySnapshot value, @Nullable @org.jetbrains.annotations.Nullable FirebaseFirestoreException error) {
                ArrayList<CalendarEvent> calendarEvent = new ArrayList<>();
                for (QueryDocumentSnapshot document : value) {
                    calendarEvent.add(new CalendarEvent(document.getString("eventDescription"), document.getTimestamp("timestamp")));
                    System.out.println();
                }
                calendarEventMutableLiveData.postValue(calendarEvent);
                calendarEventLiveData = calendarEventMutableLiveData;
            }

        });


    }

    public LiveData<ArrayList<CalendarEvent>> getCalendarEventLiveData() {
        return calendarEventLiveData;
    }
}
