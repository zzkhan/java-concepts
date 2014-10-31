package com.acme.nestedclasses;

/**
 * Created with IntelliJ IDEA.
 * User: zzkhan
 * Date: 21/05/2014
 * Time: 22:56
 * To change this template use File | Settings | File Templates.
 */
public class AuthTokenTest {
    AuthToken authToken = new AuthToken("t");
    AuthToken.ProfileHeaderHelper profileHeaderHelper = authToken.new ProfileHeaderHelper();
}
