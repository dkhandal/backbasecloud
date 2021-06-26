package com.selenium.backbase;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DisplayPaginatedList {
	
	 
	@BeforeClass
	public void beforeDisplayPaginatedList() {
		
		Util.invokeBrowser(Constants.BASE_URL);
		System.out.println("DisplayPaginatedList started");
		Reporter.log("DisplayPaginatedList started");
	}
	
	  @Test(priority=0)
	  public void testDisplayPaginatedList() {
		  try {
			    try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			    /**
			     * Credientails provided here
			     */
			    String username = Constants.USER_NAME;
			    String password = Constants.PWD;
			    String URL = "http://" + username  + ":" + password + "@" + "qa-task.backbasecloud.com/";
			    Util.getDriver().get(URL);
			    
			    // category click here
			    Util.webElementClickByXpath("/html/body/app-root/app-home-page/div/div[2]/div/div[1]/div/ul/li[2]/a");
			    
			    // get first feedurl from page 
			    String feedUrl = Util.webElementLinkToGet("/html/body/app-root/app-home-page/div/div[2]/div/div[1]/app-article-list/app-article-preview[1]/div/app-article-meta/div/div/a");
			    
			    System.out.println(feedUrl);
			    
			    

			    // scroll the page and pagination the page click
			    JavascriptExecutor js = ((JavascriptExecutor) Util.getDriver());
			    js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
				
				WebElement pagination = Util.getDriver().findElement(By.cssSelector("body > app-root > app-home-page > div > div.container.page > div > div.col-md-9 > app-article-list > nav > ul"));
				List<WebElement> links = pagination.findElements(By.tagName("li"));
				for (int i = 1; i < links.size(); i++)
				{
				    
				    JavascriptExecutor js1 = ((JavascriptExecutor) Util.getDriver());
				    js1.executeScript("window.scrollTo(0, document.body.scrollHeight)");
				    
				    Thread.sleep(2000);
				    
				    System.out.println(links.get(i).getText());
				    JavascriptExecutor executor = (JavascriptExecutor) Util.getDriver();
				    executor.executeScript("arguments[0].click();", links.get(i));

				    Thread.sleep(3000);
				    
				    String feedUrlloop = Util.webElementLinkToGet("/html/body/app-root/app-home-page/div/div[2]/div/div[1]/app-article-list/app-article-preview[1]/div/app-article-meta/div/div/a");
				    System.out.println(feedUrlloop);
				    
				    boolean feedUrlExist = false;
				    if(feedUrl != null && !feedUrl.isEmpty()) {
				    	feedUrlExist = true;
				    }
				    
					
					Assert.assertEquals(true, feedUrlExist, "Feed Url is empty");
					Reporter.log("Feed Url " + feedUrlloop + " get Successfully");
				}
			   System.out.println("All pagination done successfully");
			    
		  }catch (Exception e){
			  e.printStackTrace();
			  Util.driverAndBroswerClose();
		  }

	  }
	  
@AfterClass(alwaysRun = true)
public void tearDown() {
	try {
		Thread.sleep(10000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	Util.driverAndBroswerClose();
	Reporter.log("Driver Closed After Testing");
}

	
}

