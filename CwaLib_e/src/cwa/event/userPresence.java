/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cwa.event;

import cwa.util.NodeHelper;
import java.util.HashMap;
import java.util.Map;
import org.w3c.dom.Node;

/**
 *
 * @author JA34916
 */
public class userPresence extends Event {
    // -1,0,1,2,3 : unav, away, avail, busy, do_not_disturb

    private Map map;

    public userPresence(Node node) {

        map = new HashMap();

        String sip = null;
        int avi = -1;

        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            Node child = (Node) node.getChildNodes().item(i);

            if (!NodeHelper.isValid(child)) continue;
            

            sip = child.getAttributes().getNamedItem("uri").toString();

            //System.err.println(sip.split("\"")[1]);

            for(int j =0; j<child.getChildNodes().getLength(); j++){
                Node cchild = (Node) child.getChildNodes().item(j);

                if (!NodeHelper.isValid(cchild)) continue;
                //System.err.println(cchild.getAttributes().getNamedItem("name"));

                if ( cchild.getAttributes().getNamedItem("name").toString().contains("state") ) {
                    //System.err.println("got state");
                    avi = Integer.parseInt(cchild.getChildNodes().item(1).getTextContent());
                    //System.err.println("up: " + avi);
                    break;
                }
            }
            map.put(sip.split("\"")[1], avi);
        }
        //System.err.println(map);

    }

    /**
     * @return the avMap
     */
    public Map getMap() {
        return map;
    }

    @Override
    public String toString(){
        return this.getClass().toString();
    }
}
