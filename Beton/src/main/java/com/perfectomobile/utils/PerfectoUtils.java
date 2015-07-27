package com.perfectomobile.utils;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.regexp.recompile;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Reporter;

import com.google.common.base.Function;
//import com.perfectomobile.selenium.util.EclipseConnector;







import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class PerfectoUtils {

	private static final String REPOSITORY = "PUBLIC:";
	//private static Map<String, String> deviceProperties = new HashMap<String, String>();

	public static RemoteWebDriver getDriver(DesiredCapabilities cap, int retries, int retryIntervalSeconds)
	{
		System.out.println("Current capabilities " + cap.toString());

		RemoteWebDriver driver;
		boolean waitForDevice = true;
		int index = retries;
		do {
			try {
//					String host = System.getProperty("np.testHost", "qatestlab.perfectomobile.com");
//					String username = System.getProperty("np.testUsername", "test_automation@gmail.com");
//					String password = System.getProperty("np.testPassword", "Test_automation");
				
				String host = System.getProperty("np.testHost", "demo.perfectomobile.com");
				String username = System.getProperty("np.testUsername", "avnerg@perfectomobile.com");
				String password = System.getProperty("np.testPassword", "scvuanbsh");
				
				

				cap.setCapability("user", username);
				cap.setCapability("password", password);
				
//				doesn't work	
//					EclipseConnector connector = new EclipseConnector(); 
//					String eclipseExecutionId = connector.getExecutionId();                  
//					cap.setCapability("eclipseExecutionId", cap); 
//					
				
				driver = new RemoteWebDriver(new URL("https://" + host + "/nexperience/perfectomobile/wd/hub"), cap);
				//driver = new RemoteWebDriver(cap);
				System.out.println("Driver Created");
				return driver;

			} catch (Exception e) {
				index--;
				System.out.println("device not found: " + cap.toString() +"\n Retries left: " + index);
				//System.out.println();
				sleep(retryIntervalSeconds * 1000);
				if (e.getMessage().contains("command browser open")) {
					waitForDevice = false;
				}
			}
				//waitForDevice = false;
		} while (waitForDevice && index > 0);
		return null;

	}

//	private void uploadMedia(String resource, String repositoryKey) throws URISyntaxException, IOException {
//		repositoryKey = REPOSITORY;
//		String FILENAME;
//		File file = new File(FILENAME);
//		
//		d.uploadMedia(repositoryKey, file);
//		File file = loadResource(resource);
//		_driver.uploadMedia(repositoryKey, file);
//	}

	
	
	

	public static void installApp(String appLocation,RemoteWebDriver d )
	{
		Map<String,String> params = new HashMap<String,String>();
		params.put("file", appLocation);
		d.executeScript("mobile:application:install", params);
	}
	
	
	public static void startApp(String appName,RemoteWebDriver d )
	{
		Map<String,String> params = new HashMap<String,String>();
		params.put("name", appName);
		d.executeScript("mobile:application:open", params);
	}
	public static void uninstallApp(String appName,RemoteWebDriver d )
	{
		Map<String,String> params = new HashMap<String,String>();
		params.put("name", appName);
		d.executeScript("mobile:application:uninstall", params);
	}
	
	public static void swipe(String start,String end,RemoteWebDriver d )
	{
		Map<String,String> params = new HashMap<String,String>();
		params.put("start", start);  //50%,50%
		params.put("end", end);  //50%,50%

		d.executeScript("mobile:touch:swipe", params);

	}
	private static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
		}

	}

	public static void switchToContext(RemoteWebDriver driver, String context) {
		RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
		Map<String,String> params = new HashMap<String,String>();
		params.put("name", context);
		executeMethod.execute(DriverCommand.SWITCH_TO_CONTEXT, params);
	}

	public static void downloadReport(RemoteWebDriver driver, String type, String fileName) throws IOException {
		try { 
			String command = "mobile:report:download"; 
			Map<String, Object> params = new HashMap<>(); 
			params.put("type", type); 
			String report = (String)driver.executeScript(command, params); 
			File reportFile = new File(fileName + "." + type); 
			BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(reportFile)); 
			byte[] reportBytes = OutputType.BYTES.convertFromBase64Png(report); 
			output.write(reportBytes); output.close(); 
		} catch (Exception ex) { 
			System.out.println("Got exception " + ex); }
	}
	
	public static void downloadAttachment(RemoteWebDriver driver, String type, String fileName, String suffix) throws IOException {
		try {
			String command = "mobile:report:attachment";
			boolean done = false;
			int index = 0;

			while (!done) {
				Map<String, Object> params = new HashMap<>();	

				params.put("type", type);
				params.put("index", Integer.toString(index));

				String attachment = (String)driver.executeScript(command, params);
				
				if (attachment == null) { 
					done = true; 
				}
				else { 
					File file = new File(fileName + index + "." + suffix); 
					BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(file)); 
					byte[] bytes = OutputType.BYTES.convertFromBase64Png(attachment);	
					output.write(bytes); 
					output.close(); 
					index++; }
			}
		} catch (Exception ex) { 
			System.out.println("Got exception " + ex); 
		}
	}


	public static String getCurrentContextHandle(RemoteWebDriver driver) {		  
		RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
		String context =  (String) executeMethod.execute(DriverCommand.GET_CURRENT_CONTEXT_HANDLE, null);
		return context;
	}

	public static List<String> getContextHandles(RemoteWebDriver driver) {		  
		RemoteExecuteMethod executeMethod = new RemoteExecuteMethod(driver);
		List<String> contexts =  (List<String>) executeMethod.execute(DriverCommand.GET_CONTEXT_HANDLES, null);
		return contexts;
	}
	public static boolean checkVisual(RemoteWebDriver driver, String needle){
		String previousContext = getCurrentContextHandle(driver);
		// Switch to visual driver, to perform text checkpoint
		switchToContext(driver, "VISUAL");
		
		// Perform the checkpoint
		try{
			driver.findElement(By.linkText(needle));
		}
		catch(Exception e){
			switchToContext(driver, previousContext);
			return false;
		}
		
		// Switch back to webview context
		switchToContext(driver, previousContext);
		return true;
		
	}
	/* Wait until the objects loads until the timeout */
	  public static WebElement fluentWait(final By locator, RemoteWebDriver driver, long timeout) {
		 
		try {
			 FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				        .withTimeout(timeout, TimeUnit.SECONDS)
				        .pollingEvery(250, TimeUnit.MILLISECONDS)
				        .ignoring(Exception.class);
				        //.ignoring(NoSuchElementException.class);
				       
					  WebElement webelement = wait.until(new Function<WebDriver, WebElement>() {
						  public WebElement apply(WebDriver driver) {
				            return driver.findElement(locator);
						  }
					  });
					    return  webelement;
		} catch (Exception e) {
			return null;
		}
		 
		
	  }
	  public static String getDateAndTime(int offset){
		  Calendar c = Calendar.getInstance();
		  c.setTime(new Date());
		  c.add(Calendar.DATE, offset);
		  DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-z");
		  return dateFormat.format(c.getTime());
	  }
	  
	  
	  public static void getScreenshotOnError(RemoteWebDriver driver) {
		  
		String errorFile = takeScreenshot(driver);
		Reporter.log("Error screenshot saved in file: " + errorFile);
		  
		  
	  }
	  
	  
		public static void getScreenShot(RemoteWebDriver driver,String name )
		{
			driver   = (RemoteWebDriver) new Augmenter().augment( driver );
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

			try {
				FileUtils.copyFile(scrFile, new File("c:\\test\\"+name+".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	  
	  public static String takeScreenshot(RemoteWebDriver driver) {
		  
		  String filePath = new File("").getAbsolutePath();
		  filePath += "\\test-output\\screenshots";
		  File theDir = new File(filePath);

		  // if the directory does not exist, create it
		  if (!theDir.exists()) {
			  //System.out.println("creating directory: " + directoryName);

			  try{
				  theDir.mkdir();
			  } 
			  catch(SecurityException se) {
				  
				  return null;
			  }        
		  }
		  filePath+= "\\";		  
		  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		  String filename = filePath + getDateAndTime(0) + ".png";
		  System.out.println(filename);
			try {
				FileUtils.copyFile(scrFile, new File(filename+".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  
		  return filename;
	  }
	  
	  public static DesiredCapabilities generateCapabilitiesObject(HashMap<String,String> map){
		  
		  String browserName = map.get("browserName"); 
		  if(browserName == null){
			  browserName = "";
		  }
		  DesiredCapabilities capabilities = new DesiredCapabilities(browserName, "", Platform.ANY);
		  for(Entry<String,String> entry : map.entrySet()){
			  capabilities.setCapability(entry.getKey(),entry.getValue());
		  }
		  return capabilities;
	  }
	  
	  public static Object[][] getCapabilitiesArray(ArrayList<HashMap<String,String>> mapsArray){
		  Object[][] returnArray;
		  int arraySize = mapsArray.size();
		  returnArray = new Object[arraySize][1];
		  for(int i = 0; i < arraySize; i++){
			  returnArray[i][0] = generateCapabilitiesObject(mapsArray.get(i));
		  }
		  return returnArray;
	  }
	  public static boolean isDevice(RemoteWebDriver driver){
          //first check if driver is a mobile device:
            Capabilities capabilities = driver.getCapabilities();
            String type = (String) capabilities.getCapability("browserName");
            if (type.toLowerCase().equals("chrome") || type.toLowerCase().equals("firefox")){
                  return false;
            }
            return true;
      }
      public static HashMap<String, String> getDevicePropertiesList(RemoteWebDriver driver) {
    	  //hashmap to contain device properties
    	  HashMap<String, String> deviceProperties = new HashMap<String, String>();
          deviceProperties = new HashMap<String, String>();
            
          if (!isDevice(driver))
        	  return null;
        
          Map<String, Object> params = new HashMap<>();
          params.put("property", "ALL");
          String properties = (String) driver.executeScript("mobile:handset:info", params);

          List<String> items = Arrays.asList(properties.split(","));
          String key,value;
        //build hashmap for all device properties:
          for (int i = 0; i < items.size(); i=i+2) {
        	  key=items.get(i);
        	  if (key.startsWith("[")||key.startsWith(" ")){
        		  key=key.substring(1);
        	  }
          value=items.get(i+1);
          if(value.startsWith(" ")){
        	  value=value.substring(1);
          }
          if (value.startsWith("[")){
        	  for (int j = i+2; j < items.size(); j++) {
        		  value=value+","+items.get(j);
                  if (value.endsWith("]")){
                	  value=value.substring(1,value.length()-1);
                      i=j-1;
                      break;
                  }
              }
          }
          if (value.endsWith("]")){
        	  value=value.substring(0,value.length()-1);
          }
          deviceProperties.put(key, value);
      }
          return deviceProperties;
   }
    /*****************************************************************************
    * gets a specific device property out of the Dictionary.
    * returns the value of the property
    * for example:
    * getDeviceProperty("Model") will return the model of the device(ie iPhone-6)
    ******************************************************************************/
    public static String getDeviceProperty(RemoteWebDriver driver, String Property){
    	HashMap<String, String> deviceProperties = getDevicePropertiesList(driver);
        return (deviceProperties.get(Property));
    }


//	  public static DesiredCapabilities getCapabilites(String deviceName, String platformName, String platformVersion, String manufacturer,
//			  String deviceModel, String deviceResolution, String deviceNetwork, String deviceLocation, String deviceDescription, String browserName, String automationName) throws Exception{
//		  
//		  DesiredCapabilities capabilities = new DesiredCapabilities(browserName, "", Platform.ANY);
//		  		  
//		  if(!deviceName.equals("")){
//			  capabilities.setCapability("deviceName", deviceName);
//		  }
//		  if(!platformName.equals("")){
//			  capabilities.setCapability("platformName", platformName);
//		  }
//		  if(!platformVersion.equals("")){
//			  capabilities.setCapability("platformVersion", platformVersion);
//		  }
//		  if(!manufacturer.equals("")){
//			  capabilities.setCapability("manufacturer", manufacturer);
//		  }
//		  if(!deviceModel.equals("")){
//			  capabilities.setCapability("model", deviceModel);
//		  }
//		  if(!deviceResolution.equals("")){
//			  capabilities.setCapability("resolution", deviceResolution);
//		  }
//		  if(!deviceNetwork.equals("")){
//			  capabilities.setCapability("network", deviceNetwork);
//		  }
//		  if(!deviceLocation.equals("")){
//			  capabilities.setCapability("location", deviceLocation);
//		  }
//		  if(!deviceDescription.equals("")){
//			  capabilities.setCapability("description", deviceDescription);
//		  }
//		  if(!automationName.equals("")){
//			  capabilities.setCapability("automationName", automationName);
//		  }
//		  		 
//		  return capabilities;
//	  }
}