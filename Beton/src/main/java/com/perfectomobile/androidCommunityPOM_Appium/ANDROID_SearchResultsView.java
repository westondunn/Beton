package com.perfectomobile.androidCommunityPOM_Appium;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.perfectomobile.utils.*;
import com.perfectomobile.utils.PerfectoUtils.*;


// TODO: Auto-generated Javadoc
/**
 * The Class ANDROID_SearchResultsView.
 */
public class ANDROID_SearchResultsView extends ANDROID_CommunityBaseView{
	
	/** The btn nav up. */
	By btnNavUp = By.id("android:id/up");
//	By btnNavUp = By.id("android:id/action_bar_title");
	
	/**
 * Instantiates a new ANDROID_SearchResultsView.
 *
 * @param driver the driver
 */
public ANDROID_SearchResultsView(AndroidDriver driver) {
		super(driver);
	}
	
	/**
	 * Select search result.
	 *
	 * @param findResult the search result to select
	 * @return the ANDROID_PostPageView object
	 */
	public ANDROID_PostPageView selectSearchResult(String findResult){
		VisualDriverControl.clickVisualText(this.driver, findResult, true, ScrollNext.SWIPE_UP, 30, 1, 90, MatchMode.CONTAIN,96);
		return new ANDROID_PostPageView(this.driver);
	}
	
	/**
	 *  Go Back to set the search query.
	 *
	 * @return the ANDROID_CommunityBaseView
	 */
	public ANDROID_CommunityBaseView backToSetSearch(){
		//System.out.println(btnNavUp);
		this.driver.findElement(btnNavUp).click();
		return new ANDROID_CommunityBaseView(this.driver);
	}
}
