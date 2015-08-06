package com.perfectomobile.androidCommunityPOM;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.perfectomobile.utils.PerfectoUtils;


public class ANDROID_PostPageView extends ANDROID_CommunityBaseView {
	
	String navButton = "'Navigate up'";
	

	By btnNavUp = By.id("android:id/up");		
	By txtAuthor = By.id("com.bloomfire.android.perfecto:id/authors");
	By txtDescription = By.id("com.bloomfire.android.perfecto:id/description");
	
	By btnRefresh = By.id("com.bloomfire.android.perfecto:id/item_refresh");	// Works
	By btnOptions = By.xpath("//*[@class='android.widget.ImageButton']");	// Works
	By txtFollow = By.xpath("//*[@text='Follow']");
	
	public ANDROID_PostPageView(RemoteWebDriver driver) {
		super(driver);
		PerfectoUtils.sleep(3000);
	}
	
	public ANDROID_SearchResultsView backToSearchResults(){
		//System.out.println(btnNavUp);
		this.driver.findElement(btnNavUp).click();
		return new ANDROID_SearchResultsView(this.driver);
	}
	
	public ANDROID_PostPageView refreshPost(){
		//System.out.println("--------------------->>> Clicking " + btnRefresh);
		this.driver.findElement(btnRefresh).click();
		return new ANDROID_PostPageView(this.driver);
	}
	public ANDROID_PostPageView followPost(){
		//System.out.println("--------------------->>> Clicking " + btnOptions);
		this.driver.findElement(btnOptions).click();		
		this.driver.findElement(txtFollow).click();
		return new ANDROID_PostPageView(this.driver);
	}
	
	public String getAuthor(){
		String author = this.driver.findElement(txtAuthor).getAttribute("text");
		System.out.println("------------------------>>>Post Author:" + author);
		return author;
	}
	public String getDescription(){
		String author = this.driver.findElement(txtDescription).getAttribute("text");
		System.out.println("------------------------>>>Post Description:" + author);
		return author;
	}

}
