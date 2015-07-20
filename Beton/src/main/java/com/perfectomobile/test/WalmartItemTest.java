package com.perfectomobile.test;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import org.apache.commons.lang3.StringEscapeUtils;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.perfectomobile.dataDrivers.excelDriver.ExcelDriver;
import com.perfectomobile.utils.PerfectoUtils;
//import com.perfectomobile.selenium.util.EclipseConnector;
import com.perfectomobile.walmartPOM.SearchItemsPageView;
import com.perfectomobile.walmartPOM.WalmartBaseView;

public class WalmartItemTest extends BasicTest {
	
	@Factory(dataProvider="factoryData")
	public WalmartItemTest(DesiredCapabilities caps){
		super(caps);
	}
	@Test (dataProvider = "searchItemsData")
	public void searchItemsTest(String itemSerial, String itemDescription, String itemPrice) throws Exception {
		boolean testFail = false;
//		beforeClass("111");
		if(this.driver == null){
			Assert.fail("Device not available: " + caps);
		}
		// Get Excel file path
		 
		  //ClassLoader classLoader = PerfectoUtils.class.getClassLoader();
		  //File inputWorkbook = new File(classLoader.getResource("testResults.xlsx").getFile());
		  //String absolutePath = inputWorkbook.getAbsolutePath();
		  
		  // Open workbook
		  ExcelDriver ed = new ExcelDriver("C:\\Users\\AvnerG\\git\\data\\testResults.xlsx", this.deviceDesc, true);
	 	  ed.setResultColumn(this.testCycle, true);
	 	  //Reporter.log(String.valueOf(Thread.currentThread().getId()));
	 	  try{
    	
	    	//this.driver.get("http://google.com");
	    	SearchItemsPageView view = new WalmartBaseView(this.driver).init().searchItem(itemSerial);
	    	 
	        String actualPrice = view.getItemPriceByIndex(1);
	        String actualDescription = view.getItemNameByIndex(1);
	        
	        if(!actualDescription.equals(itemDescription)){
	        	testFail = true;
	        	Reporter.log("Value is: " + actualDescription + ", Should be: " + itemDescription);
	        	String errorFile = PerfectoUtils.takeScreenshot(driver);
	    		Reporter.log("Error screenshot saved in file: " + errorFile);
	        }
	        if(!actualPrice.equals(itemPrice)){
	        	testFail = true;
	        	Reporter.log("Value is: " + actualPrice + ", Should be: " + itemPrice);
	        	String errorFile = PerfectoUtils.takeScreenshot(driver);
	        	Reporter.log("<br> <img src=" + errorFile + ".png style=\"max-width:50%;max-height:50%\" /> <br>");
	        	
	        }
	    }
	    catch(Exception e){
	    	ed.setResultByColumnName(false, this.testName, itemSerial, itemDescription, itemPrice);
	    	Assert.fail("See Reporter log for details");
	    }
	    
	    if(testFail){
	    	ed.setResultByColumnName(false, this.testName, itemSerial, itemDescription, itemPrice);
	    	Assert.fail("See reporter log for details");
	    }
	    else{
	    	ed.setResultByColumnName(true, this.testName, itemSerial, itemDescription, itemPrice);
	    }
	    //System.out.println(price);
	    //Reporter.log(price);
	      
	}
 
	@DataProvider (name = "searchItemsData", parallel = false)
	public Object[][] searchItemsData() throws Exception{
		
		  //ClassLoader classLoader = PerfectoUtils.class.getClassLoader();
		  //File inputWorkbook = new File(classLoader.getResource("testData.xlsx").getFile());
		  //String absolutePath = inputWorkbook.getAbsolutePath();
		  // Open workbook
		  ExcelDriver ed = new ExcelDriver("C:\\Users\\AvnerG\\git\\PerfectoCI\\data\\testData.xlsx", "items", false);
		  //ed.setWorkbook(absolutePath);
		  
		  // Open sheet
		  //ed.setSheet("items", false);
		  
		  // Read the sheet into 2 dim (String)Object array.
		  // "3" is the number of columns to read.
		  Object[][] s = ed.getData(3);

		  return s;
	}
}