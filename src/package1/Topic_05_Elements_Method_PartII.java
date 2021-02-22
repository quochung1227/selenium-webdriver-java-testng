package package1;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_05_Elements_Method_PartII {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		boolean emailTextboxStatus = driver.findElement(By.cssSelector("#mail")).isDisplayed();
		if (emailTextboxStatus == true) {
			driver.findElement(By.cssSelector("#email")).sendKeys("Automation test");
			Thread.sleep(5000);
			System.out.println("Email textbox is displayed");
		} else {
			System.out.println("Email textbox is not displayed");
		}
		boolean educationTextAreaStatus = driver.findElement(By.cssSelector("#edu")).isDisplayed();
		if (educationTextAreaStatus == true) {
			driver.findElement(By.cssSelector("#edu")).sendKeys("Automation testing");
			System.out.println("Education textaera is displayed");
		} else {
			System.out.println("Education textaera is not Displayed");
		}
		boolean ageUnderEighteenRadioStatus = driver.findElement(By.cssSelector("#under_18")).isDisplayed();
		if (ageUnderEighteenRadioStatus == true) {
			driver.findElement(By.cssSelector("#under_18")).click();
			System.out.println("Age Under 18 is displayed");
		} else {
			System.out.println("Age Under 18 is not Displayed");
		}
		boolean nameUserFiveTextStatus = driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed();
		if (nameUserFiveTextStatus == true) {
			System.out.println("Name under 5 is Displayed");
		} else {
			System.out.println(" Name under 5  is not Displayed");
		}
	}

	@Test
	public void TC_02() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

}
