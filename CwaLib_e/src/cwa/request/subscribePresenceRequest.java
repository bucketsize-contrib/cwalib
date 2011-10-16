package cwa.request;

public class subscribePresenceRequest{
	public static String build(String sid, int rid, String uri){
		return("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<cwaRequests sid=\"" + sid + "\" xmlns=\"http://schemas.microsoft.com/2006/09/rtc/cwa\">\n"
                + " <subscribePresence rid=\"" + rid + "\">\n"
                + "     <uris>\n"
                + "         <uri>sip:" + uri + "</uri>\n"
                + "     </uris>\n"
                + " </subscribePresence>\n"
                + "</cwaRequests>\n");
	}
}