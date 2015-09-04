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

import com.perfectomobile.androidCommunityPOM_Appium.ANDROID_CommunityBaseView;
import com.perfectomobile.androidCommunityPOM_Appium.ANDROID_PostPageView;
import com.perfectomobile.utils.VisualDriverControl;
import com.perfectomobile.utils.PerfectoUtils;

public class PerfectoCommunityAppium {

	public static void main(String[] args) throws MalformedURLException {
		

		DesiredCapabilities capabilities = new DesiredCapabilities("", "", Platform.ANY);
		
		//Pointing to Perfeco Mobile Cloud
		String host = args[0];
		String url = "https://" + host + "/nexperience/perfectomobile/wd/hub";
		String device = args[3];
		capabilities.setCapability("user", args[1]);
		capabilities.setCapability("password", args[2]);		
		
		//Pointing to Local Appium
//		String url = "http://127.0.0.1:4723/wd/hub";
//		String device = "htc-htc6525lvw-FA465SF03657";	//local device via USB
		
		
		
				
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");
		capabilities.setCapability("deviceName", device);
		capabilities.setCapability("platformVersion", "5.01");
		capabilities.setCapability("automationName","Appium");
		capabilities.setCapability("platformName", "Android");
//		capabilities.setCapability("appPackage", "com.bloomfire.android.perfecto");
//		capabilities.setCapability("appActivity", "com.bloomfire.android.MainActivity");

		
		
//		try { //check if there is a current connection to the device and use the same executionId
//		    EclipseConnector connector = new EclipseConnector(); 
//		    String eclipseExecutionId = connector.getExecutionId();                  
//		    capabilities.setCapability("eclipseExecutionId", eclipseExecutionId); 
//		} catch (IOException ex) { 
//		    ex.printStackTrace(); 
//		    return; 
//		}

		AndroidDriver driver = new AndroidDriver(new URL(url), capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		String caps = driver.getCapabilities().toString();
		
		try {
			
			System.out.println("Current Context: " + PerfectoUtils.getCurrentContextHandle(driver));
//			System.out.println("Current Activity: " + driver.currentActivity());			

			driver.get("shop.lego.com");
					
					//http://mobileqa.lego.com/);
				
			
			
			sleep(600000);
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
