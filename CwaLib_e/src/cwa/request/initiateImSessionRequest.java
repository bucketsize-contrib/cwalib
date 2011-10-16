package cwa.request;

public class initiateImSessionRequest{
	public static String build(String sid, int rid, int confid, String uri, String msg){
		return("<cwaRequests xmlns=\"http://schemas.microsoft.com/2006/09/rtc/cwa\" sid=\"" + sid + "\">\n"
                + " <conference confId=\""+confid+"\" rid=\"" + rid + "\">\n"
                + "  <initiateImSession>\n"
                + "   <uris>\n"
                + "    <uri>" + uri + "</uri>\n"
                + "   </uris>\n"
                + "   <message>\n"
                + "    <format>text/plain</format>\n"
                + "    <content>" + msg + "</content>\n"
                + "   </message>\n"
                + "  </initiateImSession>\n"
                + " </conference>\n"
                + "</cwaRequests>\n");
	}
}