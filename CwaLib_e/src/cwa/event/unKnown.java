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
public class unKnown extends Event {

    public unKnown(Node node){
        super.setNode(node);
    }
    @Override
    public String toString(){
        return this.getClass().toString() +": "+ super.getNode().getNodeName();
    }
}
