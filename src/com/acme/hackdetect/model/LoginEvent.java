package com.acme.hackdetect.model;

import java.util.Date;

public class LoginEvent {
    private String ipAddress;
    private Date date;
    private String outcome;
    private String username;

    public LoginEvent(String ipAddress, Date date, String outcome, String username) {
        //To change body of created methods use File | Settings | File Templates.
        this.ipAddress = ipAddress;
        this.date = date;
        this.outcome = outcome;
        this.username = username;
    }

    public static LoginEvent from(String log) {
        String[] parts = log.split(",");
        String ipAddress = parts[0];
        Date date = getDate(parts[1]);
        String outcome = parts[2];
        String username = parts[3];
        LoginEvent loginEvent = new LoginEvent(ipAddress,date,outcome,username);
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    private static Date getDate(String timeSinceEpoch) {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    public boolean isFailure() {
        return "SIGNIN_FAILURE".equals(outcome);
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public Date getDate() {
        return date;
    }
}
