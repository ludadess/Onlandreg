package stepsreusable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import extentreport.Loggers;
import pages.PropertyPINPage;
import utils.BaseUtils;
import utils.MyDriverClass;


public class SRSearchProperty {
	private static Logger log = LogManager.getLogger(SRCommon.class.getName());
	
	public static void searchPropertyByPIN (String TestSetName) {
		PropertyPINPage page = new PropertyPINPage();
		BaseUtils.initilizeJSON ("testdata","PropertySearch", TestSetName);
		page.enterBlockNumb(BaseUtils.getJSONData("Block#"));
		page.enterPropertyNumb(BaseUtils.getJSONData("Property#"));
		page.enterRequestedBy(BaseUtils.getJSONData("RequestedBy"));
		page.clickSearch();
		log.info("The following search data was entered: ");
		log.info("Block#: "+BaseUtils.getJSONData("Block#"));
		log.info("Property#: "+BaseUtils.getJSONData("Property#"));
		log.info("RequestedBy: "+BaseUtils.getJSONData("RequestedBy"));	
	}
	
	public static void SearchResultsIsDisplayed (String TestSetName) {
		PropertyPINPage page = new PropertyPINPage();
		BaseUtils.initilizeJSON ("testdata","PropertySearch", TestSetName);
		// verify correct PIN is displayed in search results header
		String expPIN = BaseUtils.getJSONData("Block#")+"-"+BaseUtils.getJSONData("Property#");
		String resultsHeader = page.getSearchResultsHeader().getText();
				if((resultsHeader.contains(expPIN)) && (!resultsHeader.contains("No results"))) {
					log.info("Page 'Property Search by PIN results' is displayed ********* ");
					log.info(page.getSearchResultsHeader().getText());
				}
				else
					log.error("PIN is not displayed in the Search Results header");
					
		// verify correct PIN is displayed in search results table
		String actPIN = page.getSearchResults().getText();
		Assert.assertEquals(actPIN, expPIN);
		log.info("Search results: "+page.getSearchResults().getText()+" is displayed");
		
	}
}