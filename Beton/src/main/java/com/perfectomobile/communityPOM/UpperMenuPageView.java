package com.perfectomobile.communityPOM;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

import com.perfectomobile.utils.PerfectoUtils;
import com.perfectomobile.test.BasicTest;

public class UpperMenuPageView{
	
	private boolean loggedIn;
	
	private By communityLogo	= By.xpath("//img[@class='brand-image']");
	
	
	
	private By welcomeMessage = By.xpath("//span[@class='greeting']");
	private By popUp = By.xpath("//*[@class='no-thanks']");
	
	//login fields	
	private String loginButton = "//*[text()='Log In']";
	private String logoutButton = "//*[@href='/logout']";
	
	//search fields
	private String searchButton1 = "(//header[1]/div[2]/button[1]/span[1])";
	private String searchButton2 = "(//header[1]/div[1]/button[1]/div[1]/span[1])";
	private By searchAreaInput	= By.xpath("//input[contains(@class,'js-search-input')]");

	private RemoteWebDriver driver;
	
	
	/**********************************************************************
	 * 		Constructor
	 * @throws IOException 
	 **********************************************************************/
	public UpperMenuPageView(RemoteWebDriver driver) throws IOException{
		this.driver = driver;
        //this.loggedIn = false;
           
        
        //validate page loaded successfully before proceeding
        try{
        	PerfectoUtils.fluentWait(communityLogo, this.driver, 30);
        	handleAppPopup();
        	try {
        		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
				this.driver.findElement(By.xpath(logoutButton));
				this.loggedIn = true;
			} catch (Exception e) {
				
				this.loggedIn = false;
			}
        	
        	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }
        catch(Exception e){
        	/*
        		String errorFile = PerfectoUtils.takeScreenshot(driver);
        		Reporter.log("Error screenshot saved in file: " + errorFile);
        		
        		*/
			   	throw new IllegalStateException();
        }
        
       
    }

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	public UpperMenuPageView logOut() throws IOException{
		try {
			//this.driver.findElement(By.xpath(logoutButton)).click();
			jsExecute("clickLogout");
			return new UpperMenuPageView(driver);
			
		} catch (Exception e) {
			/*
			String errorFile = PerfectoUtils.takeScreenshot(driver);
			Reporter.log("Error screenshot saved in file: " + errorFile);
			*/
			throw new IllegalStateException();
		}
		
		
	}
	private void handleAppPopup(){
        try {
              //not a must
              /*Capabilities capabilities = this.driver.getCapabilities();
              String os = (String) capabilities.getCapability("platformName");
              if (!os.equals("iOS"))
                    return;*/
        	//HashMap<String,String> deviceProperties = PerfectoUtils.getDevicePropertiesList(driver);
        	//String os = deviceProperties.get("os");
        	String os = PerfectoUtils.getDeviceProperty(driver, "os");
        	//System.out.println(os);
              if (!PerfectoUtils.isDevice(this.driver) || !os.equals("iOS"))
                    return;
              
              //must
              driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
              this.driver.findElement(popUp).click();
              Thread.sleep(2000);
              //PerfectoUtils.fluentWait(popUp, driver, 0);
        } catch (Exception e) {
              //System.out.println("no popup");
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        
  }

	
//	private void handleAppPopup(){
//		try {
//			//not a must
//			//Capabilities capabilities = this.driver.getCapabilities();
//			String os = driver.getCapabilities().asMap().get("os").toString();
//
//			//String os = (String) capabilities.getCapability("platformName");
//			System.out.println("os="+os);
//			if (!os.equals("iOS")){
//				System.out.println("No pop up to handle");
//				return;
//			}
//			System.out.println("handle popup iOS");
//			Thread.sleep(2000);
//			//must
//			//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//			this.driver.findElement(popUp).click();
//			Thread.sleep(2000);
//			//PerfectoUtils.fluentWait(popUp, driver, 0);
//		} catch (Exception e) {
//			//System.out.println("no popup");
//		}
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		
//	}
	
	public UpperMenuPageView login(String username,String password) throws IOException{
		try {
			//this.driver.findElement(loginButton).click();
			jsExecute("clickLogin");
			Object loginView = new LoginPageView(this.driver).login(username, password);
			
			
			return new UpperMenuPageView(driver);
			
		} catch (Exception e) {
			/*
			String errorFile = PerfectoUtils.takeScreenshot(driver);
			Reporter.log("Error screenshot saved in file: " + errorFile);
			*/
			return null;
		}
		
	
	}
	public String getWelcomeMessage(){
		
		try {
			return driver.findElement(welcomeMessage).getText();
		} catch (Exception e) {
			return "";
		}
	}
	
public SearchResultsPageView searchItem(String text) throws IOException{
		
		try {
			
			jsExecute("clickSearchButton");
			
			WebElement element = this.driver.findElement(searchAreaInput);
			//element.clear();
			element.sendKeys(text);
			this.driver.getKeyboard().pressKey(Keys.ENTER);
						
			return new SearchResultsPageView(this.driver);
			
		} catch (Exception e) {
			/*
			String errorFile = PerfectoUtils.takeScreenshot(driver);
			Reporter.log("Error screenshot saved in file: " + errorFile);
			*/
			return null;
		}
		
	}
	public void jsExecute(String action){
		
		
		//define the findElementByXpath(path) function:
		String jsScript="function findElementsByXpath(path){"
				+ "elements = document.evaluate(path, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;"
				+ "return elements;}";
		
		
		switch (action) {
		case "clickLogin":
				/*//path="primary-header-button primary-button";
				jsScript = jsScript+ " elements = document.getElementsByClassName('"+loginButtonText+"');"
						+ "elements[0].click();";*/
			jsScript=jsScript+ " elements = findElementsByXpath(\"" + loginButton + "\");"
					+ "elements.click();";
			break;
		
		case "clickLogout":
				jsScript=jsScript+ " elements = findElementsByXpath(\"" + logoutButton + "\");"
					+ "elements.click();";
		break;
		
		case "clickSearchButton":
			
			jsScript=jsScript+" elements = findElementsByXpath(\"" + searchButton1 + "\");"
					+ "elements.click();";
			jsScript=jsScript +" elements = findElementsByXpath(\"" + searchButton2 + "\");"
					+ "elements.click();";
		break;

		default:
			break;
		}

		
	     
		if (driver instanceof JavascriptExecutor) {
	        ((JavascriptExecutor)driver).executeScript(jsScript);
		}
	}
	
}