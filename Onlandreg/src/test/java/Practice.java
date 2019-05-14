import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import stepsreusable.SRCommon;
import utils.BaseUtils;
import utils.MyDriverClass;

public class Practice {
	private static Logger log = LogManager.getLogger(Practice.class.getName());
	
	@Test
	public static void main(String[] args) {
		WebDriver driver = utils.MyDriverClass.Initialize();
	    driver.get(BaseUtils.readProperty("config","url"));
		SRCommon.pageIsDisplayed("Ontario Land Registry Access");
		driver.close();
		

	}

}
