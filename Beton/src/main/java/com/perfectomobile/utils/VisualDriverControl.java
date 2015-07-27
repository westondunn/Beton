package com.perfectomobile.utils;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.perfectomobile.utils.PerfectoUtils.*;


public class VisualDriverControl {
	
	/*****************************************************************************
	 * clickByTextOffset()
	 * 		Args:
	 * 			driver:			RemoteWebDriver from test
	 * 			label:			Label to find on screen
	 * 			labelPosition:	Direction of click location relative to label. Can be:
	 * 								Uses Enum LabelPosition: Above;Below;Left;Right
	 * 			offset: 		Value of the offset in pixels or percentage. percentage is noted by a trailing % character
	 ******************************************************************************/
	public static void clickByTextOffset(RemoteWebDriver driver, String label,  LabelPosition labelPosition, String offset){
		//Get current context
		String initialContext = PerfectoUtils.getCurrentContextHandle(driver);
		String shift;		
		PerfectoUtils.switchToContext(driver, "VISUAL");
		if(labelPosition.toString()=="ABOVE"){
			shift = "Above";
		}else{
			shift = "";
		}
		
		
		String command = "mobile:text:select";
		Map<String, Object> params = new HashMap<>();
		params.put("content", label);
		params.put("shift", shift);
		params.put("offset", offset);
		
		driver.executeScript(command, params);
		
		PerfectoUtils.switchToContext(driver, initialContext);		
	}
	
	/*****************************************************************************
	 * clickVisualText()
	 * 		Args:
	 * 			driver:			RemoteWebDriver from test
	 * 			label:			Label to find on screen
	 * 			scroll:			Scroll if true (default is false)			
	 * 			next: 			Direction of scroll; Possible values: (default is SWIPE_UP)
	 * 								Uses Enum ScrollNext:	SWIPE_UP,SWIPE_DOWN,SWIPE_RIGHT,SWIPE_LEFT,UP,DOWN,RIGHT,LEFT 								
	 * 			maxScroll:		Maximum number of times to scroll (default is 5); Possible values 0 - 100
	 * 			index:			Number of index if multiples found; Possible values 1 - 2147483647
	 * 			threshold:		Minimum accuracy. Possible Values 1 - 100
	 * 			matchMode:		How to process match ( default is contain)
	 * 								Uses Enum MatchMode
	 * 			dpi:			Specifies the dots per inch resolution (default is 96) 
	 * 								Please try first the value 160, if the direction is correct then find the best value around this value [120-200]. 
	 * 								If not, then try the lower range: [50-120]; Possible values 1 - 2147483647
	 ******************************************************************************/
	public static void clickVisualText(RemoteWebDriver driver, String label, Boolean scroll, ScrollNext next, int maxScroll, int index, int threshold, MatchMode matchMode, int dpi){
		//Get current context
		String initialContext = PerfectoUtils.getCurrentContextHandle(driver);
		
		PerfectoUtils.switchToContext(driver, "VISUAL");
				
		String command = "mobile:text:select";
		Map<String, Object> params = new HashMap<>();
		params.put("content", label);
		if(scroll){
			params.put("scrolling", "scroll");
		}				
		params.put("next", next.toString());//SWIPE_UP,SWIPE_DOWN,SWIPE_RIGHT,SWIPE_LEFT,UP,DOWN,RIGHT,LEFT
		
		if(!(String.valueOf(maxScroll)=="")){
			if(maxScroll<0){maxScroll=0;}
			if(maxScroll>100){maxScroll=100;}
			params.put("maxscroll", maxScroll);
		}
		if (!(String.valueOf(index)=="")){	//1 - 2147483647
			if(index<1){index=1;}
			if(index>2147483647){index=2147483647;}
			params.put("index", index);
		}
		if (!(String.valueOf(threshold)=="")){	// 20 - 100
			if(threshold<20){threshold=20;}
			if(threshold>100){threshold=100;}
			params.put("threshold", threshold);
		}		
		params.put("match", matchMode.toString());	//contain,equal,startwith,endwith,first,last,index
			
		if (!(String.valueOf(dpi)=="")){
			if(dpi<1){dpi=1;}
			if(dpi>2147483647){dpi=2147483647;}
			params.put("dpi", dpi);
		}
		
		driver.executeScript(command, params);
		
		PerfectoUtils.switchToContext(driver, initialContext);		
	}
	
