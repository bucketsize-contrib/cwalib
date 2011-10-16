package cwa.request;

public class terminateImSessionRequest {
	public static String build(String sid, int rid, int confid){
		return("<cwaRequests xmlns=\"http://schemas.microsoft.com/2006/09/rtc/cwa\" sid=\"" + sid + "\">\n"
                + "	<conference confId=\"" + confid + "\" rid=\"" + rid + "\">\n"
                + "		<terminateImSession/>"
                + "	</conference>\n"
                + "</cwaRequests>\n");
	}
}
