package utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MyDriverClass {
	
	public static WebDriver driver;
	public static final String USERNAME = "Valdess";
	public static final String ACCESS_KEY = "bcc2229e-853b-4290-a1b4-3392eba941df";
	public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";
	
	
	public static WebDriver getDriver() { return driver; }
	
	public static WebDriver Initialize() {
		String browserSession = BaseUtils.readProperty("Config","browser" );
		if (browserSession.contentEquals("chrome")) {
			//System.setProperty("webdriver.chrome.driver", "C:\\Work\\chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();			
		}
		else if(browserSession.contentEquals("firefox"))	{
			//System.setProperty("webdriver.gecko.driver", "C:\\Work\\geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if (browserSession.contentEquals("ie")) {
			//System.setProperty("webdriver.ie.driver", "C:\\Work\\IEDriverServer.exe");
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}
		else if (browserSession.contentEquals("cloud")){
		
			DesiredCapabilities caps = DesiredCapabilities.chrome();
			 caps.setCapability("platform", "Windows 10");
			 caps.setCapability("version", "latest");
			 try {
				driver = new RemoteWebDriver(new URL(URL), caps);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
		return driver;
	}
	
}
