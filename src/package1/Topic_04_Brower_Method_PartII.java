package package1;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_04_Brower_Method_PartII {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01() {
		driver.get("http://google.com");
	}
	@Test
	public void TC_02() {
		
	}
	@Test
	public void TC_03() {
		
	}
	@Test
	public void TC_04() {
		
	}

	@AfterClass
	public void afterClass() {
	}

}
