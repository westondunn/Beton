package com.perfectomobile.communityPOM;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

import com.perfectomobile.utils.PerfectoUtils;

public class PostPageView extends CommunityBaseView {
	
	private By postPage = By.xpath("//div[@class='post-head-main']");
	private By postTitle = By.xpath("//h2[@class='title']");
	private By postDescription = By.xpath("//h4[starts-with(@class,'description')]");
	private By postUpdateDate = By.xpath("//div[starts-with(@class,'updated-at')]");
	
		
	/**********************************************************************
	 * 		Constructor
	 * @throws IOException 
	 **********************************************************************/
	public PostPageView(RemoteWebDriver driver) throws IOException{
        super(driver);
        
        //validate page loaded successfully before proceeding
        try{
        	PerfectoUtils.fluentWait(postPage, driver, 15);
        }
        catch(Exception e){
        	String errorFile = PerfectoUtils.takeScreenshot(driver);
			Reporter.log("Error screenshot saved in file: " + errorFile);
        	throw new IllegalStateException();
        }
    }
	
	//gets the title of the post
	public String getPostTitle(){
		try {
			return this.driver.findElement(postTitle).getText();
		} catch (Exception e) {
			return "ERROR";
		}
	}
	
	//gets the description of the post
	public String getPostDescription(){
		try {
			return this.driver.findElement(postDescription).getText();
		} catch (Exception e) {
			return "ERROR";
		}
	}
	
	//gets the date the post was published
	public String getPostPublishedDate(){
		String text;
		int start,end;
		try {
			text =  this.driver.findElement(postUpdateDate).getText();
			start = text.indexOf("Published")+9;
			return text.substring(start);
			
		} catch (Exception e) {
			return "ERROR";
		}
	}
	

}