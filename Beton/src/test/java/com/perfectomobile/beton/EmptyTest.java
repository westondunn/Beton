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
import com.perfectomobile.utils.PerfectoUtils;
import com.perfectomobile.webCommunityPOM.WebCommunityBaseView;

public class EmptyTest extends BasicTest {

	@Test (dataProvider="logInData")
	public void testEmpty(String username, String password, String message) throws Exception{
		boolean testFail = false;
		//WebCommunityBaseView mobileView = null;
		if(this.driver == null){
			Assert.fail("Device not available: " + caps);
		}
	 	try{
	 		System.out.println("test");
	 		reportPass("test pass", "param1", "param2");
	 	}
	 	catch(Exception e){
	 		e.printStackTrace();
	 	}
		
        if(testFail){
        	Assert.fail();
        }

	}

	@DataProvider (name = "logInData", parallel = false)
	public Object[][] searchItemsData(){
		 Object[][] s = null;
		try {
		  ExcelDriver ed = new ExcelDriver(sysProp.get("inputDataSheet"), sysProp.get("signInSheet"), false);
		  s = ed.getData(3);
		} catch(IOException e) {
			System.out.println("Not able to search data from excel: " + sysProp.get("inputDataSheet"));
			System.err.println("IndexOutOfBoundsException: " + e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	
	@Factory(dataProvider="factoryData")
	public EmptyTest(DesiredCapabilities caps) {
		super(caps);
	}
}
