/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jbapps;

import cwa.exception.RequestFailed;
import jbapps.responders.EventResponseDispacher;
import jbapps.ui.IMFrameUI;
import jbapps.ui.AppUI;
import jbapps.model.User;
import cwa.Client;
import cwa.event.Event;
import cwa.exception.CwaException;
import cwa.util.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.SwingUtilities;
import jbapps.model.SubscriberTableModel;

/**
 *
 * @author ja34916
 */
public class App {

    public AppUI ui;
    private Client cwaClient;
    public SubscriberTableModel subscriberTableModel;
    private Timer sltimer;
    private User currentSubscriber, loggedInUser;
    public Map<User, IMFrameUI> imDialogs;

    public App(AppUI _ui) {
        ui = _ui;

        subscriberTableModel = new SubscriberTableModel();
        ui.subscriberTable.setModel(subscriberTableModel);

        imDialogs = new HashMap<User, IMFrameUI>();
    }

    private void respondTo(Event evt) {
        EventResponseDispacher.respondTo(this, evt);
    }

    public void inviteIMDialog() {
        if (currentSubscriber != null) {
            createIMDialog(currentSubscriber);
            return;
        }
        IMFrameUI cui = new IMFrameUI(this);
        cui.setVisible(true);
    }

    public void createIMDialog(User user) {
        IMFrameUI cui = new IMFrameUI(this);

        cui.setUser(user);
        cui.chatUserLabel1.setText(ui.loggedInUserLabel.getText());
        cui.setTitle(user.getName());
        cui.chatUserLabel2.setText(user.getDisplayString());
        cui.setVisible(true);

        imDialogs.put(user, cui);
    }

    public void closeIMDialog(User user) {
        if (cwaClient != null) {
            try {
                cwaClient.terminateImSession(user.getUri());
            } catch (RequestFailed ex) {
                cwaClient.log(this, ex);
            }
        }
    }

    public void updateStatusUI() {
        if (loggedInUser == null) {
            int i = subscriberTableModel.getIndexOfUri(cwaClient.getUri());
            loggedInUser = subscriberTableModel.get(i);
        } else {
            ui.loggedInUserLabel.setText(loggedInUser.getDisplayString());
        }
    }

    private void queryPresenseSubscriptions() {
        List<String> sipList = new ArrayList<String>();
        for (int i = 0; i < subscriberTableModel.getRowCount(); i++) {
            String s = (String) subscriberTableModel.get(i).getUri();
            sipList.add(s.split(" ")[0]);
        }
        try {
            cwaClient.queryPresense(sipList);
        } catch (CwaException ex) {
            cwaClient.log(this, ex);
        }
    }

    public void login() {
        try {
            // TODO add your handling code here:
            String user = ui.userTextField.getText();
            String pass = String.valueOf(ui.passField.getPassword());
            String url = ui.urlTextField.getText();
            cwaClient = new Client();
            if (cwaClient.xconnect(user, pass, url) == 1) {
                if (cwaClient.logon(user, pass) == 1) {
                    if (cwaClient.initiateSession() == 1) {
                        if (cwaClient.getUri() != null) {
                            ui.loggedInUserLabel.setText(cwaClient.getUri());
                            ui.loginButton.setText("logout");
                            ui.urlTextField.setEnabled(false);
                            ui.userTextField.setEnabled(false);
                            ui.passField.setEnabled(false);
                            return;
                        }
                    }
                }
            }
            ui.loggedInUserLabel.setText("(o_O) unable!");
            cwaClient = null;
            return;
        } catch (CwaException ex) {
            cwaClient.log(this, ex);
        }
    }

    public void logout() {
        try {
            cwaClient.terminateSession();
        } catch (CwaException ex) {
            cwaClient.log(this, ex);
        }
        sltimer.cancel();
        cwaClient = null;
        subscriberTableModel.clear();
        ui.subscriberTable.updateUI();

        ui.urlTextField.setEnabled(true);
        ui.userTextField.setEnabled(true);
        ui.passField.setEnabled(true);

        ui.loginButton.setText("login!");
        ui.loggedInUserLabel.setText("(-_-)");

    }

    public void sendIM(User user, String msg) {
        try {
            cwaClient.sendMessage(user.getUri(), msg);
        } catch (CwaException ex) {
            cwaClient.log(this, ex);
        }
    }

    public void startSubcriberListDaemon() {
        TimerTask subscriberEventListner = new TimerTask() {

            @Override
            public void run() {
                // listen to events and update state
                List<Event> eventList = null;
                try {
                    eventList = cwaClient.getCwaEvents();
                } catch (Exception ex) {
                    ex.printStackTrace(System.err);
                    cwaClient.log(this, ex);
                    return;
                }
                ListIterator<Event> eli = eventList.listIterator();
                // despatch event responses
                while (eli.hasNext()) {
                    respondTo(eli.next());
                }
                return;
            }
        };
        sltimer = new Timer();
        sltimer.scheduleAtFixedRate(subscriberEventListner, 1000, 3000);
        cwaClient.log(this, "startSubcriberListDaemon");
    }

    public void setSubscriber(int j) {
        int index = ui.subscriberTable.convertRowIndexToModel(j);
        currentSubscriber = subscriberTableModel.get(index);
        Logger.log("currentSubscriber: " + currentSubscriber);
    }

    public static void main(String args[]) {    	
    	
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                AppUI ui = new AppUI();
                App app = new App(ui);

                ui.setApp(app);
                ui.setVisible(true);
            }
        });

    }
}
