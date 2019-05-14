package extentreport;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import utils.MyDriverClass;

public class ExtentReportUtils extends MyDriverClass{
	static WebDriver driver = MyDriverClass.getDriver();
	public static ExtentReports extent;
	public static String reportLocation = System.getProperty("user.dir") + "/ExtentReports/";
	public static String screenLocation = System.getProperty("user.dir") + "/FailedTestsScreenshoots/";
	public static String timeStamp = new SimpleDateFormat("MM_dd-HH_mm_ss").format(Calendar.getInstance().getTime());
	static ExtentTest extentTest;
	
	
	public static ExtentReports ExtentReport() {
		String fileName = reportLocation+timeStamp+"_report.html";
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setDocumentTitle("Test report for Selenium test");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("Test Report");
		htmlReporter.start();
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		return extent;
		}
	
	public static void testStepHandle(String testStatus, WebDriver driver, ExtentTest extentTest, Throwable throwable) {
		switch (testStatus) {
		case "FAIL":
			extentTest.fail(MarkupHelper.createLabel("Test case is Failed", ExtentColor.RED));
			extentTest.error(throwable.fillInStackTrace());
			try {
				extentTest.addScreenCaptureFromPath(ExtentReportScreenshot());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (driver !=null) {
				driver.quit();
			}
			break;
		case "PASS":
			extentTest.pass(MarkupHelper.createLabel("Test case is Passed", ExtentColor.GREEN));
			break;
		default :
			break;
		}
		}
	public static String ExtentReportScreenshot() {
		driver = MyDriverClass.getDriver();      			
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String pathImage = screenLocation+timeStamp+"_screenshot.png";
		try {
			FileUtils.copyFile(src, new File(pathImage));
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return pathImage;
		
		}
	
	public void flushReport() {
		extent.flush();
		
	}
}
