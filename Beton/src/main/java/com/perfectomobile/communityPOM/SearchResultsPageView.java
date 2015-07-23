package com.perfectomobile.communityPOM;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

import com.perfectomobile.utils.PerfectoUtils;

public class SearchResultsPageView extends CommunityBaseView {
	
	//
	private By searchPage = By.xpath("//*[@class='search-results']|//*[contains(text(),'No results to display')]");
	
	private String searchResults = "//li[starts-with(@class,'contribution-card')]";
	private String postsResults = "(//li[starts-with(@class,'contribution-card')]/a[starts-with(@href,'/posts/')])";
	private String seriesResults = "(//li[starts-with(@class,'contribution-card')]/a[starts-with(@href,'/series/')])";
	
	
	
	// Page elements
	private String itemPrice = "(//*[@class='price' or @class='price price-display' or @class='item-price'])";
	private String itemName	= "(//*[@class='name' or @class='js-product-title'] | //div[@class='item-page-header ']/h2)";
	
		
	/**********************************************************************
	 * 		Constructor
	 * @throws IOException 
	 **********************************************************************/
	public SearchResultsPageView(RemoteWebDriver driver) throws IOException{
        super(driver);
        
        //validate page loaded successfully before proceeding
        try{
        	PerfectoUtils.fluentWait(searchPage, driver, 30);
        }
        catch(Exception e){
        	String errorFile = PerfectoUtils.takeScreenshot(driver);
			Reporter.log("Error screenshot saved in file: " + errorFile);
        	throw new IllegalStateException();
        }
    }
	
	//total results:
	public int getResultsCount(){
		try {
			return this.driver.findElementsByXPath(searchResults).size();
			
		} catch (Exception e) {
			return 0;
		}
	}
	//get number of posts found:
	public int getPostsCount(){
		
		try {
			return this.driver.findElementsByXPath(postsResults).size();
						
		} catch (Exception e) {
			return 0;
		}
	}
	
	//get number of series found:
	public int getSeriesCount(){
		
		try {
			return this.driver.findElementsByXPath(seriesResults).size();

		} catch (Exception e) {
			return -1;
		}
	}
	
	//clicks post number index
	public PostPageView clickPost(int index){
		By xpath = By.xpath(postsResults + "[" + index + "]") ;
		try {
			this.driver.findElement(xpath).click();
			return new PostPageView(driver);
		} catch (Exception e) {
			System.out.println("didnt find post number " + index);
			return null;
		}
	}
	
	//click series number index
	public void clickSeries(int index){
		By xpath = By.xpath(seriesResults + "[" + index + "]") ;
		try {
			this.driver.findElement(xpath).click();
		} catch (Exception e) {
			System.out.println("didnt find series number " + index);
			
		}
	}
	
	

}