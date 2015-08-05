//package com.perfectomobile.IOSCommunityPOM;
//
//import org.openqa.selenium.By;
//
//import io.appium.java_client.android.AndroidDriver;
//
//import com.perfectomobile.utils.PerfectoUtils;
//
//
//
//public class IOS_MenuPageView extends IOS_CommunityBaseView{
//	
//	// Page elements
//		By btnCloseMenu = By.xpath("//*[@text='Perfecto Mobile']");
//		By btnDashboard = By.xpath("//*[@text='Dashboard']");
//		By btnStash = By.xpath("//*[@text='Stash']");
//		By btnTags = By.xpath("//*[@text='Tags']");		
//		By btnCategories = By.xpath("//*[@text='Categories']");
//		By btnPosts = By.xpath("//*[@text='Posts']");
//		By btnSeries = By.xpath("//*[@text='Series']");
//		By btnMembers = By.xpath("//*[@text='Members']");
//		By btnProfile = By.xpath("//*[@text='Profile']");
//		By btnCommunities = By.xpath("//*[@text='Your Communities']");
//		By btnSettings = By.xpath("//*[@text='Settings']");
//	
//	
//
//	public MenuPageView(AndroidDriver driver) {
//		super(driver);
//		PerfectoUtils.sleep(3000);
//		
//	}
//	public CommunityBaseView closeMenu(){
//		this.driver.findElement(btnCloseMenu).click();
//		return new CommunityBaseView(this.driver);
//	}
//	
//	public CommunityBaseView gotoDashboard(){
//		this.driver.findElement(btnDashboard).click();
//		return new CommunityBaseView(this.driver);
//	}
//	
////	public PCStashView gotoStash(){
////		this.driver.findElement(btnStash).click();
////		return new PCStashView(this.driver);
////	}
////	
////	public PCTagsView gotoTags(){
////		this.driver.findElement(btnTags).click();
////		return new PCTagsView(this.driver);
////	}
//	
//	public SettingsPageView gotoSettings(){
//		this.driver.findElement(btnSettings).click();
//		return new SettingsPageView(this.driver);
//	}
//}
