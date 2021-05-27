package package1;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_09_Button_Radio_Checkbox {
	WebDriver driver;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		// Ép kiểu tường minh
		// Từ Interface này sang interface khác
		jsExecutor = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		By loginButton = By.cssSelector("fhs-btn-login");
		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
		// Verify login button is disabled
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());
		// Input to Email/Passwords
		driver.findElement(By.xpath("//input[@id=\"login_username\"]")).sendKeys("nguyenquochung2712@gmail.com");
		driver.findElement(By.xpath("//input[@id=\"login_password\"]")).sendKeys("passwords");
		sleepInSecond(2);
		// Verify login button is enabled
		Assert.assertTrue(driver.findElement(loginButton).isEnabled());
		driver.navigate().refresh();
		driver.findElement(By.xpath("//span[text()='Đăng nhập'")).click();
		
		removeDisableAttributeByJS(loginButton);
		sleepInSecond(2);
		driver.findElement(loginButton).click();
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']")).getText(),"Thông tin này không thể để trống");
	}

	public void removeDisableAttributeByJS(By by) {
		WebElement element = driver.findElement(by);
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", element);
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
	}

}
