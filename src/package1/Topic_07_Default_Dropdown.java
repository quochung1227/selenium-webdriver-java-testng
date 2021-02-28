package package1;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_07_Default_Dropdown {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/");

	}

	@Test
	public void TC_01_Register() {
		// 1-Mở trang Register
		driver.findElement(By.cssSelector(".ico-register")).click();
		
		
		/* Điền thông tin vào các field required */
		driver.findElement(By.cssSelector("#gender-male")).click();
		driver.findElement(By.id(""));
		
		/* 3-Đăng kí */

	}
	public void checkToCheckboxOrRadio(By by) {
		WebElement element =driver.findElement(by);
		if(!element.isSelected()) {
			element.click();
		}
	}
	public void uncheckToCheckbox(By by) {
		WebElement element =driver.findElement(by);
		if(element.isSelected()) {
			element.click();
		}
	}
	
	@AfterClass
	public void afterClass() {

	}
}