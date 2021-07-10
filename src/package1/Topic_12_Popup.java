package package1;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_12_Popup {
	WebDriver driver;
	Actions action;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", ".\\BrowserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_ValidateCurrentUrl() {
		driver.get("https://tiki.vn");
		// Hover mouse
		action.moveToElement(driver.findElement(By.xpath("//img[@class='profile-icon']"))).perform();
		// Click to Dang nhap
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();

		// Verify popup is displayed
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@class='styles__Root-sc-2hr4xa-0 cyNgyk']")).isDisplayed());

		// Click to Close icon

		driver.findElement(By.xpath("//div//button[@class='btn-close']")).click();

		// Verifiy popup is not Displayed
		Assert.assertFalse(
				driver.findElement(By.xpath("//div[@class='styles__Root-sc-2hr4xa-0 cyNgyk']")).isDisplayed());
	}

	// @Test
	public void TC_02_Popup_In_DOM() {
		driver.get("https://bni.vn");
		// Verify popup is displayed
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='sgpb-popup-dialog-main-div']")).isDisplayed());
		sleepInSecond(3);
		// Close popup
		driver.findElement(By.xpath("//img[@class='sgpb-popup-close-button-1]")).click();

		// Verify popup is not displayed
		Assert.assertFalse(driver.findElement(By.xpath("//div[@id='sgpb-popup-dialog-main-div']")).isDisplayed());

		// Step1
		WebElement popup = driver.findElement(By.xpath("//div[@id='sgpb-popup-dialog-main-div']"));

		// Step2
		boolean status = popup.isDisplayed();
		System.out.println("Status=" + status);

		// Step3
		Assert.assertFalse(status);
	}

	@Test
	public void TC_02_Popup_In_DOM_Condition() {
		driver.get("https://blog.testproject.io/");

		if (driver.findElement(By.xpath("//div[@class='mailch-wrap']")).isDisplayed()) {

			driver.findElement(By.xpath("//div[@id='close-mailch']")).click();

			sleepInSecond(10);
		}
		driver.findElement(By.xpath("//section[@id='search-2']//input[@class='search-field']")).sendKeys("Robot Framework");
		
		sleepInSecond(3);

		driver.findElement(By.xpath("//section[@id='search-2']//span[@class='glass']")).click();
	}

	// @Test
	public void TC_03_Popup_Not_In_DOM() {
		driver.get("https://tiki.vn");
		// Hover mouse
		action.moveToElement(driver.findElement(By.xpath("//img[@class='profile-icon']"))).perform();
		// Click to Dang nhap
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();

		// Verify popup is displayed
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@class='styles__Root-sc-2hr4xa-0 cyNgyk']")).isDisplayed());

		// Click to Close icon

		driver.findElement(By.xpath("//div//button[@class='btn-close']")).click();

		// Verifiy popup is not Displayed
		Assert.assertEquals(driver.findElements(By.xpath("//div[@class='styles__Root-sc-2hr4xa-0 cyNgyk']")).size(), 0);
	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