	/*****************************************************************************
	 * findVisualTextElement()	Basic Method
	 * 		Returns:			Boolean
	 * 		Args:
	 * 			driver:			RemoteWebDriver from test
	 * 			label:			Label to find on screen
	 ******************************************************************************/
	public static Boolean findVisualTextElement (RemoteWebDriver driver, String label){
		//Get current context
		String initialContext = PerfectoUtils.getCurrentContextHandle(driver);
		
		//Change driver to VISUAL context
		PerfectoUtils.switchToContext(driver, "VISUAL");
		
		String command = "mobile:text:find";
		Map<String, Object> params = new HashMap<>();
		params.put("content", label);
				
		Boolean found =  (Boolean) driver.executeScript(command, params);
		
		// change driver back to initial context
		PerfectoUtils.switchToContext(driver, initialContext);
		
		return found;
	}
	
	/*****************************************************************************
	 * findVisualTextElement()	Overloaded Method
	 * 		Returns:			Boolean
	 * 		Args:
	 * 			driver:			RemoteWebDriver from test
	 * 			label:			Label to find on screen
	 * 			scroll:			Scroll if true (default is false)			
	 * 			next: 			Direction of scroll; Possible values: (default is SWIPE_UP)
	 * 								Uses Enum ScrollNext:	SWIPE_UP,SWIPE_DOWN,SWIPE_RIGHT,SWIPE_LEFT,UP,DOWN,RIGHT,LEFT 								
	 * 			maxScroll:		Maximum number of times to scroll (default is 5); Possible values 0 - 100
	 * 			index:			Number of index if multiples found; Possible values 1 - 2147483647
	 * 			threshold:		Minimum accuracy. Possible Values 1 - 100
	 * 			matchMode:		How to process match ( default is contain)
	 * 								Uses Enum MatchMode
	 * 			dpi:			Specifies the dots per inch resolution (default is 96) 
	 * 								Please try first the value 160, if the direction is correct then find the best value around this value [120-200]. 
	 * 								If not, then try the lower range: [50-120]; Possible values 1 - 2147483647
	 ******************************************************************************/
	public static Boolean findVisualTextElement (RemoteWebDriver driver, String label, Boolean scroll, ScrollNext next, int maxScroll, int index, int threshold, MatchMode matchMode, int dpi){
		//Get current context
		String initialContext = PerfectoUtils.getCurrentContextHandle(driver);
		
		//Change driver to VISUAL context
		PerfectoUtils.switchToContext(driver, "VISUAL");
		
		String command = "mobile:text:find";
		Map<String, Object> params = new HashMap<>();
		params.put("content", label);
		if(scroll){ 	//noscroll,scroll
			params.put("scrolling", "scroll");
		}
		
		params.put("next", next.toString());	//SWIPE_UP,SWIPE_DOWN,SWIPE_RIGHT,SWIPE_LEFT,UP,DOWN,RIGHT,LEFT
		
		if(!(String.valueOf(maxScroll)=="")){	// 0 - 100
			if(maxScroll<0){maxScroll=0;}
			if(maxScroll>100){maxScroll=100;}
			params.put("maxscroll", maxScroll);
		}
		if (!(String.valueOf(index)=="")){	//1 - 2147483647
			if(index<1){index=1;}
			if(index>2147483647){index=2147483647;}
			params.put("index", index);
		}
		if (!(String.valueOf(threshold)=="")){	// 20 - 100
			if(threshold<20){threshold=20;}
			if(threshold>100){threshold=100;}
			params.put("threshold", threshold);
		}
		
		params.put("match", matchMode.toString());	//contain,equal,startwith,endwith,first,last,index
		
		if (!(String.valueOf(dpi)=="")){
			if(dpi<1){dpi=1;}
			if(dpi>2147483647){dpi=2147483647;}
			params.put("dpi", dpi);
		}	
		
		Boolean found =  (Boolean) driver.executeScript(command, params);
		
		// change driver back to initial context
		PerfectoUtils.switchToContext(driver, initialContext);
		
		return found;
	}
	
