package package1;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_16_Upload_File {
	WebDriver driver;
	// using in Window
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String seleniumFileName = "01.jpg";
	String seleniumFileName1 = "02.jpg";
	String seleniumFileName2 = "03.jpg";
	// String seleniumPath =
	// "C:\\Users\\Admin\\git\\selenium-webdriver-java-testng\\UploadFile\\" +
	// seleniumFileName;
	String seleniumPath = projectPath + getFileSeparator() + "UploadFile" + getFileSeparator() + seleniumFileName;
	String seleniumPath1 = projectPath + getFileSeparator() + "UploadFile" + getFileSeparator() + seleniumFileName1;
	String seleniumPath2 = projectPath + getFileSeparator() + "UploadFile" + getFileSeparator() + seleniumFileName2;

	@BeforeClass
	public void beforeTest() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\BrowserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectPath + "//BrowserDrivers//chromedriver.exe");
		}
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Upload_1_File() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		// link to file
		uploadFile.sendKeys(seleniumPath);
		// Verify file loaded sucess
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name'and text()='" + seleniumFileName + "']")).isDisplayed());
		// Click to Start
		driver.findElement(By.cssSelector(".files .start")).click();

		// Verify file upload success
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + seleniumFileName + "']")).isDisplayed());

	}

	@Test
	public void TC_02_Multi_File_Upload() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		// link to file
		uploadFile.sendKeys(seleniumPath + "\n" + seleniumPath1 + "\n" + seleniumPath2);
		// Verify file loaded sucess
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name'and text()='" + seleniumFileName + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name'and text()='" + seleniumFileName1 + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name'and text()='" + seleniumFileName2 + "']")).isDisplayed());
		// Click to Start
		List<WebElement> startButtons = driver.findElements(By.cssSelector(".files .start"));
		for (WebElement start : startButtons) {
			start.click();
			sleepInSecond(1);

		}
		// Verify file upload success
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + seleniumFileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + seleniumFileName1 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + seleniumFileName2 + "']")).isDisplayed());
	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Auto detect FilePath \\ or //
	public String getFileSeparator() {
		return File.separator;
	}

	@AfterClass
	public void afterTest() {
		driver.quit();
	}

}
