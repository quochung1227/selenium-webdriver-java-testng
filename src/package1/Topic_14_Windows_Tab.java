package package1;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic_14_Windows_Tab {
	WebDriver driver;

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", ".\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Only_Two_Window_Or_Tab() {
		driver.get("https://kyna.vn");
		// Before Click

		String parentID = driver.getWindowHandle();
		System.out.println("Id of page A =" + parentID);

		// Click to Facebook link at Footer
		driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='facebook']")).click();
		
		// After click
		
		Set<String> allIDs = driver.getWindowHandles();
		
		// Switch to Facebook page
		for (String id : allIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
			}
		}
		// Verify Url is corrected Facebook page
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/kyna.vn");

		//

	}

	@Test
	public void TC_02_Greater_Than_Two_Window_Or_Tab() {
		driver.get("https://kyna.vn");

	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterTest
	public void afterTest() {
	}

}
