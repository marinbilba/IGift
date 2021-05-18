package com.viauc.igift.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.lifecycle.LiveData;

import com.google.firebase.Timestamp;

public class CalendarEvent implements Parcelable {
    private String eventDescription;
    private Timestamp timestamp;

    public CalendarEvent() {
    }

    public CalendarEvent(String eventDescription, Timestamp timestamp) {
        this.eventDescription = eventDescription;
        this.timestamp = timestamp;
    }

    protected CalendarEvent(Parcel in) {
        eventDescription = in.readString();
        timestamp = in.readParcelable(Timestamp.class.getClassLoader());
    }

    public static final Creator<CalendarEvent> CREATOR = new Creator<CalendarEvent>() {
        @Override
        public CalendarEvent createFromParcel(Parcel in) {
            return new CalendarEvent(in);
        }

        @Override
        public CalendarEvent[] newArray(int size) {
            return new CalendarEvent[size];
        }
    };

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(eventDescription);
        dest.writeParcelable(timestamp, flags);
    }
}
