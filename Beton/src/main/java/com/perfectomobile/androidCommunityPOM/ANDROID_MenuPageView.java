package com.perfectomobile.androidCommunityPOM;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.perfectomobile.utils.PerfectoUtils;

public class ANDROID_MenuPageView extends ANDROID_CommunityBaseView{
	
	// Page elements
		By btnCloseMenu = By.xpath("//*[@text='Perfecto Mobile']");
		By btnDashboard = By.xpath("//*[@text='Dashboard']");
		By btnStash = By.xpath("//*[@text='Stash']");
		By btnTags = By.xpath("//*[@text='Tags']");		
		By btnCategories = By.xpath("//*[@text='Categories']");
		By btnPosts = By.xpath("//*[@text='Posts']");
		By btnSeries = By.xpath("//*[@text='Series']");
		By btnMembers = By.xpath("//*[@text='Members']");
		By btnProfile = By.xpath("//*[@text='Profile']");
		By btnCommunities = By.xpath("//*[@text='Your Communities']");
		By btnSettings = By.xpath("//*[@text='Settings']");
	
	

	public ANDROID_MenuPageView(RemoteWebDriver driver) {
		super(driver);
		PerfectoUtils.sleep(3000);
		
	}
	public ANDROID_CommunityBaseView closeMenu(){
		this.driver.findElement(btnCloseMenu).click();
		return new ANDROID_CommunityBaseView(this.driver);
	}
	
	public ANDROID_CommunityBaseView gotoDashboard(){
		this.driver.findElement(btnDashboard).click();
		return new ANDROID_CommunityBaseView(this.driver);
	}
	
	public ANDROID_ProfilePageView gotoProfile(){
		this.driver.findElement(btnDashboard).click();
		return new ANDROID_ProfilePageView(this.driver);
	}
//	public PCStashView gotoStash(){
//		this.driver.findElement(btnStash).click();
//		return new PCStashView(this.driver);
//	}
//	
//	public PCTagsView gotoTags(){
//		this.driver.findElement(btnTags).click();
//		return new PCTagsView(this.driver);
//	}
	
	public ANDROID_SettingsPageView gotoSettings(){
		this.driver.findElement(btnSettings).click();
		return new ANDROID_SettingsPageView(this.driver);
	}
}
