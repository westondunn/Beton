package com.perfectomobile.test;

import org.openqa.selenium.remote.DesiredCapabilities;

public class Init {

	protected static String inputDataSheet = "data/testData.xlsx";
	protected static String outputResultSheet = "data/testResults.xlsx";
	protected static String remoteDriverURL = "http://localhost:4444/wd/hub";
	protected static int retryIntervalSeconds = 30;
	protected static int driverRetries = 5;
		
	
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
	public static int getRetryIntervalSeconds() {
		return retryIntervalSeconds;
	}
	public static void setRetryIntervalSeconds(int retryIntervalSeconds) {
		Init.retryIntervalSeconds = retryIntervalSeconds;
	}
	public static int getDriverRetries() {
		return driverRetries;
	}
	public static void setDriverRetries(int driverRetries) {
		Init.driverRetries = driverRetries;
	}

	
}
