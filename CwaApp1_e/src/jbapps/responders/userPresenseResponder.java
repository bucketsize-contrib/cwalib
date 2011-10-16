/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jbapps.responders;

import cwa.event.*;
import java.util.Map.Entry;
import jbapps.App;

/**
 *
 * @author JA34916
 */
public class userPresenseResponder extends CwaEventResponder {

    public userPresenseResponder(App app, Event evt) {
        super(app, evt);
    }

    @Override
    public void run() {
        userPresence e = (userPresence) evt;
        for (Object _ent : e.getMap().entrySet()) {
            Entry ent = (Entry) _ent;
            app.subscriberTableModel.update(ent.getKey().toString(), Integer.parseInt(ent.getValue().toString()));
        }
        app.ui.subscriberTable.updateUI();
        app.updateStatusUI();
    }
}
