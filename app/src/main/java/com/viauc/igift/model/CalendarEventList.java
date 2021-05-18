package com.viauc.igift.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class CalendarEventList implements Parcelable {
    private ArrayList<CalendarEvent> calendarEvents;

    public CalendarEventList() {
    }

    public CalendarEventList(ArrayList<CalendarEvent> calendarEvents) {
        this.calendarEvents = calendarEvents;
    }

    public ArrayList<CalendarEvent> getCalendarEvents() {
        return calendarEvents;
    }

    public void setCalendarEvents(ArrayList<CalendarEvent> calendarEvents) {
        this.calendarEvents = calendarEvents;
    }

    protected CalendarEventList(Parcel in) {
        in.readTypedList(calendarEvents,CalendarEvent.CREATOR);
    }

    public static final Creator<CalendarEventList> CREATOR = new Creator<CalendarEventList>() {
        @Override
        public CalendarEventList createFromParcel(Parcel in) {
            return new CalendarEventList(in);
        }

        @Override
        public CalendarEventList[] newArray(int size) {
            return new CalendarEventList[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(calendarEvents);
    }
}
