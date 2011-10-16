/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cwa.event;

import cwa.util.NodeHelper;
import org.w3c.dom.Node;


/**
 *
 * @author Jantu
 */
public class messageReceived extends Event{
    private String sender;
    private String message;

    public messageReceived(Node node){
        sender = NodeHelper.getAttribute(node, "sender");

        Node n = NodeHelper.getNodeByName(node.getChildNodes(), "message");
        n = NodeHelper.getNodeByName(n.getChildNodes(), "content");
        message = n.getTextContent();
      
    }

    /**
     * @return the sender
     */
    public String getSender() {
        return sender;
    }

    /**
     * @param sender the sender to set
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString(){
        return this.getClass()+": "+sender+": "+message;
    }
}
