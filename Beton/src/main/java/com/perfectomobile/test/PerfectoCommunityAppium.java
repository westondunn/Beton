package com.perfectomobile.test;

import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.perfectomobile.androidCommunityPOM.ANDROID_CommunityBaseView;
import com.perfectomobile.androidCommunityPOM.ANDROID_PostPageView;
import com.perfectomobile.utils.VisualDriverControl;
import com.perfectomobile.utils.PerfectoUtils;

public class PerfectoCommunityAppium {

	public static void main(String[] args) throws MalformedURLException {
		String host = args[0];
		String url = "https://" + host + "/nexperience/perfectomobile/wd/hub";
		
		DesiredCapabilities capabilities = new DesiredCapabilities("", "", Platform.ANY);
		
		capabilities.setCapability("user", args[1]);
		capabilities.setCapability("password", args[2]);
		
		url = "https://" + host + "/nexperience/perfectomobile/wd/hub";
		
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("deviceName", args[3]);
		capabilities.setCapability("automationName","Appium");
		capabilities.setCapability("appPackage", "com.bloomfire.android.perfecto");
		
//		try { //check if there is a current connection to the device and use the same executionId
//		    EclipseConnector connector = new EclipseConnector(); 
//		    String eclipseExecutionId = connector.getExecutionId();                  
//		    capabilities.setCapability("eclipseExecutionId", eclipseExecutionId); 
//		} catch (IOException ex) { 
//		    ex.printStackTrace(); 
//		    return; 
//		}

		RemoteWebDriver driver = new RemoteWebDriver(new URL(url), capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		try {
			
			System.out.println("Current Context: " + PerfectoUtils.getCurrentContextHandle(driver));
//			System.out.println("Current Activity: " + driver.currentActivity());			

			ANDROID_CommunityBaseView communityBase = new ANDROID_CommunityBaseView(driver);
			communityBase.login("brianc@perfectomobile.com", "Perfecto99");
//			communityBase.clickSearchButton().inputSearchText("selenium").selectSearchResult("Jenkins");
//			VisualDriverControl.findBoundedVisualImageElement(driver, "PRIVATE:images/communityFileButton.png",75,90);
//			VisualDriverControl.findVisualImageElement(driver, "C:\\temp\\communityFileButton.png");
			ANDROID_PostPageView currentPost = communityBase.clickSearchButton().inputSearchText("selenium").selectSearchResult("Eclipse");
			currentPost.getAuthor();
			currentPost.getDescription();
//			currentPost.followPost();
			currentPost.backToSearchResults().backToSetSearch().closeSearch().openMenuDrawer().gotoSettings().logout();
			
			
//			System.out.println(driver.getAppStrings());
			sleep(10000);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				System.out.println("Run ended");
				driver.quit();
			}
	}
	private static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
		}

	}
	

}
