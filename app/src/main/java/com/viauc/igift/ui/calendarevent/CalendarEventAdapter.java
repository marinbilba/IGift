package com.viauc.igift.ui.calendarevent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.viauc.igift.R;
import com.viauc.igift.model.CalendarEvent;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CalendarEventAdapter extends RecyclerView.Adapter<CalendarEventAdapter.CalendarEventViewHolder> {
    private final ArrayList<CalendarEvent> calendarEvents;
    private final Context context;

    public CalendarEventAdapter(ArrayList<CalendarEvent> calendarEvents, Context context) {
        this.calendarEvents = calendarEvents;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public CalendarEventViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.calendar_event_card_layout, parent, false);
        return new CalendarEventAdapter.CalendarEventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CalendarEventViewHolder holder, int position) {
        holder.eventNumber.setText("Event " + (position + 1));
        holder.eventDescription.setText(calendarEvents.get(position).getEventDescription());
        String time = getTimeInFormatHHMM(calendarEvents.get(position));
        holder.editTextTime.setText(time);

    }

    private String getTimeInFormatHHMM(CalendarEvent calendarEvent) {
        String temp = "";
        Date currentDate = calendarEvent.getTimestamp().toDate();
        Calendar tempDate = Calendar.getInstance();
        tempDate.setTime(currentDate);
        temp = temp.concat(String.valueOf(tempDate.get(Calendar.HOUR)));
        temp = temp.concat(":");
        temp = temp.concat(String.valueOf(tempDate.get(Calendar.MINUTE)));


        return temp;
    }

    @Override
    public int getItemCount() {
        return calendarEvents.size();
    }

    public static class CalendarEventViewHolder extends RecyclerView.ViewHolder {
        TextView eventNumber;
        TextView eventDescription;
        TextView editTextTime;


        public CalendarEventViewHolder(@NonNull View itemView) {
            super(itemView);
            eventNumber = itemView.findViewById(R.id.eventNumberTextView);
            eventDescription = itemView.findViewById(R.id.eventDescriptionTextView);
            editTextTime = itemView.findViewById(R.id.editTextTime);

        }

    }
}
