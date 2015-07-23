package com.perfectomobile.communityPOM;

import java.io.IOException;




import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

import com.perfectomobile.utils.PerfectoUtils;

public class LoginPageView extends CommunityBaseView {
	
	String username;
	String password;
	
	//page elements:
	private By headerPanel = By.xpath("//*[@class='page__wrapper']");
	private By usernameTextField = By.xpath("//input[@id='user_email']");
	private By passwordTextField = By.xpath("//input[@id='user_password']");
	private By submitButton = By.xpath("//input[@type='submit']");
	
	private By loginError = By.xpath("//div[contains(@class,'alert-error')]");
	
	/**********************************************************************
	 * 		Constructor
	 * @throws IOException 
	 **********************************************************************/
	public LoginPageView(RemoteWebDriver driver) throws IOException{
        super(driver);
        this.username="";
        this.password="";
        
        //validate page loaded successfully before proceeding
        try{
        	PerfectoUtils.fluentWait(headerPanel, driver, 30);
        }
        catch(Exception e){
        	String errorFile = PerfectoUtils.takeScreenshot(driver);
			Reporter.log("Error screenshot saved in file: " + errorFile);
        	throw new IllegalStateException();
        }
        
       
    }
	

	/*****
	 * login
	 * 	gets a username and password and logins to the community
	 * @param username username
	 * @param password password
	 * @return the same page only this time the upper menu is logged in
	 * @throws IOException
	 */
	public LoginPageView login(String username, String password) throws IOException{
		try {
			WebElement element = this.driver.findElement(this.usernameTextField);
			//element.clear();
			element.sendKeys(username);
			element = this.driver.findElement(passwordTextField);
			//element.clear();
			element.sendKeys(password);
			
			this.driver.findElement(this.submitButton).click();
			this.username = username;
			this.password = password;
			
			return this;
			//check for errors:
								
		} catch (Exception e) {
			return null;
		}
		
	}
	
	
}