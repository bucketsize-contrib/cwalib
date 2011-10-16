package org.heavywater.HttpMini;

import java.net.URL;
import java.net.URLConnection;

import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Date;

public class HttpRequest {
    public HttpRequest() {}
    
    public HttpResponse post(HttpHeader h, String c, String urls) throws java.io.IOException {
        //setup connection
        URL url = new URL(urls);
        URLConnection uc = url.openConnection();
        uc.setDoOutput(true);
        //setup headers
        for (Object hk : h.getProperties().keySet()) {
            uc.setRequestProperty((String) hk, (String) h.getProperties().get(hk));
        }
        //send
        OutputStreamWriter out = new OutputStreamWriter(uc.getOutputStream());
        out.write(c);
        out.close();
        //read response
        HttpResponse response = new HttpResponse();
        response.setHeader(getResponseHeaders(uc));
        response.setContent(getResponseContent(uc));

        return response;
    }

    private String getResponseContent(URLConnection urlc) throws java.io.IOException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                urlc.getInputStream()));

        String s = "", line = null;

        while ((line = in.readLine()) != null) {
            s += line + '\n';
        }
        in.close();
        return s;
    }

    public HttpResponse get(HttpHeader h, String urls) throws java.io.IOException {
        //setup connection
        URL url = new URL(urls);
        URLConnection uc = url.openConnection();
        //setup headers
        for (Object hk : h.getProperties().keySet()) {
            //CwaLogger.log(hk + ": " + h.getProperties().get(hk));
            uc.setRequestProperty((String) hk, (String) h.getProperties().get(hk));
        }
        //read response
        HttpResponse response = new HttpResponse();
        response.setHeader(getResponseHeaders(uc));
        response.setContent(getResponseContent(uc));

        return response;
    }

    private HttpHeader getResponseHeaders(URLConnection urlc) {
        HttpHeader h = new HttpHeader();
        for (int i = 0;; i++) {
            String headerName = urlc.getHeaderFieldKey(i);
            String headerValue = urlc.getHeaderField(i);
            if (headerName == null && headerValue == null) {
                // No more headers
                return h;
            }
            if (headerName == null) {
                // The header value contains the server's HTTP version
                h.getProperties().put("server", headerValue);
            }
            h.getProperties().put(headerName, headerValue);
        }
    }


    @Override
    public String toString() {
        Date date = new Date();
        return date.toString() + '\n';
    }

    public void print() {
        System.err.println(this);
    }

}
