
package org.heavywater.HttpMini;

import java.util.Map;
import java.util.HashMap;


public class HttpHeader {

    private Map properties;

    public HttpHeader() {
        properties = new HashMap();
        // default headers
        setProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.2.9) Gecko/20100824 Firefox/3.6.9 (.NET CLR 3.5.30729)");
        setProperty("Accept", "text/plain");
        setProperty("Keep-Alive", "115");
        setProperty("Connection", "keep-alive");
    }

    /**
     * @return the properties
     */
    public Map getProperties() {
        return properties;
    }

    /**
     * @param properties the properties to set
     */
    public void setProperties(Map properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        String s = "";
        for (Object hk : properties.keySet()) {
            s += hk + ": " + properties.get(hk) + "\n";
        }
        return s;
    }

    public String getProperty(String pk){
        return (String) properties.get(pk);
    }

    public final void setProperty(String pk, String  pv){
        properties.put(pk, pv);
    }
}
