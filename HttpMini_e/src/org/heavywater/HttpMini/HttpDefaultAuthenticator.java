package org.heavywater.HttpMini;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

public class HttpDefaultAuthenticator extends Authenticator {

    /**
     * ntlm scheme sometime needs domain
     * can be specified as DOMAIN\\user for user name
     * or, as property http.auth.ntlm.domain
     */
    private static String user;
    private static String pass;
    private static HttpDefaultAuthenticator instance;
    private static int retry;

    public HttpDefaultAuthenticator() {
    }

    public static HttpDefaultAuthenticator instance(String user, String pass) {
        if (instance != null) {
            HttpDefaultAuthenticator.user = user;
            HttpDefaultAuthenticator.pass = pass;
            return instance;
        } else {
            instance = new HttpDefaultAuthenticator();
            HttpDefaultAuthenticator.user = user;
            HttpDefaultAuthenticator.pass = pass;
            return instance;
        }

    }

    @Override
    public PasswordAuthentication getPasswordAuthentication() {
        if (++retry > 1) {
            System.err.println("sent server auth too many times, giving up " + getRequestingScheme());
            return (new PasswordAuthentication("", "".toCharArray()));
        } else {
            System.err.println("server requested authentication " + getRequestingScheme());
            return (new PasswordAuthentication(user, pass.toCharArray()));
        }
    }
}
