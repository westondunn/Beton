package com.perfectomobile.test;

import org.openqa.selenium.remote.DesiredCapabilities;

public class Init {

	protected static String inputDataSheet = "data/testData.xlsx";
	protected static String outputResultSheet = "data/testResults.xlsx";
	protected static String remoteDriverURL = "http://localhost:4444/wd/hub";
	protected int retryIntervalSeconds = 30;
	protected int driverRetries = 5;
		
	
	public static String getInputDataSheet() {
		return inputDataSheet;
	}
	public static void setInputDataSheet(String inputDataSheet) {
		Init.inputDataSheet = inputDataSheet;
	}
	public static String getOutputResultSheet() {
		return outputResultSheet;
	}
	public static void setOutputResultSheet(String outputResultSheet) {
		Init.outputResultSheet = outputResultSheet;
	}
	public static String getRemoteDriverURL() {
		return remoteDriverURL;
	}
	public static void setRemoteDriverURL(String remoteDriverURL) {
		Init.remoteDriverURL = remoteDriverURL;
	}	
	public int getRetryIntervalSeconds() {
		return retryIntervalSeconds;
	}
	public void setRetryIntervalSeconds(int retryIntervalSeconds) {
		this.retryIntervalSeconds = retryIntervalSeconds;
	}
	public int getDriverRetries() {
		return driverRetries;
	}
	public void setDriverRetries(int driverRetries) {
		this.driverRetries = driverRetries;
	}
	
}
