package cwa.request;

public class logonRequest {
	public static String build(String user, String pass){
		return ("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<cwaRequests xmlns=\"http://schemas.microsoft.com/2006/09/rtc/cwa\">\n"
                + " <logon>\n"
                + "     <user>" + user + "</user>\n"
                + "     <password>" + pass + "</password>\n"
                + " </logon>\n"
                + "</cwaRequests>\n");
	}
}