package com.acme.hackdetect;

import com.acme.hackdetect.model.LoginEvent;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: zzkhan
 * Date: 01/07/2014
 * Time: 22:47
 * To change this template use File | Settings | File Templates.
 */
public interface EventRepository {
    void add(LoginEvent event);

    List<LoginEvent> getEvents(String ipAddress, int interval, TimeUnit timeUnit);
}
