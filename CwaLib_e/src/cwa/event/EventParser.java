/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cwa.event;

import cwa.util.Logger;

import org.w3c.dom.*;

import java.util.List;
import java.util.ListIterator;

import java.util.ArrayList;

import cwa.util.NodeHelper;
/**
 *
 * @author JA34916
 */
public class EventParser {

	public int getAckId(String message){
		Document dom = NodeHelper.instance().build(message);
        if (dom == null) {  return 0; }
        NodeList nl0 = dom.getElementsByTagName("cwaEvents");
        String ackId = NodeHelper.getAttribute(nl0.item(0), "ackId");
        Logger.log("ackId: " + ackId);
        int ackid = Integer.parseInt(ackId);
        cwa.util.Logger.log("ackid: "+ackid);
        return ackid;
	}

	public List getEvents(String message){
		ArrayList list = new ArrayList();
        Document dom = NodeHelper.instance().build(message);

        if (dom == null) { return list; }

        NodeList nl0 = dom.getElementsByTagName("cwaEvents"); //cwaEvents nodes
        for (int in0 = 0; in0 < nl0.getLength(); in0++) {
            NodeList nl = nl0.item(in0).getChildNodes(); //sub-cwaEvents nodes
            cwa.util.Logger.log("[events]");
            for (int in = 0; in < nl.getLength(); in++) {
                if (!nl.item(in).getNodeName().equals("#text")) {
                    Event evt = buildEvent(nl.item(in));
                    list.add(evt);
                }
            }
        }
        return list;	
	}
	
    private Event buildEvent(Node node) {
		Event evt = new unKnown(node);;
        if (node.getNodeName().equals("subscribers")){
            evt = new subscribers(node);
        }
        if (node.getNodeName().equals("userPresence")){
            evt = new userPresence(node);
        }
        if (node.getNodeName().equals("conference")){
            evt = new conference(node);
        }
        if (node.getNodeName().equals("contactGroup")){
            evt = new contactGroup(node);
        }
		cwa.util.Logger.log("Spawning Event: "+evt.toString());
        return evt;
    }
}
