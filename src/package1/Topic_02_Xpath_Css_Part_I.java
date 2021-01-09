package package1;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_02_Xpath_Css_Part_I {
WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}

	@Test
	public void TC_01_ID() throws InterruptedException {
		driver.findElement(By.id("email")).sendKeys("automationtest@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123456");
		Thread.sleep(4000);
	}
	@Test
	public void TC_02_Class() throws InterruptedException {
		driver.navigate().refresh();
		driver.findElement(By.className("validate-password")).sendKeys("12345");
		Thread.sleep(4000);
	}

	@AfterClass
	public void afterClass() {
	}

}
