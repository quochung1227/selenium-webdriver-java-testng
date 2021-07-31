package package1;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class Topic_17_Wait_I_Element_Status {
	WebDriver driver;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 15);
		driver.manage().window().maximize();

	}

	//@Test
	public void TC_01_Visible() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		// Cho submit button dc visible trong 15s
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='SubmitLogin']")));

	}

	//@Test
	public void TC_02_Invisible() {
//		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
//		// Wait error message invisible 15s
//		explicitWait.until(
//				ExpectedConditions.invisibilityOfElementLocated(By.xpath("//li[text()='An email address required.']")));
//		//Wait forgot password invisible in 15s
//		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//form[@id='form_forgotpassword']")));
//		
		driver.get("http://live.demoguru99.com/index/php");
		// Co trong Dom
		explicitWait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//div[@id='header-account']//a[@title='My Account']")));

		// Khong co trong DOM
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@id='email']")));
	}

	//@Test
	public void TC_03_Precence() {
		driver.get("http://live.demoguru99.com/index/php");
		// Co trong DOM + khong co tren UI(che mat)
		explicitWait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//div[@id='header-account']//a[@title='My Account']")));
		// Co trong DOM + co tren UI
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='search']")));

	}
	@Test
	public void TC_04_Staless() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		driver.findElement(By.xpath("//button[@id='SubmitLogin']")).click();
		WebElement errorMessage = driver.findElement(By.xpath("//li[text()='An email address required.']"));
		driver.navigate().refresh();
		sleepInSecond(10);
		explicitWait.until(ExpectedConditions.stalenessOf(errorMessage));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
