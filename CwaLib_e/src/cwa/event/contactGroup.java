/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cwa.event;

import cwa.util.Logger;
import cwa.util.NodeHelper;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import org.w3c.dom.Node;

/**
 *
 * @author JA34916
 */
public class contactGroup extends Event {

    private Map groups;
    private Map contacts;

    public contactGroup(Node node) {
        //get groups
        List nl = NodeHelper.getNodesByName(node.getChildNodes(), "group");
        ListIterator nli = nl.listIterator();
        groups = new HashMap();
        while (nli.hasNext()) {
            Node n = (Node) nli.next();
            groups.put(Integer.parseInt(NodeHelper.getAttribute(n, "id")),
                    NodeHelper.getAttribute(n, "name"));

        }
        Logger.log("[ groups ]:\n" + groups);

        //get contacts
        nl = NodeHelper.getNodesByName(node.getChildNodes(), "contact");
        nli = nl.listIterator();
        contacts = new HashMap();
        while (nli.hasNext()) {
            Node n = (Node) nli.next();
            String uri = NodeHelper.getAttribute(n, "uri");
            String s = NodeHelper.getAttribute(n, "groups");
            if (s != null) {
                //Logger.log("cool: " + uri + " ... " + s);
                try {
                    s = s.split(" ")[1];
                } catch (ArrayIndexOutOfBoundsException ex) {
                    s = "1";
                }
                int gid = Integer.parseInt(s);
                contacts.put(uri, groups.get(gid));
            }
        }
        Logger.log("[ contacts ]:\n" + contacts);
    }

    /**
     * @return the contacts
     */
    public Map getContacts() {
        return contacts;
    }
}
