package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.BaseUtils;
import utils.MyDriverClass;


public class PropertyPINPage {
	private static Logger log = LogManager.getLogger(PropertyPINPage.class.getName());
	private WebDriver driver;
	
	By logo = By.className("logo");
	By pageHeader = By.xpath("//h1[contains(text(),'Property')]");
	By pinRadioBtn = By.className("mat-radio-inner-circle");
	By pinRangeRadioBtn = By.className("mat-radio-outer-circle");
	By blockNumb = By.id("block");
	By propertyNumb = By.id("pin");
	By propertyEndNumb = By.id("pinTo");
	By requestedBy = By.id("requested-by");
	By searchBtn = By.cssSelector("button.btn.btn-primary.requested-by");
	By searchResultsHeader = By.xpath("//h2[contains(text(),'Showing 1 to 1 of 1 search results for PIN')]");
	By searchResults = By.cssSelector("span.medium.no-wrap");
	By buyParcelRegBtn = By.xpath("//button[@class='btn btn-primary']");
	

	public PropertyPINPage() {
		this.driver = MyDriverClass.getDriver();
		utils.BaseUtils.getWhenVisible(pageHeader, 20);
	}
	
	public WebElement getPageHeader() {return driver.findElement(pageHeader);}
	public void selectPinRadioBtn() {driver.findElement(pinRadioBtn).click();}
	public void selectPinRangeRadioBtn() {driver.findElement(pinRangeRadioBtn).click();}
	public WebElement getBlockNumb() { return driver.findElement(blockNumb);}
	public void enterBlockNumb(String blockNumber) {
		BaseUtils.getWhenVisible(blockNumb, 20);
		driver.findElement(blockNumb).sendKeys(blockNumber);}
	public void enterPropertyNumb(String propertyNumber ) {driver.findElement(propertyNumb).sendKeys(propertyNumber);}
	public void enterPropertyEndNumb(String propertyEndNumber ) {driver.findElement(propertyEndNumb).sendKeys(propertyEndNumber);}
	public void enterRequestedBy(String requestBy ) {driver.findElement(requestedBy).sendKeys(requestBy);}
	public void clickSearch() {driver.findElement(searchBtn).click();}
	public WebElement getSearchResults() {
		BaseUtils.getWhenVisible(searchResults, 20);
		return driver.findElement(searchResults);}
	public void clickBuyParcelRegBtn() {driver.findElement(buyParcelRegBtn).click();}
	public WebElement getSearchResultsHeader() {
		BaseUtils.getWhenVisible(searchResultsHeader, 20);
		return driver.findElement(searchResultsHeader);}
}
