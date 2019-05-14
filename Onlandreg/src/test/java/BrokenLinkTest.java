import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class BrokenLinkTest {


@Test
public void testBrokenLink() {
	
	WebDriver driver = utils.MyDriverClass.Initialize();
	driver.get("https://freecrm.com/");
	
	List<WebElement> links = driver.findElements(By.tagName("a"));
	List<WebElement> images = driver.findElements(By.tagName("img"));
	
	System.out.println("number of links ---->"+links.size() );
	System.out.println("number of images ---->"+images.size() );
	
	 links.addAll(driver.findElements(By.tagName("img")));
	 System.out.println("number of links and images ---->"+links.size() );
	
}

}
