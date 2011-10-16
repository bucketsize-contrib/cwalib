package cwa.event;

import cwa.util.Logger;

import java.util.List;
import java.util.ListIterator;

public class EventManager {

    private EventParser eventParser;

    public EventManager() {
        eventParser = new EventParser();
    }

    public List getEvents(String message) {
		return eventParser.getEvents(message);        
    }

    public List filter(List list, Class type) {
        System.err.println(list.size());
        ListIterator li = list.listIterator();

        while (li.hasNext()) {
            Class evtype = li.next().getClass();
            if (evtype != type) {
                li.remove();
            }
        }
        return list;
    }

    public int getAckId(String content) {
		return eventParser.getAckId(content);        
    }
}
