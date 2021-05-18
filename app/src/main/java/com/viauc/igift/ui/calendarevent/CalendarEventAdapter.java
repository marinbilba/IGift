package com.viauc.igift.ui.calendarevent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.viauc.igift.R;
import com.viauc.igift.data.callbacks.OnRecyclerViewPositionClickListener;
import com.viauc.igift.model.CalendarEvent;
import com.viauc.igift.ui.groupmembers.GroupMembersAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CalendarEventAdapter extends RecyclerView.Adapter<CalendarEventAdapter.CalendarEventViewHolder>{
private ArrayList<CalendarEvent> calendarEvents;
    private  Context context;

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
        holder.eventNumber.setText("Event "+ (position+1));
        holder.eventDescription.setText(calendarEvents.get(position).getEventDescription());


    }

    @Override
    public int getItemCount() {
        return calendarEvents.size();
    }

    public static class CalendarEventViewHolder extends RecyclerView.ViewHolder {
        TextView eventNumber;
        TextView eventDescription;


        public CalendarEventViewHolder(@NonNull View itemView) {
            super(itemView);
            eventNumber = itemView.findViewById(R.id.eventNumberTextView);
            eventDescription = itemView.findViewById(R.id.eventDescriptionTextView);

        }

    }
}
