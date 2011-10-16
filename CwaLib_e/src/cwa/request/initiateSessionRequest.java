package cwa.request;

public class initiateSessionRequest {
	public static String build(String signInData){
			return ("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<cwaRequests sid=\"0\" xmlns=\"http://schemas.microsoft.com/2006/09/rtc/cwa\">\n"
                + " <initiateSession rid=\"1\">\n"
                + "   <securityMode>private</securityMode>\n"
                + "     <userStateAvailability>12500</userStateAvailability>\n"
                + "     <options autoPublishMachineState=\"true\" />\n"
                + "     <signInData>" + signInData + "</signInData>\n"
                + " </initiateSession>\n"
                + "</cwaRequests>\n");
	}
}