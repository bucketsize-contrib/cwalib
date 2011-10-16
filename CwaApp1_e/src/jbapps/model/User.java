/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jbapps.model;

/**
 *
 * @author Jantu
 */
public class User {

    private String uri;
    private String name;
    private String group;
    private String status;
    private String color;
    private int _status;

    public User(String _grp, String _sip, int _status_) {
        group = _grp;
        uri = _sip;
        _status = _status_;
        name = uri.split(":")[1].split("@")[0];
        status = "( unknown )";
    }

    @Override
    public String toString() {
        return name;
    }

    public void update(int _status_) {
        setStatus(_status_);

        color = "#A4A4A4";
        status = "unknown";
        if (getStatus() == 3500) {
            color = "green";
            status = "online";
        }
        if (getStatus() == 4500) {
            color = "yellow";
            status = "online-idle";
        }
        if (getStatus() == 6500) {
            color = "red";
            status = "busy";
        }
        if (getStatus() == 7500) {
            color = "yellow";
            status = "busy-idle";
        }
        if (getStatus() == 9500) {
            color = "#8A0808";
            status = "do not disturb";
        }
        if (getStatus() == 12500) {
            color = "yellow";
            status = "be right back";
        }
        if (getStatus() == 15500) {
            color = "yellow";
            status = "away";
        }
        if (getStatus() == 18000) {
            color = "#FF8000";
            status = "offline";
        }


    }

    public String getUri() {
        return uri;
    }

    public void setUri(String sip) {
        this.uri = sip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayString() {

        return "<html>"
                + "<font>" + name + "</font>"
                + "<font color=\"" + color + "\"> (" + status + ") </font>"
                + "</html>";
    }

    public String getDisplayStatus() {

        return "<html>"
                + "<font color=\"" + color + "\">( " + status + ") </font>"
                + "</html>";
    }

    public int getStatus() {
        return _status;
    }

    public void setStatus(int status) {
        this._status = status;
    }

    /**
     * @return the group
     */
    public String getGroup() {
        return group;
    }

    /**
     * @param group the group to set
     */
    public void setGroup(String group) {
        this.group = group;
    }
}
