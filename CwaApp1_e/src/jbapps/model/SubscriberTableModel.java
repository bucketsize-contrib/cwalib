/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jbapps.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class SubscriberTableModel extends AbstractTableModel {

    private List<User> userList;
    private String[] cols = {"Group", "User", "Status"};

    public SubscriberTableModel() {
        userList = new ArrayList<User>();
    }

    public void add(String _grp, String _sip, int _status) {
        userList.add(new User(_grp, _sip, _status));
        //System.err.println("Added: " + _sip);
    }

    public boolean contains(String _sip) {
        for (User u : userList) {
            if (u.getUri().equals(_sip)) {
                return true;
            }
        }
        return false;
    }

    public void update(String _sip, int _status) {
        for (User u : userList) {
            if (u.getUri().equals(_sip)) {
                u.update(_status);
                //System.err.println("Updated: " + u.getName());
            }
        }
    }

    public void clear() {
        userList.clear();
    }

    public User get(String uri) {
        return get(getIndexOfUri(uri));
    }

    public User get(int i) {
        return userList.get(i);
    }

    public int getIndexOfUri(String uri) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUri().equals(uri)) {
                return i;
            }
        }
        return -1;
    }

    ///////////////////////////////////////////////////
    @Override
    public Object getValueAt(int i, int j) {
        if (j == 0) {
            return userList.get(i).getGroup();
        } else if (j == 1) {
            return userList.get(i).getName();
        } else if (j == 2) {
            return userList.get(i).getDisplayStatus();
        } else {
            return null;
        }
    }

    @Override
    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public int getRowCount() {
        return userList.size();
    }

    @Override
    public String getColumnName(int col) {
        return cols[col];
    }
    ///////////////////////////////////////////////////

    public String[] getCols() {
        return cols;
    }
}
