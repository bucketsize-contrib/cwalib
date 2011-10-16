/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cwa.util;

import cwa.Client;
import cwa.event.*;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author JA34916
 */
public class ClientStateHelper {
    public synchronized void update(Client aThis, List<Event> evli) {
        ListIterator<Event> eli = evli.listIterator();
        while (eli.hasNext()) {
            Event e = eli.next();
            if (e.getClass() == conference.class) {
                conference c = (conference) e;
                if (c.getSubevent() != null) {
                    if (c.getSubevent().getClass() == imSessionReceived.class) {
                        imSessionReceived imse = (imSessionReceived) c.getSubevent();
                        aThis.confs.put(imse.getInviter(), c.getConfid());
                    }
                    if (c.getSubevent().getClass() == messageReceived.class) {
                        messageReceived imse = (messageReceived) c.getSubevent();
                        aThis.confs.put(imse.getSender(), c.getConfid());
                    }
                    if (c.getSubevent().getClass() == imSessionState.class){
                        imSessionState imse = (imSessionState) c.getSubevent();
                        for(Object s: aThis.confs.keySet()){
                            if ( (Integer) aThis.confs.get(s) == c.getConfid() ){
                                aThis.confs.remove(s);
                                Logger.log("removed conf: "+s+" id: "+c.getConfid());
                            }
                        }
                    }
                    aThis.confid = Helper.max(aThis.confs.values());
                    Logger.log("confId updated to "+aThis.confid);
                }
            }
        }
    }
}