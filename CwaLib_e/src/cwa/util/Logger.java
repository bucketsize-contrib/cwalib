/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cwa.util;

/**
 *
 * @author JA34916
 */
public class Logger {

    public static void log(Object msg) {
        System.err.println(msg.toString());
        //java.util.logging.Logger.getLogger("cwaLogger.log").log(Level.INFO,
        //        "{0}\n=================================", msg);
    }

    
}
