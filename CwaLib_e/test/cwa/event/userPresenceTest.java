/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cwa.event;

import org.w3c.dom.Document;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author JA34916
 */
public class userPresenceTest {

    public userPresenceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getMap method, of class userPresence.
     */
    @Test
    public void testGetMap() {
        String sxml = "<userPresence eid=\"11\">"
+"    <user uri=\"sip:Kasturi_Rao@satyam.com\" sourceNetwork=\"sameEnterprise\">"
+"      <cwaCategory name=\"calendarData\" instance=\"1155267548\" version=\"0\" publishTime=\"2010-09-01T03:00:37Z\">"
+"        <calendarData xmlns=\"http://schemas.microsoft.com/2006/09/sip/calendarData\" mailboxID=\"Kasturi_Rao@mahindrasatyam.com\"><freeBusy startTime=\"2010-08-30T18:30:00Z\" granularity=\"PT15M\" encodingVersion=\"1\">AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA</freeBusy></calendarData>"
+"      </cwaCategory>"
+"      <cwaCategory name=\"calendarData\" instance=\"0\" version=\"0\" publishTime=\"2009-11-27T11:08:57Z\">"
+"        <calendarData xmlns=\"http://schemas.microsoft.com/2006/09/sip/calendarData\" mailboxID=\"Kasturi_Rao@mahindrasatyam.com\"><WorkingHours xmlns=\"http://schemas.microsoft.com/exchange/services/2006/types\"><TimeZone><Bias>-330</Bias><StandardTime><Bias>0</Bias><Time>00:00:00</Time><DayOrder>0</DayOrder><Month>0</Month><DayOfWeek>Sunday</DayOfWeek></StandardTime><DaylightTime><Bias>0</Bias><Time>00:00:00</Time><DayOrder>0</DayOrder><Month>0</Month><DayOfWeek>Sunday</DayOfWeek></DaylightTime></TimeZone><WorkingPeriodArray><WorkingPeriod><DayOfWeek>Monday Tuesday Wednesday Thursday Friday</DayOfWeek><StartTimeInMinutes>480</StartTimeInMinutes><EndTimeInMinutes>1020</EndTimeInMinutes></WorkingPeriod></WorkingPeriodArray></WorkingHours></calendarData>"
+"      </cwaCategory>"
+"      <cwaCategory name=\"contactCard\" instance=\"0\" version=\"0\" publishTime=\"2010-06-08T15:32:20Z\">"
+"        <contactCard xmlns=\"http://schemas.microsoft.com/2006/09/sip/contactcard\"><identity><name><displayName>"
+"Kasturi_Rao</displayName></name><email>"
+"Kasturi_Rao@mahindrasatyam.com</email></identity></contactCard>"
+"      </cwaCategory>"
+"      <cwaCategory name=\"contactCard\" instance=\"2\" version=\"0\" publishTime=\"2010-06-09T07:06:05Z\">"
+"        <contactCard xmlns=\"http://schemas.microsoft.com/2006/09/sip/contactcard\"><address type=\"work\"><street>Gachibowli</street><city>HYDERABAD</city><state>AP</state><countryCode>IN</countryCode></address></contactCard>"
+"      </cwaCategory>"
+"      <cwaCategory name=\"contactCard\" instance=\"3\" version=\"0\" publishTime=\"2010-06-10T07:19:33Z\">"
+"        <contactCard xmlns=\"http://schemas.microsoft.com/2006/09/sip/contactcard\"><company>SCSL</company><title>Band Bi</title><office>HSZ9</office></contactCard>"
+"      </cwaCategory>"
+"      <cwaCategory name=\"note\" />"
+"      <cwaCategory name=\"state\" instance=\"1\" version=\"0\" publishTime=\"2010-09-01T06:28:10Z\">"
+"        <state xsi:type=\"aggregateState\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://schemas.microsoft.com/2006/09/sip/state\"><availability>3500</availability></state>"
+"      </cwaCategory>"
+"      <cwaCategory name=\"services\" instance=\"0\" version=\"0\" publishTime=\"2010-09-01T06:28:10Z\">"
+"        <services xmlns=\"http://schemas.microsoft.com/2006/09/sip/service\"><service uri=\"sip:kasturi_rao@satyam.com\"><capabilities><text render=\"true\" capture=\"true\" deviceAvailability=\"3500\" /><gifInk render=\"true\" capture=\"false\" deviceAvailability=\"3500\" /><isfInk render=\"true\" capture=\"false\" deviceAvailability=\"3500\" /></capabilities></service></services>"
+"      </cwaCategory>"
+"    </user>"
+"    <user uri=\"sip:Peter_Thomas@satyam.com\" sourceNetwork=\"sameEnterprise\">"
+"      <cwaCategory name=\"calendarData\" />"
+"      <cwaCategory name=\"contactCard\" instance=\"0\" version=\"0\" publishTime=\"2010-06-08T16:31:49Z\">"
+"        <contactCard xmlns=\"http://schemas.microsoft.com/2006/09/sip/contactcard\"><identity><name><displayName>"
+"Peter_Thomas</displayName></name><email>"
+"Peter_Thomas@mahindrasatyam.com</email></identity></contactCard>"
+"      </cwaCategory>"
+"      <cwaCategory name=\"contactCard\" instance=\"2\" version=\"0\" publishTime=\"2010-02-19T13:28:04Z\">"
+"        <contactCard xmlns=\"http://schemas.microsoft.com/2006/09/sip/contactcard\"><address type=\"work\"><city>BANGALORE</city><state>Karnataka</state><zipcode>560008</zipcode><countryCode>IN</countryCode></address></contactCard>"
+"      </cwaCategory>"
+"      <cwaCategory name=\"contactCard\" instance=\"3\" version=\"0\" publishTime=\"2010-08-23T09:56:52Z\">"
+"        <contactCard xmlns=\"http://schemas.microsoft.com/2006/09/sip/contactcard\"><company>SCSL</company><title>Band I</title><office>BSD</office></contactCard>"
+"      </cwaCategory>"
+"      <cwaCategory name=\"note\" />"
+"      <cwaCategory name=\"state\" instance=\"0\" version=\"0\" publishTime=\"2010-08-23T10:25:09Z\">"
+"        <state xsi:type=\"aggregateState\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://schemas.microsoft.com/2006/09/sip/state\"><availability>18000</availability></state>"
+"      </cwaCategory>"
+"      <cwaCategory name=\"services\" />"
+"    </user>"
+"</userPresence>";

        Document doc = cwa.util.NodeHelper.instance().build(sxml);
        userPresence instance = new userPresence(doc.getFirstChild());
    }

}