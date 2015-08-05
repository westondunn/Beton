//package com.perfectomobile.IOSCommunityPOM;
//
//import org.openqa.selenium.By;
//
//import com.perfectomobile.utils.*;
//import com.perfectomobile.utils.PerfectoUtils.*;
//
//import io.appium.java_client.android.AndroidDriver;
//
//public class IOS_SearchResultsView extends IOS_CommunityBaseView{
//	
//	By btnNavUp = By.id("android:id/up");
////	By btnNavUp = By.id("android:id/action_bar_title");
//	
//	public SearchResultsView(AndroidDriver driver) {
//		super(driver);
//	}
//	
//	public PostPageView selectSearchResult(String findText){
//		VisualDriverControl.clickVisualText(this.driver, findText, true, ScrollNext.SWIPE_UP, 30, 1, 90, MatchMode.CONTAIN,96);
//		return new ANDROID_PostPageView(this.driver);
//	}
//	
//	public CommunityBaseView backToSetSearch(){
//		System.out.println(btnNavUp);
//		this.driver.findElement(btnNavUp).click();
//		return new CommunityBaseView(this.driver);
//	}
//}
