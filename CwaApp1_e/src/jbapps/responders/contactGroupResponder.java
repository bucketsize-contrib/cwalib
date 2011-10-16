/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jbapps.responders;

import cwa.event.Event;
import cwa.event.contactGroup;
import jbapps.App;

/**
 *
 * @author JA34916
 */
public class contactGroupResponder extends CwaEventResponder {

    public contactGroupResponder(App app, Event evt) {
        super(app, evt);
    }

    @Override
    public void run() {
        contactGroup e = (contactGroup) evt;
        for (Object k : e.getContacts().keySet()) {
            if (!app.subscriberTableModel.contains((String) k)) {
                app.subscriberTableModel.add((String) e.getContacts().get(k), (String) k, 0);
            }
        }
        app.ui.subscriberTable.updateUI();
        app.updateStatusUI();
    }
}
