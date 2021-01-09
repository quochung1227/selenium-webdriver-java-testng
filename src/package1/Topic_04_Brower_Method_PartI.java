package package1;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_04_Brower_Method_PartI {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
	}

	@Test
	public void TC_01() {
		// Mo trang web
		driver.get("http://live.demoguru99.com/");
		// Mo ra phan Mobile
		driver.findElement(By.xpath("//a[text()=\"Mobile\"]")).click();
		// Kiem tra Url moi co dung hay khong
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/mobile.html");
		// Lay ra Title
		driver.getTitle();
		// Lay ra source code cua page hien tai
		driver.getPageSource();
		// Lay ra ID cua tab/window no dang dung
		driver.getWindowHandle();
		// Lay ra tat ca ID cua tat ca cac tab
		driver.getWindowHandles();
		// Doi 30s
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// implicitlyWait : Cho cho 1 element duoc xuat hien de tuong tac trong vong bao
		// lau
		// find Element / find Elements
		// Cho cho 1 page duoc load xong trong vong ...s
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

		// Cho cho 1 doan script cua JS Excutor thuc thi
		((Timeouts) driver.manage()).setScriptTimeout(30, TimeUnit.SECONDS);
		// Phong to
		driver.manage().window().maximize();
		// Full screen F11
		driver.manage().window().fullscreen();
		// Kich thuoc cua trinh duyet

		// setSize
		// getSize

		// Vi tri cua trinh duyet
		// setPosition
		// getPosition

		// back kai trang truoc
		driver.navigate().back();
		// Di den trang truoc do
		driver.navigate().forward();
		// Refresh lai trang
		driver.navigate().refresh();
		// Mo 1 Url: tracking history tot hon
		driver.navigate().to("http://live.demoguru99.com/");
		// Thao tac voi alert
		driver.switchTo().alert();
		// Thao tac vs Frame/Iframe
		driver.switchTo().frame(0);
		// Thao tac voi Window
		driver.switchTo().window("");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
