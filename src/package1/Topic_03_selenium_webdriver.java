package package1;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_03_selenium_webdriver {
	WebDriver driver;
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("https://zingnews.vn/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void f() {
		// Verify title
		String homePageTitle = driver.getTitle();
		Assert.assertEquals(homePageTitle, "Zing -  tin uy tín, hình ảnh ấn tượng - ZINGNEWS.VN");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
