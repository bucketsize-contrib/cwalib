/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jbapps.responders;

import jbapps.ui.IMFrameUI;
import jbapps.model.User;
import cwa.event.Event;
import cwa.event.conference;
import cwa.event.imSessionReceived;
import cwa.event.messageReceived;
import jbapps.App;

/**
 *
 * @author jb
 */
public class conferenceResponder extends CwaEventResponder {

    public conferenceResponder(App app, Event evt) {
        super(app, evt);
    }

    @Override
    public void run() {
        conference e = (conference) evt;
        if (e.getSubevent() != null) {
            if (e.getSubevent().getClass() == imSessionReceived.class) {
                respondTo((imSessionReceived) e.getSubevent());
            }
            if (e.getSubevent().getClass() == messageReceived.class) {
                respondTo((messageReceived) e.getSubevent());
            }
        }
    }

    private void respondTo(imSessionReceived se) {
        User inviter = app.subscriberTableModel.get(se.getInviter());
        app.createIMDialog(inviter);
    }

    private void respondTo(messageReceived se) {
        User sender = app.subscriberTableModel.get(se.getSender());
        IMFrameUI myd = app.imDialogs.get(sender);
        myd.updateDialog(")) "+se.getMessage());
    }
}
