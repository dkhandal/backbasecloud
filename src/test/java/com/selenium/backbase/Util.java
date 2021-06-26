/**
 * 
 */
package com.selenium.backbase;

import java.io.File;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

/*
 * Helper class to provide all utility functions
 */
public class Util {

	public static WebDriver driver;

	/*
	 * To open browser for testing with give URL from Constant
	 */
	public static void invokeBrowser(String URL) {
		try {
			System.out.println("Opening Browser");
			try {
	          System.setProperty("webdriver.chrome.driver", "chdriver/chromedriver.exe");
	          driver = new ChromeDriver();
	          driver.manage().deleteAllCookies();
	          driver.manage().window().maximize();
	          driver.manage().timeouts().implicitlyWait(Constants.timeOutInSeconds, TimeUnit.SECONDS);
	          driver.manage().timeouts().pageLoadTimeout(Constants.timeOutInSeconds, TimeUnit.SECONDS);
	      } catch (Exception e) {
	          System.out.println("Exception occured in invokeBrowser");
	          e.printStackTrace();
	      }

			driver.get(URL); // to open URL via contant file

		} catch (Exception e) {
			String STRING1 = "Exception occured in invokeBrowser";
			System.out.println(STRING1);
			e.printStackTrace();
//	      driver.quit();
		}
	}
	
	public static WebDriver getDriver() {
		return driver;
	}
	
	public static String getPageTitle() {
		System.out.println(driver.getTitle());
		return driver.getTitle();
	}
	
	public static String getPageSourceCheck(String str) {
		if(driver.getPageSource().contains(str)){
				System.out.println("Text is present");
				return str;
			}else{
				System.out.println("Text is absent");
				return str;
			}
	}

