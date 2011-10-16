package cwa.request;

import java.util.List;
import java.util.ListIterator;

public class queryPresenseRequest {
	public static String build(String sid, int rid, List<String> uril){
		ListIterator<String> li = uril.listIterator();
        String s = null;
        s = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<cwaRequests xmlns=\"http://schemas.microsoft.com/2006/09/rtc/cwa\" sid=\"" + sid + "\">\n"
                + " <queryPresence rid=\"" + rid + "\">\n"
                + "     <uris>\n";

        s = s + "       <uri>" + "sip:foo.bar@go.in" + "</uri>\n";

        while (li.hasNext()) {
            s = s + "       <uri>" + li.next() + "</uri>\n";
        }

        s = s + "   </uris>\n"
                + " </queryPresence>\n"
                + "</cwaRequests>\n";
		return(s);
	}
}