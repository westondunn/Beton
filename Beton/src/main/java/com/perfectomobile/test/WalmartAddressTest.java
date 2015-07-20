package com.perfectomobile.test;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.perfectomobile.dataDrivers.excelDriver.ExcelDriver;
import com.perfectomobile.utils.PerfectoUtils;
//import com.perfectomobile.selenium.util.EclipseConnector;
import com.perfectomobile.walmartPOM.SearchStoresPageView;
import com.perfectomobile.walmartPOM.WalmartBaseView;


public class WalmartAddressTest extends BasicTest{

  @Factory(dataProvider="factoryData")
  public WalmartAddressTest(DesiredCapabilities caps) {
	  super(caps);
	// TODO Auto-generated constructor stub
}
  @Test (dataProvider="searchStoresDP")
  public void searchStores(String searchedAddress, String strIdx, String storeAddress) throws Exception{	 
 	 boolean testFail = false;	
 	ClassLoader classLoader = PerfectoUtils.class.getClassLoader();
	  File inputWorkbook = new File(classLoader.getResource("testResults.xlsx").getFile());
	  String absolutePath = inputWorkbook.getAbsolutePath();
	  
	// Open workbook
	  ExcelDriver ed = new ExcelDriver();
	  ed.setWorkbook(absolutePath);
 	  ed.setSheet(this.deviceDesc, true);
 	  ed.setTestCycle(this.testCycle, true);
 	  Reporter.log(String.valueOf(Thread.currentThread().getId()));
 	  
 	 int index = Integer.valueOf(strIdx);
 	 try{
 		WalmartBaseView view = new WalmartBaseView(driver).init();
 	 	 SearchStoresPageView storeView = view.clickLocateStores().searchAddress(searchedAddress);
 	 	 String actualAddress = storeView.getAddressByStoreIndex(index);
 	 	 
 	 	 if(!actualAddress.equals(storeAddress)){
 	      	testFail = true;
 	      	Reporter.log("Value is: " + actualAddress + ", Should be: " + storeAddress);
 	      	String errorFile = PerfectoUtils.takeScreenshot(driver);
 	  		Reporter.log("Error screenshot saved in file: " + errorFile);
 	      }
 	 }
 	 catch(Exception e){
       	ed.setResultByTestCycle(false, this.testName, searchedAddress, strIdx, storeAddress);
       	Assert.fail("See reporter log for details");
 	 }
 	 //this.driver.get("http://google.com");
 	 
 	 if(testFail){
      	ed.setResultByTestCycle(false, this.testName, searchedAddress, strIdx, storeAddress);
      	Assert.fail("See reporter log for details");
      }
      else{
      	ed.setResultByTestCycle(true, this.testName, searchedAddress, strIdx, storeAddress);
      }

  }
  
  @DataProvider (name="searchStoresDP")
  public Object[][] searchStoresDP() throws Exception{
	  ClassLoader classLoader = PerfectoUtils.class.getClassLoader();
	  File inputWorkbook = new File(classLoader.getResource("testData.xlsx").getFile());
	  String absolutePath = inputWorkbook.getAbsolutePath();
	  // Open workbook
	  ExcelDriver ed = new ExcelDriver();
	  ed.setWorkbook(absolutePath);
 	  
 	  // Open sheet
 	  ed.setSheet("addresses", false);
 	  
 	  // Read the sheet into 2 dim (String)Object array.
 	  // "3" is the number of columns to read.
 	  Object[][] s = ed.getData(3);

 	  return s;
  }
  
	@BeforeMethod
	public void beforeMethod(Method method){
	this.testName = method.getName();
	}
}
