package com.perfectomobile.androidCommunityPOM;

import org.openqa.selenium.By;

import com.perfectomobile.utils.*;
import com.perfectomobile.utils.PerfectoUtils.*;

import io.appium.java_client.android.AndroidDriver;

public class ANDROID_SearchResultsView extends ANDROID_CommunityBaseView{
	
	By btnNavUp = By.id("android:id/up");
//	By btnNavUp = By.id("android:id/action_bar_title");
	
	public ANDROID_SearchResultsView(AndroidDriver driver) {
		super(driver);
	}
	
	public ANDROID_PostPageView selectSearchResult(String findText){
		VisualDriverControl.clickVisualText(this.driver, findText, true, ScrollNext.SWIPE_UP, 30, 1, 90, MatchMode.CONTAIN,96);
		return new ANDROID_PostPageView(this.driver);
	}
	
	public ANDROID_CommunityBaseView backToSetSearch(){
		System.out.println(btnNavUp);
		this.driver.findElement(btnNavUp).click();
		return new ANDROID_CommunityBaseView(this.driver);
	}
}
