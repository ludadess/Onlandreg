package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utils.MyDriverClass;


public class HomePage {
	private static Logger log = LogManager.getLogger(HomePage.class.getName());
	private WebDriver driver;
	By pageHeader = By.xpath("//h1[contains(text(),'Search Ontario Land Property Records')]");
	By lroList = By.cssSelector("input.type-ahead.ng-untouched.ng-pristine.ng-valid");
	By propertyLink = By.cssSelector("a[routerlink='property']");
	By propertyBtn = By.xpath("//ul[@class='search-tile-list container-content']//li[1]//a[1]//search-tile[1]//div[1]//div[3]//div[1]");
	By searchCategories = By.cssSelector("section[aria-label='Search categories']");
	
	
	
	public HomePage() {
		this.driver = MyDriverClass.getDriver();	
		utils.BaseUtils.getWhenVisible(pageHeader, 20);
	}
	
	public void selectLRO(String lroNumber) {
		utils.BaseUtils.getWhenVisible(lroList, 20);
		WebElement searchLRO = driver.findElement(lroList);
		Actions action = new Actions(driver);
		action.moveToElement(searchLRO).sendKeys(searchLRO,lroNumber).build().perform();
		try {
		    Thread.sleep(500);
		} catch (InterruptedException e) {
		   e.printStackTrace();
		}
		action.moveToElement(searchLRO).sendKeys(searchLRO,Keys.ENTER).build().perform();
		log.info("LRO "+lroNumber+ " is selected");
	}
	
	public void clickPropertyBtn() {
		utils.BaseUtils.clickWhenReady(propertyBtn, 20);
		log.info("Property Search button is clicked");
	}
	
	public void clickPropertyLink() {
		utils.BaseUtils.clickWhenReady(propertyLink, 20);
		log.info("Property Search link is clicked");
	}
	
	public WebElement getSearchCategories() {
		return driver.findElement(searchCategories);
	}	
	
}
