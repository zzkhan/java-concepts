package com.acme.hackdetect;

import com.acme.hackdetect.model.LoginEvent;
import org.joda.time.DateTime;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class EventsRepositoryImpl implements EventRepository {

    Map<String,PriorityQueue<LoginEvent>> failedEventsMap;


    @Override
    public void add(LoginEvent event) {
        PriorityQueue<LoginEvent> loginEvents = failedEventsMap.get(event.getIpAddress());
        if(loginEvents == null){
            loginEvents = new PriorityQueue<LoginEvent>(5, new LoginEventComparator());
        } else if(loginEvents.size()>=5){
            loginEvents.remove();

        }
        loginEvents.add(event);
    }

    @Override
    public List<LoginEvent> getEvents(String ipAddress, int interval, TimeUnit timeUnit) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private class LoginEventComparator implements Comparator<LoginEvent> {
        @Override
        public int compare(LoginEvent object,LoginEvent object1) {
            return new DateTime(object.getDate()).compareTo(new DateTime(object1));
        }
    }
}
