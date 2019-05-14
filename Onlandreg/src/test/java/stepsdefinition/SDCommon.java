package stepsdefinition;

import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Scenario;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import extentreport.ExtentReportUtils;
import pages.HomePage;
import stepsreusable.SRCommon;
import stepsreusable.SRSearchProperty;
import utils.BaseUtils;
import utils.MyDriverClass;


@RunWith(Cucumber.class)
public class SDCommon extends ExtentReportUtils{
//private static Logger log = LogManager.getLogger(SDCommon.class.getName());
	static WebDriver driver = MyDriverClass.getDriver();
	static ExtentTest test;
	static ExtentTest logInfo;


@Given("^The user is on the Home page$")
public void the_user_is_on_the_Home_page() throws Throwable {
	ExtentTest logInfo = null;
	try {
		test = extent.createTest(Feature.class, "Property Search");
		test= test.createNode(Scenario.class, "One");
		logInfo = test.createNode(new GherkinKeyword("Given"), "The user is on the Home page");
		
		WebDriver driver = utils.MyDriverClass.Initialize();
	    driver.get(BaseUtils.readProperty("config","url"));
	    logInfo.pass("Open browser and entered url");
	} catch (AssertionError|Exception e) {
		testStepHandle("FAIL", driver, logInfo, e);
	}  
    //logInfo.info("Page '"+driver.getTitle()+"' is disdplayed ****************");
    //Loggers.info("Page '"+driver.getTitle()+"' is disdplayed ****************");
   
}

@When("^The user selects LRO \"([^\"]*)\"$")
public void the_user_selects_LRO(String lroNumber) throws Throwable {
	ExtentTest logInfo = null;
	try {
		logInfo = test.createNode(new GherkinKeyword("When"), "The user selects LRO ");
		HomePage page = new HomePage();
	    page.selectLRO(lroNumber);
	    logInfo.pass("The user selected LRO");
	} catch (AssertionError|Exception e) {
		testStepHandle("FAIL", driver, logInfo, e);
	}  
}

@When("^The user clicks on the Property Search button$")
public void the_user_clicks_on_the_Property_Search_button() throws Throwable {
    ExtentTest logInfo = null;
	try {
		logInfo = test.createNode(new GherkinKeyword("When"), "The user clicks on the Property Search button");
		HomePage page = new HomePage();
	    page.clickPropertyBtn();
	    logInfo.pass("The user clicked on Property search button");
	} catch (AssertionError|Exception e) {
		testStepHandle("FAIL", driver, logInfo, e);
	}     
}

@Then("^\"([^\"]*)\" page is dispalyed$")
public void page_is_dispalyed(String pageName) throws Throwable {
	ExtentTest logInfo = null;
	try {
		logInfo = test.createNode(new GherkinKeyword("Then"), "Desired Page is displayed");
		SRCommon.pageIsDisplayed(pageName);
	   
	    logInfo.pass("Page is displayed");
	} catch (AssertionError|Exception e) {
		testStepHandle("FAIL", driver, logInfo, e);
	}     
}

@Given("^The user is on the \"([^\"]*)\" page$")
public void the_user_is_on_the_page(String pageName) throws Throwable {
	driver = MyDriverClass.getDriver();
	ExtentTest logInfo = null;
	 try {
		 test= test.createNode(Scenario.class, "Two");
		 logInfo = test.createNode(new GherkinKeyword("Given"), "Desired Page is displayed");
		SRCommon.pageIsDisplayed(pageName);
		   
		    logInfo.pass("Page is displayed");
		} catch (AssertionError|Exception e) {
			testStepHandle("FAIL", driver, logInfo, e);
		}    
}
@When("^The user enters \"([^\"]*)\" to search property by PIN$")
public void the_user_enters_to_search_property_by_PIN(String TestSetName) throws Throwable {
	ExtentTest logInfo = null;
	try {
		logInfo = test.createNode(new GherkinKeyword("When"), "The user enters data to search property by PIN");
		SRSearchProperty.searchPropertyByPIN(TestSetName);
	    logInfo.pass("The user enters data to search property by PIN");
	} catch (AssertionError|Exception e) {
		testStepHandle("FAIL", driver, logInfo, e);
	}     
}

@Then("^Results for search property by \"([^\"]*)\" are shown$")
public void results_for_search_property_by_are_shown(String TestSetName) throws Throwable {
	ExtentTest logInfo = null;
	try {
		logInfo = test.createNode(new GherkinKeyword("Then"), "Results for search property by PIN are shown");
		SRSearchProperty.SearchResultsIsDisplayed(TestSetName);
	    logInfo.pass("Results for search property by PIN are shown");
	} catch (AssertionError|Exception e) {
		testStepHandle("FAIL", driver, logInfo, e);
	}   
}		
}	
	
	
