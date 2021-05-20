package com.viauc.igift.ui.calendarevent;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viauc.igift.R;
import com.viauc.igift.model.CalendarEvent;
import com.viauc.igift.model.CalendarEventList;
import com.viauc.igift.model.Group;
import com.viauc.igift.ui.groupmembers.GroupMembersAdapter;
import com.viauc.igift.ui.groupmembers.GroupMembersFragmentArgs;

import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;


public class CalendarEventFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private ArrayList<CalendarEvent> calendarEvents;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_calendar_event, container, false);
        recyclerView = view.findViewById(R.id.calendarEventRecyclerView);

        return view;
    }

    private void inflateRecyclerView() {
        CalendarEventAdapter myAdapter = new CalendarEventAdapter(calendarEvents, getContext());
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        CalendarEventFragmentArgs calendarEventFragmentArgs = CalendarEventFragmentArgs.fromBundle(getArguments());
        CalendarEventList calendarEventList = calendarEventFragmentArgs.getCalendarEvents();
        calendarEvents = calendarEventList.getCalendarEvents();
        if(!CollectionUtils.isEmpty(calendarEvents)){
            inflateRecyclerView();
        }

    }
}