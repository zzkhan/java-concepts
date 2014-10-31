package com.acme.nestedclasses;

public class Sender {
    public static void main(String[] args) {
        AuthToken authToken = new AuthToken("token");
        System.out.println(authToken.isProfileSet());
        authToken.setDth(true);
        authToken.setVip(true);
        System.out.println(authToken.isProfileSet());
        System.out.println(authToken.profileAsHeader());
        authToken.profileFromHeader("dth=false,vip=false");
        System.out.println(authToken.profileAsHeader());
    }
}
