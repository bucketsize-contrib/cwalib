/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cwa.util;

import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

/**
 *
 * @author JA34916
 */
public class XTrustManager implements X509TrustManager {

    public XTrustManager() {
        Logger.log("BAD: Client using promiscuous trust manager!");
    }
    public void checkClientTrusted(X509Certificate[] chain, String authType) {
    }

    public void checkServerTrusted(X509Certificate[] chain, String authType) {
    }

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }

}
