/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cwa.util;

import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.parsers.*;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JA34916
 */
public class NodeHelper {

    private static NodeHelper instance = null;
    private DocumentBuilder builder;

    private NodeHelper() {
        try {
            DocumentBuilderFactory dbf =
                    DocumentBuilderFactory.newInstance();
            builder = dbf.newDocumentBuilder();
        } catch (Exception e) {
            Logger.log(e.toString());
        }
    }

    public static NodeHelper instance() {
        if (instance == null) {
            instance = new NodeHelper();
        }
        return instance;
    }

    public static List<Node> getNodesByAttrib(NodeList nl, String attname, String cont) {
        List<Node> nnl = new ArrayList<Node>();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (isValid(node)) {
                if (node.getAttributes().getNamedItem(attname) != null) {
                    if (node.getAttributes().getNamedItem(attname).toString().contains(cont)) {
                        nnl.add(node);
                    }
                }
            }
        }
        return nnl;
    }

    public static List<Node> getNodesByName(NodeList nl, String name) {
        List<Node> nnl = new ArrayList<Node>();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (isValid(node)) {
                if (node.getNodeName().toString().contains(name)) {
                    nnl.add(node);
                }
            }
        }
        return nnl;
    }

    public static Node getNodeByName(NodeList nl, String name) {
        for (int i = 0; i < nl.getLength(); i++) {
            if (nl.item(i).getNodeName().equals(name)) {
                return nl.item(i);
            }
        }
        return null;
    }

    public static String getAttribute(Node n, String key) {
        Node nn = n.getAttributes().getNamedItem(key);
        if (nn != null) {
            return nn.getNodeValue();

        }
        return null;
    }

    public static boolean isValid(Node node) {
        return !node.getNodeName().equals("#text");
    }

    public Document build(String xml) {
        if (xml == null) {
            return null;
        }
        Document dom1 = null;
        try {
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(xml));

            /////          critical section        /////
            //Logger.log("parse called from: "+Thread.currentThread());
            dom1 = builder.parse(is);
            /////        end critical section      /////

        } catch (SAXException ex) {
            Logger.log(ex);
            Logger.log("[ XML Text ]\n" + xml);
        } catch (Exception ex) {
            Logger.log(ex.toString());
        }
        return dom1;
    }
}
