package cwa.request;

public class sendMessageRequest {
	public static String build(String sid, int rid, int confid, String msg){
		return("<cwaRequests xmlns=\"http://schemas.microsoft.com/2006/09/rtc/cwa\" sid=\"" + sid + "\">\n"
                + "<conference confId=\"" + confid + "\" rid=\"" + rid + "\">\n"
                + "		<sendMessage>"
                + "			<format>text/plain</format>\n"
                + "		        <content>" + msg + "</content>\n"
                + "		</sendMessage>\n"
                + "	</conference>\n"
                + "</cwaRequests>\n");
	}
}