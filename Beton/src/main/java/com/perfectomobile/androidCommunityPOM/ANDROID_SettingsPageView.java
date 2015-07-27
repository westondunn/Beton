package com.perfectomobile.androidCommunityPOM;

import org.openqa.selenium.By;

import io.appium.java_client.android.AndroidDriver;

public class ANDROID_SettingsPageView extends ANDROID_CommunityBaseView{
	
	By btnLogout = By.xpath("//*[@text='Logout']");
	
	public ANDROID_SettingsPageView(AndroidDriver driver) {
		super(driver);
	}
	
	public ANDROID_CommunityBaseView logout(){
		this.driver.findElement(btnLogout).click();
		return new ANDROID_CommunityBaseView(this.driver);
	}

}
