/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cwa;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import cwa.exception.CwaException;

/**
 * 
 * @author jb
 */
public class Main {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) throws Exception {

		final Client myc = new Client();

		// read user data from file
		Properties user = new Properties();
		try {
			user.load(new FileInputStream("user.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("user: " + user.getProperty("uid"));
		// myc.connect("satyam\\ja34916", "Welcome11235", "https://imhyd.satyam.com");

		myc.xconnect(user.getProperty("uid"), user.getProperty("pwd"),
				user.getProperty("url"));
		myc.logon(user.getProperty("uid"), user.getProperty("pwd"));
		myc.initiateSession();

		myc.searchUser("jayabalan");

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				try {
					myc.getCwaEvents();
				} catch (CwaException ex) {
					ex.printStackTrace(System.err);
				}
			}
		}, 1000, 5000);

		// myc.terminateSession();

	}
}
