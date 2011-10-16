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
public abstract class Event {
    private Node node;

    /**
     * @return the node
     */
    public Node getNode() {
        return node;
    }

    /**
     * @param node the node to set
     */
    public void setNode(Node node) {
        this.node = node;
    }

}

