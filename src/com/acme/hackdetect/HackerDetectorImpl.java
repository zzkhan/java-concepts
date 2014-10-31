package com.acme.hackdetect;

import com.acme.hackdetect.model.LoginEvent;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: zzkhan
 * Date: 01/07/2014
 * Time: 22:42
 * To change this template use File | Settings | File Templates.
 */
public class HackerDetectorImpl implements HackerDetector {
    private EventRepository eventsRepository;

    @Override
    public String parseLine(String line) {
        LoginEvent event = LoginEvent.from(line);
        if(event.isFailure()){
            eventsRepository.add(event);
        }

        List<LoginEvent> failedEvents = eventsRepository.getEvents(event.getIpAddress(), 5, TimeUnit.MINUTES);
        if(failedEvents.size()>5){
            return event.getIpAddress();
        }
        return null;
    }
}