	/*****************************************************************************
	 * findVisualImageElement()	Basic Method
	 * 		Returns:			Boolean
	 * 		Args:
	 * 			driver:			RemoteWebDriver from test
	 * 			filePath:		Path to image file :  or PRIVATE:images\\image.png
	 * 								- Uses Perfecto media repository if starts with PRIVATE: or PUBLIC: Example-- PRIVATE:images/image.png
	 * 								- Uploads file to media repository images folder if it's a local file path. 
	 * 									Must use double backslash when referencing local path-- ie. C:\\temp\\image.png
	 ******************************************************************************/
	public static Boolean findVisualImageElement(RemoteWebDriver driver, String filePath){
		Boolean found = false;
		//Get current context
		String initialContext = PerfectoUtils.getCurrentContextHandle(driver);
		
		//Change driver to VISUAL context
		PerfectoUtils.switchToContext(driver, "VISUAL");
		
		String command = "mobile:image:find";
		Map<String, Object> params = new HashMap<>();
		
		if(filePath.contains("PRIVATE:") || filePath.contains("PUBLIC:")){
			params.put("content", filePath);
		}else{
			String repoPath = PerfectoUtils.uploadLocalFileToMediaRepo(driver, filePath, "PRIVATE:images");
			params.put("content", repoPath);			
		} 
		
		//executeScript
		found = Boolean.valueOf((String) driver.executeScript(command, params));
				
		// change driver back to initial context
		PerfectoUtils.switchToContext(driver, initialContext);
		
		//return result
		return found;		
	}

	/*****************************************************************************
	 * findBoundedVisualImageElement()	Basic Method
	 * 		Returns:			Boolean
	 * 		Args:
	 * 			driver:			RemoteWebDriver from test
	 * 			filePath:		Path to image file :  or PRIVATE:images\\image.png
	 * 								- Uses Perfecto media repository if starts with PRIVATE: or PUBLIC: Example-- PRIVATE:images/image.png
	 * 								- Uploads file to media repository images folder if it's a local file path. 
	 * 									Must use double backslash when referencing local path-- ie. C:\\temp\\image.png
	 * 			bound			The ratio in percentage by which the original needle width may vary. 
	 * 								- The ratio is defined as a ( 100 - specified percentage ) / 100
	 * 			threshold		Specifies the percentage of acceptable match level. 
	 * 								- Range 20 - 100
	 * 								- Default is calculated by the system according to the needle. 
	 * 								- Too low values might lead to FALSE POSITIVE results while too high values might lead to FALSE NEGATIVE results. 
	 ******************************************************************************/
	public static Boolean findBoundedVisualImageElement(RemoteWebDriver driver, String filePath, int bound, int threshold){
		Boolean found = false;
		//Get current context
		String initialContext = PerfectoUtils.getCurrentContextHandle(driver);
		
		//Change driver to VISUAL context
		PerfectoUtils.switchToContext(driver, "VISUAL");
		
		String command = "mobile:image:find";
		Map<String, Object> params = new HashMap<>();
		//set params
		params.put("match", "bounded");
		if(bound<0){bound = 0;}
		if(bound>99){bound = 99;}
		params.put("imageBounds.needleBound", bound);
		if(threshold < 20){threshold = 20;}
		if(threshold > 100){threshold = 100;}
		params.put("threshold", threshold);
		
		if(filePath.contains("PRIVATE:") || filePath.contains("PUBLIC:")){
			params.put("content", filePath);
		}else{
			String repoPath = PerfectoUtils.uploadLocalFileToMediaRepo(driver, filePath, "PRIVATE:images");
			params.put("content", repoPath);			
		}
		
		//executeScript
		found = Boolean.valueOf((String) driver.executeScript(command, params));
		
		// change driver back to initial context
		PerfectoUtils.switchToContext(driver, initialContext);
		
		//return result
		
		return found;		
	}
}

	
