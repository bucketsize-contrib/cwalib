/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cwa;

import cwa.util.*;
import cwa.request.*;
import cwa.event.Event;
import cwa.exception.CwaException;
import cwa.exception.RequestFailed;
import cwa.request.RequestText;
import cwa.response.Response;
import org.heavywater.HttpMini.*;

import java.io.IOException;
import java.net.Authenticator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.HostnameVerifier;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class Client {
    private String baseurls;
    private HttpHeader httpHeader;
    private NodeHelper nodeHelper;
    private cwa.event.EventManager evtMan;
    private cwa.response.Factory rspFactory;
	
//    set after logon
    private String user;
    private String uri;
    private String signInData;
    private String cwaTicket;
    public Map confs;
	
//    set after session init
    public int confid;
    private String sid;
    private int rid;
    private int ackid;
	
//    temp
    HttpResponse rsp = null;
    ClientStateHelper stateHelper = null;
	
//    private boolean connectRetry = true;

    public Client() {
        httpHeader = new HttpHeader();

        nodeHelper = NodeHelper.instance();
        evtMan = new cwa.event.EventManager();
        rspFactory = new cwa.response.Factory();
        stateHelper = new ClientStateHelper();

        confs = new HashMap();

        confid = 0;
        rid = 0;
        ackid = 0;
    }

    private int get_confid() {
        return ++confid;
    }

    private int get_rid() {
        return ++rid;
    }

    private int get_ackid() {
        return ackid++;
    }

    public int xconnect(String ntuser, String ntpass, String urls) throws CwaException {
        // == cwa server cert is not trusted / invalid;
        //      use a promiscuous trust manager
        TrustManager[] ptm = { new XTrustManager() };
        HostnameVerifier hver = null;
        SSLContext ctx = null;
        try {
            ctx = SSLContext.getInstance("TLS");
        } catch (NoSuchAlgorithmException ex) {
            log(this, ex);
            ex.printStackTrace(System.err);
            throw new CwaException();
        }
        try {
            ctx.init(null, ptm, null);
        } catch (KeyManagementException ex) {
            log(this, ex);;
            ex.printStackTrace(System.err);
            throw new CwaException();
        }
        SSLSocketFactory ssf = ctx.getSocketFactory();
        HttpsURLConnection.setDefaultSSLSocketFactory(ssf);

        hver = new XHostnameVerifier();
        HttpsURLConnection.setDefaultHostnameVerifier(hver);
        // --
        return connect(ntuser, ntpass, urls);
    }

    public int connect(String ntuser, String ntpass, String urls)
            throws CwaException {
        log(this, "connecting to " + urls + " ...");
        baseurls = urls;
        setupAuthHandler(ntuser, ntpass);
        HttpRequest httpRequest = new HttpRequest();
        try {
            rsp = httpRequest.get(httpHeader, baseurls);
        } catch (IOException ex) {
            log(this, ex.toString());
            ex.printStackTrace(System.err);
            throw new RequestFailed();
        }
        return 1;
    }

    public void setupAuthHandler(String u, String p)
            throws CwaException {
        Authenticator.setDefault(HttpDefaultAuthenticator.instance(u, p));
    }

    public int logon(String cwauser, String cwapass)
            throws CwaException {
        HttpRequest httpRequest = new HttpRequest();
        try {
            log(this, "logging in ...");
            rsp = httpRequest.post(httpHeader, 
					logonRequest.build(cwauser, cwapass), 
					baseurls + "/forms/logon.html");
        } catch (IOException ex) {
            log(this, ex);
            ex.printStackTrace(System.err);
            log(this, httpRequest.toString() + rsp);
            throw new RequestFailed();
        }

        if (validateResponse(rsp.getContent(), "requestSucceeded")) {
            Document dom = nodeHelper.build(rsp.getContent());
            if (dom != null) {
                //cwa user
                user = cwauser;
                //cwa-ticket from header
                cwaTicket = rsp.getHeader().getProperty("CWA-Ticket");
                //sign-in data
                Node no1 = dom.getElementsByTagName("signInData").item(0);
                signInData = no1.getTextContent();
                return 1;
            }
        }
        log(this, httpRequest.toString() + rsp);
        throw new RequestFailed();
    }

    public int initiateSession()
            throws CwaException {

        log(this, "initiateSession ...");
        httpHeader.setProperty("CWA-Ticket", cwaTicket);
        HttpRequest httpRequest = new HttpRequest();
        try {
            rsp = httpRequest.post(httpHeader, 
					initiateSessionRequest.build(signInData), 
					baseurls + "/cwa/MainCommandHandler.ashx");
        } catch (IOException ex) {
            log(this, ex);
            ex.printStackTrace(System.err);
            log(this, httpRequest.toString() + rsp);
            throw new RequestFailed();
        }

        if (validateResponse(rsp.getContent(), "requestAccepted")) {
            //TODO: replace this with async poll of server status
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                log(this, ex);
                ex.printStackTrace(System.err);
                log(this, httpRequest.toString() + rsp);
            }
            Document dom = nodeHelper.build(rsp.getContent());
            if (dom != null) {
                //sid
                Node no1 = dom.getElementsByTagName("sid").item(0);
                sid = no1.getTextContent();
                //uri
                no1 = dom.getElementsByTagName("uri").item(0);
                uri = no1.getTextContent();
                return 1;
            }
        }
        log(this, httpRequest.toString() + rsp);
        throw new RequestFailed();
    }

    public int terminateSession()
            throws CwaException {
        HttpRequest httpRequest = new HttpRequest();
        try {
            rsp = httpRequest.post(httpHeader, 
					terminateSessionRequest.build(sid, get_rid()), 
					baseurls + "/cwa/MainCommandHandler.ashx");
        } catch (IOException ex) {
            log(this, ex);
            ex.printStackTrace(System.err);
            log(this, httpRequest.toString() + rsp);
            throw new RequestFailed();
        }

        if (!validateResponse(rsp.getContent(), "requestAccepted")) {
            log(this, httpRequest.toString() + rsp);
            throw new RequestFailed();
        }
        return 0;
    }

    public void searchUser(String dn)
            throws CwaException {
        HttpRequest httpRequest = new HttpRequest();
        try {
            rsp = httpRequest.post(httpHeader, 
					searchRequest.build(sid, get_rid(), dn), 
					baseurls + "/cwa/MainCommandHandler.ashx");
        } catch (IOException ex) {
            log(this, ex);
            ex.printStackTrace(System.err);
            log(this, httpRequest.toString() + rsp);
            throw new RequestFailed();
        }
    }

    public void subscribePresence(String uri)
            throws CwaException {
        HttpRequest httpRequest = new HttpRequest();
        try {
            rsp = httpRequest.post(httpHeader, 
					subscribePresenceRequest.build(sid, get_rid(), uri), 
					baseurls + "/cwa/MainCommandHandler.ashx");
        } catch (IOException ex) {
            log(this, ex);
            ex.printStackTrace(System.err);
            log(this, httpRequest.toString() + rsp);
            throw new RequestFailed();
        }
    }

    public void queryPresense(List<String> uril)
            throws CwaException {
        HttpRequest httpRequest = new HttpRequest();
        try {
            rsp = httpRequest.post(httpHeader, 
					queryPresenseRequest.build(sid, get_rid(), uril), 
					baseurls + "/cwa/MainCommandHandler.ashx");
        } catch (IOException ex) {
            log(this, ex);
            ex.printStackTrace(System.err);
            log(this, httpRequest.toString() + rsp);
            throw new RequestFailed();
        }
    }

    private void initiateImSession(String uri, String msg)
            throws CwaException {
        HttpRequest httpRequest = new HttpRequest();
        int confid0 = get_confid();
        try {
            rsp = httpRequest.post(httpHeader, 
					initiateImSessionRequest.build(sid, get_rid(), confid0, uri, msg), 
					baseurls + "/cwa/MainCommandHandler.ashx");
        } catch (IOException ex) {
            log(this, ex);
            ex.printStackTrace(System.err);
            log(this, httpRequest.toString() + rsp);
            throw new RequestFailed();
        }
        log(this, "new IM Sesion: " + uri + " id: " + confid0);
        confs.put(uri, confid0);

//        log(this, httpRequest);
//        log(this, rsp);
    }

    public void sendMessage(String uri, String msg)
            throws CwaException {
            // see if a conf with the uri is still active?
        // else form a new im session
        Integer confid0 = (Integer) confs.get(uri);
        if (confid0 == null) {
            initiateImSession(uri, msg);
        } else {
            HttpRequest httpRequest = new HttpRequest();
            try {
                rsp = httpRequest.post(httpHeader, 
						sendMessageRequest.build(sid, get_rid(), confid0, msg), 
						baseurls + "/cwa/MainCommandHandler.ashx");
            } catch (IOException ex) {
                log(this, ex);
                ex.printStackTrace(System.err);
                log(this, httpRequest.toString() + rsp);
                throw new RequestFailed();
            }
            log(this, "new MSG to: " + uri + " msg: " + msg);
//            Logger.log(httpRequest);
//            Logger.log(rsp);
        }
    }

    public void terminateImSession(String uri) throws RequestFailed {
        Integer confid0 = (Integer) confs.get(uri);
        if (confid0 != null) {
            HttpRequest httpRequest = new HttpRequest();
            try {
                rsp = httpRequest.post(httpHeader, 
						terminateImSessionRequest.build(sid, get_rid(), confid0), 
						baseurls + "/cwa/MainCommandHandler.ashx");
            } catch (IOException ex) {
                Logger.log(ex);
                ex.printStackTrace(System.err);
                Logger.log(httpRequest.toString() + rsp);
                throw new RequestFailed();
            }
            Logger.log("terminated IM: " + uri + " id: " + confid0);
        }
    }

    public List<cwa.event.Event> getCwaEvents()
            throws CwaException {

        //Logger.log("getCwaEvents " + Thread.currentThread());
        HttpRequest httpRequest = new HttpRequest();
        try {
            rsp = httpRequest.get(httpHeader, 
					baseurls + "/cwa/AsyncDataChannel.ashx?Sid=" + sid + "&&AckID=" + get_ackid() + "&UA=false");
        } catch (IOException ex) {
            Logger.log(ex);
            ex.printStackTrace(System.err);
            Logger.log(httpRequest.toString() + rsp);
            throw new RequestFailed();
        }

        //Logger.log("[ response ] \n"+rsp);

        ackid = evtMan.getAckId(rsp.getContent());
        List<Event> evli = evtMan.getEvents(rsp.getContent());
        stateHelper.update(this, evli);
        return evli;
    }

    public List<cwa.event.Event> getCwaEvents(Class type)
            throws CwaException {
        return evtMan.filter(getCwaEvents(), type);
    }

    public List<cwa.event.Event> getCwaEvents(List<cwa.event.Event> eventList, Class type)
            throws CwaException {
        return evtMan.filter(eventList, type);
    }

    public List<Response> getCwaResponses()
            throws CwaException {
        HttpRequest httpRequest = new HttpRequest();
        try {
            rsp = httpRequest.get(httpHeader, 
					baseurls + "/cwa/AsyncDataChannel.ashx?Sid=" + sid + "&&AckID=" + get_ackid() + "&UA=false");
        } catch (IOException ex) {
            log(this, ex);
            ex.printStackTrace(System.err);
            log(this, httpRequest.toString() + rsp);
            throw new RequestFailed();
        }
        return rspFactory.getResponses(rsp.getContent());
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @return the uri
     */
    public String getUri() {
        return uri;
    }

    private boolean validateResponse(String content, String key) {
        if (content.contains(key)) {
            return true;
        }
        return false;
    }

    public static void log(Object obj, Object msg) {
        cwa.util.Logger.log(obj.getClass() + ": " + msg);
    }
}
