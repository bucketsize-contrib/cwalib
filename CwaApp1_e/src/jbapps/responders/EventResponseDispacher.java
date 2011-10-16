/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jbapps.responders;

import cwa.event.Event;
import cwa.event.conference;
import cwa.event.contactGroup;
import cwa.event.userPresence;
import javax.swing.SwingUtilities;
import jbapps.App;

public class EventResponseDispacher {
    public static void respondTo(App app, Event event) {
        
        if (event.getClass() == userPresence.class) {
            SwingUtilities.invokeLater(new userPresenseResponder(app, event));
        }
        if (event.getClass() == conference.class) {
            SwingUtilities.invokeLater(new conferenceResponder(app, event));
        }
        if (event.getClass() == contactGroup.class) {
            SwingUtilities.invokeLater(new contactGroupResponder(app, event));
        }
    }
}
