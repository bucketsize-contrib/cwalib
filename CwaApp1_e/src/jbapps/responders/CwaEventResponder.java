/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jbapps.responders;

import cwa.event.Event;
import jbapps.App;

/**
 *
 * @author JA34916
 */
public abstract class CwaEventResponder implements Runnable{

    protected  App app;
    protected  Event evt;

    public CwaEventResponder(App _app, Event _evt){
        app = _app; evt = _evt;
    }
}
