/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cwa.event;

import cwa.util.NodeHelper;
import org.w3c.dom.Node;

/**
 *
 * @author JA34916
 */
public class conference extends Event {

    private int confid;
    private Event subevent;

    /**
     * @return the confid
     */
    public int getConfid() {
        return confid;
    }

    /**
     * @param confid the confid to set
     */
    public void setConfid(int confid) {
        this.confid = confid;
    }

    public conference(Node node) {

//        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
//            System.err.println(node.getChildNodes().item(i));
//        }

        confid = Integer.parseInt(NodeHelper.getAttribute(node, "confId"));
        Node node1 = NodeHelper.getNodeByName(node.getChildNodes(), "imSessionReceived");
        if (node1 != null) {
            subevent = new imSessionReceived(node1);
        }
        node1 = NodeHelper.getNodeByName(node.getChildNodes(), "messageReceived");
        if (node1 != null) {
            subevent = new messageReceived(node1);
        }
        node1 = NodeHelper.getNodeByName(node.getChildNodes(), "imSessionState");
        if (node1 != null) {
            subevent = new imSessionState(node1);
        }
        node1 = NodeHelper.getNodeByName(node.getChildNodes(), "composingState");
        if (node1 != null) {
            subevent = new imSessionState(node1);
        }
    }

    @Override
    public String toString(){
        return this.getClass()+": " + getSubevent();
    }

    /**
     * @return the subevent
     */
    public Event getSubevent() {
        return subevent;
    }

    /**
     * @param subevent the subevent to set
     */
    public void setSubevent(Event subevent) {
        this.subevent = subevent;
    }
}
