package com.perfectomobile.communityPOM;

import java.awt.peer.MenuPeer;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

import com.perfectomobile.utils.PerfectoUtils;

public class CommunityBaseView {
	
	protected RemoteWebDriver driver;
	private String url = "http://community.perfectomobile.com/";
	protected UpperMenuPageView menuPanel;
	
	 /**********************************************************************
	 * 		Constructor
	 **********************************************************************/
	public CommunityBaseView(RemoteWebDriver driver){
		this.driver = driver;
	}
	
		
	/**********************************************************************
	 * 		init: initializes the driver.
	 * @throws IOException 
	 **********************************************************************/
	public CommunityBaseView init() throws IOException{
				
		//set timeouts
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//open url
		driver.get(url);
		
		//validate page opened	
		try{
			//PerfectoUtils.fluentWait(communityLogo, driver, 20);
			menuPanel = new UpperMenuPageView(this.driver);
			if (menuPanel.isLoggedIn()){
				menuPanel = menuPanel.logOut();
			}
		}
		catch(Exception e){
			
			String errorFile = PerfectoUtils.takeScreenshot(driver);
			Reporter.log("Error screenshot saved in file: " + errorFile);
			throw new IllegalStateException();
				
		}	
		return this;
	}
	
	
	/**********************************************************************
	 * 	searchItem	
	 * 		This method searches a given product in the walmart site.
	 * 		
	 * 		@param text	the text to search for
	 * 		@return	new instance of the view to the searched results page
	 * @throws IOException 
	 **********************************************************************/
	public SearchResultsPageView searchItem(String text) throws IOException{
		return menuPanel.searchItem(text);
	}
	
	
	/**********************************************************************
	 * 	clickHomePage	
	 * 		This method clicks on the Walmart image link to navigate site to its 
	 * 		home page
	 * 		
	 * 		@return	new instance of the search stores page
	 **********************************************************************//*
	public CommunityBaseView clickHomePage(){
		try {
			//click on locate stores button
			this.driver.findElement(communityLogo).click();
			return new CommunityBaseView(this.driver);
			
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
		
	}
	*/
	
	/*****
	 * login
	 * 	gets a username and password and logins to the community
	 * @param username username
	 * @param password password
	 * @return the same page only this time the upper menu is logged in
	 * @throws IOException
	 */
	public CommunityBaseView login(String username,String password) throws IOException{
		
		this.menuPanel= menuPanel.login(username, password);
		return this;
		
	}
	
	/******
	 * logout
	 * performs a logout of the system
	 * @return same page only this time the upper menu is logged out
	 * @throws IOException
	 */
	public CommunityBaseView logOut() throws IOException{
		try {
			this.menuPanel = menuPanel.logOut();
			return this;
		} catch (Exception e) {
			String errorFile = PerfectoUtils.takeScreenshot(driver);
			Reporter.log("Error screenshot saved in file: " + errorFile);
			throw new IllegalStateException();
		}
		
		
	}
	
	/*****
	 * getWelcomeMessage
	 * gets the welcome message after login
	 * @return the welcome message after login
	 */
	public String getWelcomeMessage(){
		
		return menuPanel.getWelcomeMessage();
	}
	
}
