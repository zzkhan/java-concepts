package com.acme.nestedclasses;

import com.google.common.base.Splitter;

import java.util.Map;

public class AuthToken {
    private final String token;
    private Boolean dth;
    private Boolean vip;

    public AuthToken(String token) {
        this.token = token;
    }

    public void setDth(Boolean dth) {
        this.dth = dth;
    }

    public void setVip(Boolean vip) {
        this.vip = vip;
    }

    public String profileAsHeader() {
        return new ProfileHeaderHelper().pack();
    }

    public void profileFromHeader(String profileHeaderValue) {
        new ProfileHeaderHelper().unpack(profileHeaderValue);
    }

    public boolean isProfileSet(){
        return dth != null || vip != null;
    }

    protected class ProfileHeaderHelper {

        public String pack(){
            return String.format("isdth=%s,isVip=%s",dth.toString(),vip.toString());
        }

        public Map<String,String> unpack(String header){
            Map<String, String> profileMap = Splitter.on(',').withKeyValueSeparator('=').split(header);
            dth = Boolean.valueOf(profileMap.get("dth"));
            vip = Boolean.valueOf(profileMap.get("vip"));
            return profileMap;
        }
    }
}
