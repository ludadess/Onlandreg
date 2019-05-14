package stepsdefinition;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import extentreport.ExtentReportUtils;


public class Hooks extends ExtentReportUtils {
		
	@Before
	public void InitilizeTest(Scenario scenario) {
		//scenarioDef = ExtentReportUtils.features.createNode(scenario.getName());	
		//scenarioDef = ExtentReportUtils.extent.createTest(scenario.getName());
		
	}

	//@After
	//public void tearDown() {
		//driver= MyDriverClass.getDriver();
		//driver.close();
	//}
}
