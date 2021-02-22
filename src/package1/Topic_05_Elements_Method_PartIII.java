package package1;

import org.testng.annotations.Test;

import org.testng.Assert;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_05_Elements_Method_PartIII {

	WebDriver driver;
	By emailTextbox = By.cssSelector("#mail");
	By educationTextAreaStatus = By.cssSelector("#edu");
	By ageUnder18Radio = By.cssSelector("#under_18");
	By nameText = By.xpath("//h5[text()='Name: User5']");
	By javalanguageCheckbox = By.cssSelector("#java");
	By slider1 = By.cssSelector("#slider-1");
	By slider2 = By.cssSelector("#slider-2");
	
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
 
	}
	@Test
	public void TC_01() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		if (isElementDisplayed(emailTextbox)) {
			sendkeyToElement(emailTextbox, "Automation Testing");
		}
		if (isElementDisplayed(educationTextAreaStatus)) {
			sendkeyToElement(educationTextAreaStatus, "Automation Testing");
		}
		if (isElementDisplayed(ageUnder18Radio)) {
			clickToElement(ageUnder18Radio);
		}
		if (isElementDisplayed(javalanguageCheckbox)) {
			clickToElement(javalanguageCheckbox);
		}
		
		Assert.assertFalse(isElementDisplayed(nameText));
		Assert.assertTrue(isElementDisplayed(slider1));
	}

	public boolean isElementDisplayed(By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			System.out.println(by + " is displayed");
			return true;
		} else {
			System.out.println(by + " is not displayed");
			return false;
		}
	}

	@Test
	public void TC_02_Enable() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		Assert.assertTrue(isElementDisplayed(emailTextbox));
		Assert.assertTrue(isElementDisplayed(slider1));
		Assert.assertTrue(isElementDisplayed(slider2));
	}
	@Test
	public void TC_03_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		clickToElement(javalanguageCheckbox);
		clickToElement(ageUnder18Radio);
		Assert.assertTrue(isElementSelected(javalanguageCheckbox));
		Assert.assertTrue(isElementSelected(ageUnder18Radio));
	}
	
	@AfterClass
	public void afterClass() {

	}
	public boolean isElementSelected(By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			System.out.println(by + " is Selected");
			return true;
		} else {
			System.out.println(by + " is not Selected");
			return false;
		}
	}

	public void sendkeyToElement(By by, String value) {
		WebElement element = driver.findElement(by);
		element.clear();
		element.sendKeys(value);
	}

	public void clickToElement(By by) {
		WebElement element = driver.findElement(by);
		element.click();
	}

}
