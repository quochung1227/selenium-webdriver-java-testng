package package1;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_11_UserInterface_PartI {
	WebDriver driver;
	Actions action;

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.gecko.driver", ".\\BrowserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	// @Test
	public void TC_01_Hover_Mouse() {
		driver.get("https://jqueryui.com/resources/demos/tooltip/default.html");
		action.moveToElement(driver.findElement(By.xpath("//a[text()='ThemeRoller']"))).perform();
		sleepInSecond(5);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(),
				"ThemeRoller: jQuery UI's theme builder application");
	}

	@Test
	public void TC_01B_Hover_Mouse() {
		driver.get("http://www.myntra.com/");
		action.moveToElement(driver.findElement(By.xpath("//div[@id='desktop-headerMount']//a[text()='Kids']")))
				.perform();
		sleepInSecond(5);
		action.click(driver.findElement(By.xpath("//div[@id='desktop-headerMount']//a[text()='Home & Bath']")))
				.perform();
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Kids Home Bath']")).isDisplayed());
	}

	// @Test
	public void TC_02_Hover_Mouse() {
		driver.get("https://hn.telio.vn/");
		action.moveToElement(driver.findElement(By.xpath("//span[text()='Đồ uống']"))).perform();
		sleepInSecond(5);
		Assert.assertTrue(driver.findElement(By.xpath("//main[@id='maincontent']//a[text()='Cà phê']")).isDisplayed());
		driver.findElement(By.xpath("//main[@id='maincontent']//a[@text()='Cà phê']")).click();
		sleepInSecond(2);
	}

	// @Test
	public void TC_03_Click_And_Hold() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		List<WebElement> numberSelect = driver.findElements(By.xpath("//ol[@id='selectable']/li"));

		Assert.assertEquals(numberSelect.size(), 12);

		action.clickAndHold(numberSelect.get(0)).moveToElement(numberSelect.get(3)).release().perform();
		sleepInSecond(3);

		List<WebElement> numberSelected = driver
				.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(numberSelected.size(), 4);

		for (WebElement number : numberSelected) {
			System.out.println(number.getText());
		}
	}

	// @Test
	public void TC_04_Click_And_Hold_Random() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		List<WebElement> numberSelect = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		Assert.assertEquals(numberSelect.size(), 12);
		// Nhan phim control xuong
		action.keyDown(Keys.CONTROL).perform();
		// chon cac so 2 7 9 10
		action.click(numberSelect.get(1)).click(numberSelect.get(6)).click(numberSelect.get(8))
				.click(numberSelect.get(9));
		// Nha phim control
		action.keyUp(Keys.CONTROL).perform();

		List<WebElement> numberSelected = driver
				.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(numberSelected.size(), 4);

		for (WebElement number : numberSelected) {
			System.out.println(number.getText());
		}
	}

	// Test
	public void TC_05_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		sleepInSecond(4);
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(), "Hello Automation Guys!");
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
		driver.quit();
	}

}
