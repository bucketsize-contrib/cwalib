/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cwa.event;

import cwa.util.NodeHelper;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Node;

/**
 *
 * @author JA34916
 */
public class subscribers extends Event {

    private List list;

    public subscribers(Node node) {

        list = new ArrayList();

        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            Node n = node.getChildNodes().item(i);
            if ( NodeHelper.isValid(n) ){
                //System.err.println("subsc: "+n.getAttributes().getNamedItem("uri").getNodeValue());
                list.add(NodeHelper.getAttribute(n, "uri"));
            }
        }
    }

    /**
     * @return the subscribers
     */
    public List getList() {
        return list;
    }

    @Override
    public String toString(){
        return this.getClass().toString();
    }
}
