package extentreport;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;


import utils.MyDriverClass;

public class BaseUtil {
	public static WebDriver driver = MyDriverClass.getDriver();
	public static ExtentReports extent;
	public static ExtentTest scenarioDef;
	public static ExtentTest features;
	
}
