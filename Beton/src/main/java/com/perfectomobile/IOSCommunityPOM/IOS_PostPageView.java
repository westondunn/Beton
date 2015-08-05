//package com.perfectomobile.IOSCommunityPOM;
//
//import org.openqa.selenium.By;
//
//import io.appium.java_client.android.AndroidDriver;
//
//import com.perfectomobile.utils.PerfectoUtils;
//
//
//public class IOS_PostPageView extends IOS_CommunityBaseView {
//	
//	String navButton = "'Navigate up'";
//	
//	//*[@class='android.widget.LinearLayout']/group[2]/text
//	
////	By btnNavUp = By.xpath("//*[contains(@contentDesc," + navButton + ")]/group[2]/text[1]");	//Doesn't work
////	By btnNavUp = By.xpath("//*[@class='android.widget.LinearLayout']/group[2]/text");	//Doesn't work 
////	By btnNavUp = By.xpath("//device/view/group[1]/view[1]/group[1]/view[1]/group[1]/group[1]");	//Doesn't work 
//	By btnNavUp = By.id("android:id/up");		
//	By txtAuthor = By.id("com.bloomfire.android.perfecto:id/authors");
//	By txtDescription = By.id("com.bloomfire.android.perfecto:id/description");
//	
//	By btnRefresh = By.id("com.bloomfire.android.perfecto:id/item_refresh");	// Works
//	By btnOptions = By.xpath("//*[@class='android.widget.ImageButton']");	// Works
//	By txtFollow = By.xpath("//*[@text='Follow']");
//	
//	public PostPageView(AndroidDriver driver) {
//		super(driver);
//		PerfectoUtils.sleep(3000);
//	}
//	
//	public SearchResultsView backToSearchResults(){
//		System.out.println(btnNavUp);
//		this.driver.findElement(btnNavUp).click();
//		return new SearchResultsView(this.driver);
//	}
//	
//	public PostPageView refreshPost(){
//		System.out.println("--------------------->>> Clicking " + btnRefresh);
//		this.driver.findElement(btnRefresh).click();
//		return new PostPageView(this.driver);
//	}
//	public PostPageView followPost(){
//		System.out.println("--------------------->>> Clicking " + btnOptions);
//		this.driver.findElement(btnOptions).click();		
//		this.driver.findElement(txtFollow).click();
//		return new PostPageView(this.driver);
//	}
//	
//	public String getAuthor(){
//		String author = this.driver.findElement(txtAuthor).getAttribute("text");
//		System.out.println("------------------------>>>Post Author:" + author);
//		return author;
//	}
//	public String getDescription(){
//		String author = this.driver.findElement(txtDescription).getAttribute("text");
//		System.out.println("------------------------>>>Post Description:" + author);
//		return author;
//	}
//
//}
