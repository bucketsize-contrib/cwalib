/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cwa.util;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
/**
 *
 * @author jb
 */
public class XHostnameVerifier implements HostnameVerifier {
    public boolean verify(String string, SSLSession ssls) {
        return true;
    }
}
