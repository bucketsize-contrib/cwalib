/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cwa.event;

import org.w3c.dom.Node;

/**
 *
 * @author JA34916
 */
public class imSessionState extends Event{
    private String state;

    public imSessionState(Node node) {
        state = node.getTextContent();
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString(){
        return this.getClass()+": "+state;
    }

}
