package cwa.request;

public class terminateSessionRequest {
	public static String build(String sid, int rid){
		return("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<cwaRequests sid=\"" + sid + "\" xmlns=\"http://schemas.microsoft.com/2006/09/rtc/cwa\">\n"
                + " <terminateSession rid=\"" + rid + "\" />\n"
                + "</cwaRequests>\n");
	}
}