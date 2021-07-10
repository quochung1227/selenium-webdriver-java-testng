package package1;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Topic_13_Iframe_frame {
	WebDriver driver;
	Select select;
	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.gecko.driver", ".\\BrowserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01() {
		driver.get("https://automationfc.com/2020/02/18/training-online-automation-testing/");
		// Switch to Facebook iframe
		// Index (ko nen dung)
		// driver.switchTo().frame(4);
		// ID or Name (ko nen dung)
		// driver.switchTo().frame("");

		// Web Element(Nen dung)
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='fb-page fb_iframe_widget']//iframe")));
		// Get like Number
		String likeNumber = driver.findElement(By.xpath("//a[text()='Automation FC']/parent::div/following-sibling::div")).getText();
		System.out.println(likeNumber);
		// Default content ( back lai parent)
		driver.switchTo().defaultContent();
		// Switch vao GG DOC
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'docs.google.com')]")));
		// Verify DOC header
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='KHÓA HỌC SELENIUM AUTOMATION TESTING']")).isDisplayed());
		
	}
	public void TC_02_Kyna() {
		driver.get("https://kyna.vn");
		// Web Element(Nen dung)
		driver.switchTo().frame("cs_chat_iframe");
		
		driver.findElement(By.cssSelector("div.favicon")).click();
		
		//Input vao cac field bat buoc
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("Hung nguyen");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0123456789");
		
		select= new Select(driver.findElement(By.id("serviceSelect")));
		select.selectByValue("HỖ TRỢ KỸ THUẬT");
		
		driver.findElement(By.cssSelector("textarea[name='message']")).sendKeys("Hello");
		driver.findElement(By.cssSelector("input.submit")).click();
	}
	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
