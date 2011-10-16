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
public class imSessionReceived  extends Event {
    private String inviter;
    private String message;

    public imSessionReceived(Node node){
        Node n = NodeHelper.getNodeByName(node.getChildNodes(), "inviter");
        inviter = NodeHelper.getAttribute(n, "uri");

        n = NodeHelper.getNodeByName(node.getChildNodes(), "message");
        n = NodeHelper.getNodeByName(n.getChildNodes(), "content");
        message = n.getTextContent();
    }

    /**
     * @return the inviter
     */
    public String getInviter() {
        return inviter;
    }

    /**
     * @param inviter the inviter to set
     */
    public void setInviter(String inviter) {
        this.inviter = inviter;
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
        return this.getClass()+": "+inviter+": "+message;
    }
}