	public static WebElement isElementPresent(By by, String linkContext) {
//			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		try {

			System.out.println(linkContext + " for " + "Element Present in isElementPresent");
			return driver.findElement(by);
		} catch (NoSuchElementException e) {
			System.out.println(
					linkContext + " for " + "Element absent - Came into NoSuchElementException isElementPresent");
			return null;
		} catch (Exception e) {
			System.out.println(linkContext + " for " + "Element absent - Came into Exception isElementPresent");
			return null;
		}
	}

	public static void webElementLinkToClick(String linkText) {
		try {
			driver.findElement(By.linkText(linkText)).click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", driver.findElement(By.linkText(linkText)));
		}
	}
	
	public static String webElementLinkToGet(String xpath) {
		try {
			return driver.findElement(By.xpath(xpath)).getText();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", driver.findElement(By.linkText(xpath)));
		}
		return "";
	}
	
	public static void webElementLinkToClickWithNewWindow(String linkText) {
		try {
			String winHandleBefore = driver.getWindowHandle();
			driver.findElement(By.partialLinkText(linkText)).click();
			for(String winHandle : driver.getWindowHandles()){
				try {
					 driver.switchTo().window(winHandle); 
				} catch (NoSuchWindowException e) {
					System.out.println("NoSuchWindowException occured while opening Privacy page");
				}
		    } 
			
			driver.close();
			try {
				driver.switchTo().window(winHandleBefore);
			} catch (NoSuchWindowException e) {
				System.out.println("NoSuchWindowException occured while going back home page");
			}

		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", driver.findElement(By.linkText(linkText)));
		}
	}

	public static void webElementClickById(String strId) {
		WebElement webElement = driver.findElement(By.id(strId));
		try {
			System.err.println(strId);
			webElement.click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();",webElement);
		}
	}

	public static void webElementClearById(String strId) {
		try { 
			driver.findElement(By.id(strId)).clear();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", driver.findElement(By.id(strId)));
		}
	}

	public static void webElementClickByXpath(String xpathExpression) {
		try {
			driver.findElement(By.xpath(xpathExpression)).click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(xpathExpression)));
		}
	}

	public static void webElementByNameClick(String strName) {
		try {
			driver.findElement(By.name(strName)).click();
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", driver.findElement(By.name(strName)));
		}
	}

	public static void webElementByNameClear(String strName) {
//			    try {
		driver.findElement(By.name(strName)).clear();
//			     } catch (Exception e) {
//			        JavascriptExecutor executor = (JavascriptExecutor) driver;
//			        executor.executeScript("arguments[0].clear();", driver.findElement(By.name(strName)));
//			     }
	}

	public static void webElementSendKeyById(String sendKeyId, String str) {
		try {
			WebElement webElement = driver.findElement(By.id(sendKeyId));
			webElement.clear();
			webElement.sendKeys(str);
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", driver.findElement(By.id(sendKeyId)));
		}
	}

	public static void webElementSendKeyByXpath(String sendKeyByXpath, String str) {
		try {
			WebElement webElement = driver.findElement(By.xpath(sendKeyByXpath));
			webElement.clear();
			webElement.sendKeys(str);
		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(sendKeyByXpath)));
		}
	}

	public static void driverAndBroswerClose() {
		driver.quit();
	}

	
	// Time Zone dependent to getLocalDateTime
	public static String getLocalDateTime() {
		Date date = new Date();
		System.out.println("Default Server Date Time: " + date.toString());
		String format = "MMM dd yyyy hh:mm:ss a";
		String timeZone = "IST"; // PST // GMT // IST // UTC
		// create SimpleDateFormat object with input format
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		// set timezone to SimpleDateFormat
		sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
		String ldtString = sdf.format(date);
		System.out.println("Local Date Time in " + timeZone + " : " + ldtString);
		return ldtString;
	}

	/*
	 * To get current Date time
	 */
	public static java.util.Date getDateTime() {
		java.util.Date scriptDate = new java.util.Date();
		return scriptDate;
	}
	
	/*
	 * Time Zone dependent to getLocalDate
	 */
	  public static String getLocalDate(String format){
		  	Date date = new Date();
//		  	System.out.println("Default Server Date Time: "+ date.toString());
//		  	String format = "yyyy-MM-dd";
		  	String timeZone = "IST"; // PST // GMT // IST // UTC
		  	// create SimpleDateFormat object with input format
		 	SimpleDateFormat sdf = new SimpleDateFormat(format);
		 	// set timezone to SimpleDateFormat
		 	sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
		 	String ldtString = sdf.format(date);
//		 	System.out.println("Local Date Time in " + timeZone +  " : " + ldtString);
		 	return ldtString;
	  }

	/*
	 * Time Zone dependent to getDOB
	 */
	public static String getDOB(String strDate) {
		Date date = new Date();
		String ldtString = "";
//		  	System.out.println("Default Server Date Time: "+ date.toString());
		String format = "dd-MMM-yyyy";
		String timeZone = "IST"; // PST // GMT // IST // UTC
		// create SimpleDateFormat object with input format
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		// set timezone to SimpleDateFormat
		sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
		try {
			date = sdf.parse(strDate);
			String formatNew = "yyyy-MM-dd";
			SimpleDateFormat sdfnew = new SimpleDateFormat(formatNew);
			ldtString = sdfnew.format(date);
//			 	System.out.println("Local Date Time in " + timeZone +  " : " + ldtString);				
		} catch (ParseException e) {
			e.printStackTrace();
			ldtString = "";
		}
		return ldtString;
	}

	/*
	 * To show home much time takes to process this script
	 */
	public static void showTimeDifference(java.util.Date startDateTime, java.util.Date endDateTime) {

		System.out.println("\nScript Start at: " + startDateTime);
		System.out.println("Script End at: " + endDateTime);

		DecimalFormat crunchifyFormatter = new DecimalFormat("###,###");

		// getTime() returns the number of milliseconds since January 1, 1970, 00:00:00
		// GMT represented by this Date object
		long diff = endDateTime.getTime() - startDateTime.getTime();

		int diffDays = (int) (diff / (24 * 60 * 60 * 1000));
		System.out.println("difference between days: " + diffDays);

		int diffhours = (int) (diff / (60 * 60 * 1000));
		System.out.println("difference between hours: " + crunchifyFormatter.format(diffhours));

		int diffmin = (int) (diff / (60 * 1000));
		System.out.println("difference between minutues: " + crunchifyFormatter.format(diffmin));

		int diffsec = (int) (diff / (1000));
		System.out.println("difference between seconds: " + crunchifyFormatter.format(diffsec));

		System.out.println("difference between milliseconds: " + crunchifyFormatter.format(diff));
	}

	// To detect which OS is running
	public static boolean isWindows() {
		return Constants.OS.contains("win");
	}

	public static boolean isMac() {
		return Constants.OS.contains("mac");
	}

	public static boolean isUnix() {
		return (Constants.OS.contains("nix") || Constants.OS.contains("nux") || Constants.OS.contains("aix"));
	}

	public static boolean isSolaris() {
		return Constants.OS.contains("sunos");
	}

	public static void scrollDownPage() {
		System.out.println("scrollDownPage called");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// This will scroll the web page till end.
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public static String getProjectDirectory() {
		String projectRootPath = System.getProperty("user.dir");
		return projectRootPath;
	}

	/*
	 * To get home much time takes to process this script in string
	 */
	public static String showTimeDifferenceInString(java.util.Date startDateTime, java.util.Date endDateTime,
			String message) {

		String receiveMsg = "\n" + message;

		String timeDiffStr = "";

		String strStartDate = "\nStart at: " + startDateTime;
		String strEndDate = "\nEnd at: " + endDateTime;

		DecimalFormat crunchifyFormatter = new DecimalFormat("###,###");

		// getTime() returns the number of milliseconds since January 1, 1970, 00:00:00
		// GMT represented by this Date object
		long diff = endDateTime.getTime() - startDateTime.getTime();

		int diffDays = (int) (diff / (24 * 60 * 60 * 1000));
		String strDaysDiff = "\ndifference between days: " + diffDays;

		int diffhours = (int) (diff / (60 * 60 * 1000));
		String strHoursDiff = "\ndifference between hours: " + crunchifyFormatter.format(diffhours);

		int diffmin = (int) (diff / (60 * 1000));
		String strMinuteDiff = "\ndifference between minutues: " + crunchifyFormatter.format(diffmin);

		int diffsec = (int) (diff / (1000));
		String strSecondsDiff = "\ndifference between seconds: " + crunchifyFormatter.format(diffsec);

		String strMiliSecondsDiff = "\ndifference between milliseconds: " + crunchifyFormatter.format(diff);

		timeDiffStr = receiveMsg + strStartDate + strEndDate + strDaysDiff + strHoursDiff + strMinuteDiff
				+ strSecondsDiff + strMiliSecondsDiff;

		return timeDiffStr;
	}

	public static void println(String strMsg) {
		if (!Constants.isProduction) {
//				System.out.println(strMsg);
		}

	}

	/**
	 * To check that file exist on given path
	 * 
	 * @param filePath
	 * @return boolean
	 */
	public static boolean isFileExist(String filePath) {
		boolean isFilePresent = false;
		// Get the file
		File file = new File(filePath);

		// Check if the specified file
		// Exists or not
		if (file.exists()) {
			System.out.println("Exists File: " + file.getName());
			isFilePresent = true;
		} else {
			System.out.println("Does not Exists File: " + file.getName());
			isFilePresent = false;
		}
		return isFilePresent;
	}

	/**
	 * To delete file on given path
	 * 
	 * @param filePath
	 */
	public static void deleteFile(String filePath) {
		File file = new File(filePath);
		boolean isFileDeleted = false;
		try {
			isFileDeleted = file.delete();
		} catch (Exception e) {
			isFileDeleted = false;
			e.printStackTrace();
			System.out.println(e.getLocalizedMessage());
		}

		if (isFileDeleted) {
			System.out.println("File deleted successfully: " + file.getName());
		} else {
			System.out.println("Failed to delete the file. Might be deleted already.: " + file.getName());
		}
	}

	public static String randomEmail() {
		// create instance of Random class
		Random rand = new Random();
		// Generate random integers in range 0 to 999
		int rand_int = rand.nextInt(1000);
		String emailId = "test" + rand_int + "@test.com"; // test1@test.com
		return emailId;
	}

	public static boolean webElementLinkToClickWithNewWin(String string) {
		try {
			String winHandleBefore = driver.getWindowHandle();
			driver.findElement(By.partialLinkText(string)).click();
			for(String winHandle : driver.getWindowHandles()){
				try {
					 driver.switchTo().window(winHandle); 
				} catch (NoSuchWindowException e) {
					System.out.println("NoSuchWindowException occured while opening Privacy page");
				}
		    } 
			
            boolean isFound = false;
            if(Util.getPageSourceCheck("New Window") == "New Window") {
          	  isFound = true;
            }else {
            	isFound = false;
            }
			return isFound;
//			driver.close();
//			try {
//				driver.switchTo().window(winHandleBefore);
//			} catch (NoSuchWindowException e) {
//				System.out.println("NoSuchWindowException occured while going back home page");
//			}

		} catch (Exception e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", driver.findElement(By.linkText(string)));
		}
		return false;
	}

}



