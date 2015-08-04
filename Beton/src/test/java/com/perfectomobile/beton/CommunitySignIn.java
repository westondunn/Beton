package com.perfectomobile.beton;

import java.io.IOException;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.perfectomobile.dataDrivers.excelDriver.ExcelDriver;
import com.perfectomobile.test.BasicTest;
import com.perfectomobile.test.Init;
import com.perfectomobile.utils.PerfectoUtils;
import com.perfectomobile.webCommunityPOM.WebCommunityBaseView;

public class CommunitySignIn extends BasicTest {


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
				String errorFile = reportFail(message, welcomeMessage);
				resultSheet.addScreenshotByRowNameAsLink(errorFile, this.testName, username, password, message);
				testFail = true;
			}
	 	}
	 	catch(Exception e){
	 		resultSheet.setResultByColumnName(false, this.testName, username, password, message);
			testFail = true;
        	String errorFile = PerfectoUtils.takeScreenshot(driver);
        	resultSheet.addScreenshotByRowNameAsLink(errorFile, this.testName, username, password, message);
    		Reporter.log(e.toString());
    		Reporter.log("Error screenshot saved in file: " + errorFile);
    		Reporter.log("<br> <img src=" + errorFile + ".png style=\"max-width:50%;max-height:50%\" /> <br>");
	 	}
		
        if(testFail){
        	Assert.fail();
        }

	}

	@DataProvider (name = "logInData", parallel = false)
	public Object[][] searchItemsData() throws Exception{
		 Object[][] s = null;
		try {
		  ExcelDriver ed = new ExcelDriver(sysProp.get("inputDataSheet"), "signIn", false);
		  s = ed.getData(3);
		} catch(IOException e) {
			System.out.println("Not able to search data from excel: " + sysProp.get("inputDataSheet"));
			System.err.println("IndexOutOfBoundsException: " + e.getMessage());
		}
		return s;
	}
	
	@Factory(dataProvider="factoryData")
	public CommunitySignIn(DesiredCapabilities caps) {
		super(caps);
	}
}
