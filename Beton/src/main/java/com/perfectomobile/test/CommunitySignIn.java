package com.perfectomobile.test;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.perfectomobile.webCommunityPOM.WebCommunityBaseView;
import com.perfectomobile.dataDrivers.excelDriver.ExcelDriver;
import com.perfectomobile.utils.PerfectoUtils;

public class CommunitySignIn extends BasicTest{

	@Factory(dataProvider="factoryData")
	public CommunitySignIn(DesiredCapabilities caps) {
		super(caps);
		// TODO Auto-generated constructor stub
	}
	
	@Test (dataProvider="logInData")
	public void testSignIn(String username, String password, String message) throws Exception{
		boolean testFail = false;
		WebCommunityBaseView mobileView = null;
		if(this.driver == null){
			Assert.fail("Device not available: " + caps);
		}
	 	  
	 	try{
	 		mobileView = new WebCommunityBaseView(driver);
	        mobileView = mobileView.init();

			mobileView = mobileView.login(username, password);
			String welcomeMessage = mobileView.getWelcomeMessage();
			
			if (welcomeMessage.equals(message)){
	        	resultSheet.setResultByColumnName(true, this.testName, username, password, message);
	        }
			else{
				resultSheet.setResultByColumnName(false, this.testName, username, password, message);
				reportFail(message, welcomeMessage);
				testFail = true;
			}
	 	}
	 	catch(Exception e){
	 		resultSheet.setResultByColumnName(false, this.testName, username, password, message);
			testFail = true;
        	String errorFile = PerfectoUtils.takeScreenshot(driver);
    		Reporter.log(e.toString());
    		Reporter.log("Error screenshot saved in file: " + errorFile);
    		Reporter.log("<br> <img src=" + errorFile + ".png style=\"max-width:50%;max-height:50%\" /> <br>");
	 	}
		
        mobileView = mobileView.logOut();
        if(testFail){
        	Assert.fail();
        }

	}
	@DataProvider (name = "logInData", parallel = false)
	public Object[][] searchItemsData() throws Exception{
		  ExcelDriver ed = new ExcelDriver("C:\\Users\\AvnerG\\git\\Beton\\data\\testData.xlsx", "signIn", false);
		  Object[][] s = ed.getData(3);

		  return s;
	}
}
