package extentreport;
import static extentreport.BaseUtil.features;
import static extentreport.BaseUtil.scenarioDef;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gherkin.model.Feature;

public class FeaturesListener implements ITestListener{

	ExtentReportUtils ExtentReportUtils = new ExtentReportUtils();
		
	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("onTestStart");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("onTestSuccess");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		System.out.println("onTestFailure");
		//ExtentReportUtils.ExtentReportScreenshot();
		//ExtentReportUtils.scenarioDef.log(Status.FAIL, result.getThrowable());
		//System.out.println(result.getThrowable());
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("onTestSkipped");
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("onStart");
		ExtentReportUtils.ExtentReport();
		//features = ExtentReportUtils.extent.createTest(Feature.class,"Property Search");
		
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("onFinish");
		ExtentReportUtils.flushReport();
		System.out.println("Generated Report");
	}

}
