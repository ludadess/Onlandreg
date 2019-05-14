package stepsdefinition;

import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import extentreport.ExtentReportUtils;
import stepsreusable.SRSearchProperty;
import utils.MyDriverClass;


@RunWith(Cucumber.class)
public class SDSearchProperty extends ExtentReportUtils {
	static WebDriver driver = MyDriverClass.getDriver();
	static ExtentTest test;
	static ExtentTest logInfo;
	
	

}
