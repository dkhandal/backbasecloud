/**
 * 
 */
package com.selenium.backbase;

public class Constants {
	
	public static long timeOutInSeconds = 90; //1.5 minute
	
	public static final String DATE_FORMATE1 = "yyyy-MM-dd";
	public static final String DATE_FORMATE2 = "yyyy-MM-dd HH:mm:ss";
	
	// General
	public static String OS = System.getProperty("os.name").toLowerCase();
	public static boolean isDisableFirefoxLog = false; // If this is true then will now show firefox logs otherwise will show.
	public static boolean isHeadlessRequired = false; // Please true if headless is required
	public static boolean isProduction = true;
	

	public static final String BASE_URL = "https://qa-task.backbasecloud.com";
	public static final String USER_NAME = "candidatex";
	public static final String PWD = "qa-is-cool";

	
	
}
