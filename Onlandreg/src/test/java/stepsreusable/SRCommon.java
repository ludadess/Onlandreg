package stepsreusable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import extentreport.Loggers;
import pages.HomePage;
import utils.MyDriverClass;


public class SRCommon {
	private static Logger log = LogManager.getLogger(SRCommon.class.getName());
	private static WebDriver driver= MyDriverClass.getDriver();
	
	static public void pageIsDisplayed(String pageName) {
		utils.BaseUtils.getWhenVisible(By.id("requested-by"), 20);
		Assert.assertEquals(driver.getTitle(),pageName);
		log.info("Page '"+pageName+"' is disdplayed********************");
		//Loggers.info("Page '"+pageName+"' is disdplayed********************");
	}

}