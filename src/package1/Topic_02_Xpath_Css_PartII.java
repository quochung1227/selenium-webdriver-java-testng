package package1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath_Css_PartII {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Login_empty_Email_And_Passwords() throws InterruptedException {
		driver.get("http://live.demoguru99.com/index.php");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()=''My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.name("login[password]")).sendKeys("");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(),
				"This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(),
				"This is a required field.");
		Thread.sleep(4000);

	}

	@Test
	public void TC_02_Login_Invalid_Email() throws InterruptedException {
		driver.findElement(By.xpath(".//*[@id='top']/body/div/div/div[3]/div/div[4]/ul/li[1]/a")).click();
		driver.findElement(By.id("email")).sendKeys("123434234@12312.123123");
		driver.findElement(By.id("pass")).sendKeys("123456");
		driver.findElement(By.id("send2")).click();
		driver.findElement(By.id("advice-validate-email-email")).isDisplayed();
		Thread.sleep(4000);

	}

	@Test
	public void TC_03_Login_Invalid_Password() throws InterruptedException {
		driver.findElement(By.xpath(".//*[@id='top']/body/div/div/div[3]/div/div[4]/ul/li[1]/a")).click();
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123");
		driver.findElement(By.id("send2")).click();
		driver.findElement(By.id("advice-validate-password-pass")).isDisplayed();
		Thread.sleep(5000);

	}

	@Test
	public void TC_04_Login_Incorrect_Password() throws InterruptedException {
		driver.findElement(By.xpath(".//*[@id='top']/body/div/div/div[3]/div/div[4]/ul/li[1]/a")).click();
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123123123");
		driver.findElement(By.id("send2")).click();
		driver.findElement(By.xpath(".//*[@id='top']/body/div/div/div[2]/div/div/div/ul/li/ul/li/span")).isDisplayed();
		Thread.sleep(5000);
	}

	@Test
	public void TC_05() {
		driver.findElement(By.xpath(".//*[@id='top']/body/div/div/div[3]/div/div[4]/ul/li[1]/a")).click();
		driver.findElement(By.xpath("//a[@title=\"Create an Account\"]")).click();
		driver.findElement(By.id("firstname")).sendKeys("Hung");
		driver.findElement(By.id("middlename")).sendKeys("Quoc");
		driver.findElement(By.id("lastname")).sendKeys("Nguyen");
		driver.findElement(By.id("email_address")).sendKeys("nguyenquochung2712@gmail.com");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.id("confirmation")).sendKeys("123456");
	}
}
