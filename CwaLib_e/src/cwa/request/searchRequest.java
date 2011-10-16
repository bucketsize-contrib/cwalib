package cwa.request;

public class searchRequest{
	public static String build(String sid, int rid, String dn){
		return("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<cwaRequests sid=\"" + sid + "\" xmlns=\"http://schemas.microsoft.com/2006/09/rtc/cwa\">\n"
                + " <search rid=\"" + rid + "\">\n"
                + "     <firstName></firstName>\n"
                + "     <lastName></lastName>\n"
                + "     <displayName>" + dn + "</displayName>\n"
                + "     <emailAlias></emailAlias>\n"
                + "     <function>or</function>\n"
                + " </search>\n"
                + "</cwaRequests>\n");
	}
}