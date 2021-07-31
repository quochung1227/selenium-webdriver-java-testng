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
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Only_Two_Window_Or_Tab() {
		driver.get("https://kyna.vn");
		// Before Click

		String parentID = driver.getWindowHandle();
		System.out.println("Id of page A =" + parentID);

		// Click to Facebook link at Footer
		driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='facebook']")).click();

		// After click

		// Set<String> allIDs = driver.getWindowHandles();

		/*
		 * // Switch to Facebook page for (String id : allIDs) { if
		 * (!id.equals(parentID)) { driver.switchTo().window(id); } }
		 */

		// Switch to Facebook page
		switchToWindownByID(parentID);

		// Verify Url is corrected Facebook page
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/kyna.vn");

		// Assert.assertTrue(driver.getCurrentUrl().contains("facebook.com"));

	}

	@Test
	public void TC_02_Greater_Than_Two_Window_Or_Tab() {
		driver.get("https://kyna.vn");
		String kynaID= driver.getWindowHandle();
		// Click to facebook
		driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='facebook']")).click();

		// Switch to Facebook
		switchToWindowByTitle("Đăng nhập Facebook");
		Assert.assertTrue(driver.getCurrentUrl().contains("facebook.com"));

		// Switch to Kyna
		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		Assert.assertTrue(driver.getCurrentUrl().contains("kyna.vn"));

		// Click to Youtube
		driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='youtube']")).click();

		// Switch to Youtube
		switchToWindowByTitle("Kyna.vn - Youtube");
		Assert.assertTrue(driver.getCurrentUrl().contains("youtube.com"));

		// Switch to Kyna
		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		Assert.assertTrue(driver.getCurrentUrl().contains("kyna.vn"));

		// Click to Primus
		driver.findElement(By.xpath("//div[@class='info']//a[text()='PRIMUS']")).click();

		// Switch to Kyna
		switchToWindowByTitle("PRIMUS Homepage");
		Assert.assertTrue(driver.getCurrentUrl().contains("primus.vn"));
		//Close all tab without Kyna
		closeAllWindowWithoutParent(kynaID);
	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void switchToWindownByID(String windowID) {
		// Get all ID of window/tab
		Set<String> allIDs = driver.getWindowHandles();
		// execute all ID
		for (String id : allIDs) {
			// Each Id check conditions
			if (!id.equals(windowID)) {
				// Switch to ID
				driver.switchTo().window(id);
				sleepInSecond(2);
				// Exit loop for
				break;
			}
		}

	}

	public void switchToWindowByTitle(String expectedwindowTitle) {
		Set<String> allIDs = driver.getWindowHandles();

		for (String id : allIDs) {
			driver.switchTo().window(id);
			String actualWindowTitle = driver.getTitle();
			if (actualWindowTitle.equals(expectedwindowTitle)) {
				break;
			}
		}

	}
	public void closeAllWindowWithoutParent(String windowID) {
		Set<String> allIDs = driver.getWindowHandles();
		for (String id : allIDs) {
			if(!id.equals(windowID)) {
				driver.switchTo().window(id);
				driver.close();
				
			}
		}
	}
	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
