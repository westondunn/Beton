package com.perfectomobile.beton;

import java.io.IOException;

import org.apache.http.util.Asserts;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.perfectomobile.dataDrivers.excelDriver.ExcelDriver;
import com.perfectomobile.test.BasicTest;
import com.perfectomobile.test.KeywordDrivenTestAdapter;
import com.perfectomobile.utils.PerfectoUtils;

public class KeywordDrivenTest extends BasicTest{

	@Factory(dataProvider="factoryData")
	public KeywordDrivenTest(DesiredCapabilities caps) {
		super(caps);
		// TODO Auto-generated constructor stub
	}
	
	@Test
	public void googleTest() throws Exception{
		KeywordDrivenTestAdapter testAdapter = new KeywordDrivenTestAdapter(sysProp.get("googleTestWorkbook"), sysProp.get("googleTestDataSheet"));
		try{
			testAdapter.run(driver);
		}
		catch(Exception e){
			failTest();
		}
		passTest();
	}

	private void passTest() throws Exception{
		String scrFile = PerfectoUtils.takeScreenshot(driver);
		resultSheet.setResultByColumnName(true, this.testName);
		resultSheet.addScreenshotByRowNameAsLink(scrFile, this.testName);
	}
	private void failTest() throws Exception{
		String errorFile = reportFail("Test Failed","");
		resultSheet.setResultByColumnName(false, this.testName);
		resultSheet.addScreenshotByRowNameAsLink(errorFile, this.testName);
		Assert.fail();
		
		
	}
}
