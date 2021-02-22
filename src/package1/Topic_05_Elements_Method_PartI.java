package package1;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_05_Elements_Method_PartI {
	WebDriver driver;
	@BeforeClass
	public void beforeClass() {
		driver= new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}

	@Test
	public void TC_01_WebElement_Command() {
		// 1- Thao tac truc tiep len Element + khong can khai bao
		driver.findElement(By.xpath("//input[@id='email']")).click();
		//2- Khai bao bien roi moi thao tac 
		
		
		//Nếu như muốn thao tác với 1 element nhiều lần
		//
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
