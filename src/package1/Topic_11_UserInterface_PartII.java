package package1;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_11_UserInterface_PartII {
	WebDriver driver;
	Actions action;
	Color color;
	String projectPath = System.getProperty("user.dir");
	String javascriptPath = projectPath + "\\DragandDrop\\drag_and_drop_helper.js";
	String jqueryPath = projectPath + "\\DragandDrop\\jquery_load_helper.js";
	JavascriptExecutor javascriptExecutor;

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.gecko.driver", ".\\BrowserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		action = new Actions(driver);
		javascriptExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		sleepInSecond(5);
		// Verify Quit displayed
		Assert.assertTrue(
				driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]")).isDisplayed());
		// Hover to Quit
		action.moveToElement(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]"))).perform();
		sleepInSecond(3);
		// Verify Quit (visible +Hover)
		Assert.assertTrue(driver.findElement(By.xpath(
				"//li[contains(@class,'context-menu-icon-quit') and contains(@class,'context-menu-visible') and contains(@class,'context-menu-hover')]"))
				.isDisplayed());
		// Click to quit
		action.click(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]"))).perform();
		sleepInSecond(3);
		// Confirm alert
		Assert.assertEquals(driver.switchTo().alert().getText(), "clicked: quit");
		driver.switchTo().alert().accept();
		// Verify Quit is not displayed
		Assert.assertFalse(
				driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]")).isDisplayed());
	}

	// @Test
	public void TC_02_Drag_Drop_HTML4() {
		driver.get("https://automationfc.github.io/kendo-drag-drop");
		WebElement sourceCircle = driver.findElement(By.id("draggable"));
		WebElement targetCircle = driver.findElement(By.id("droptarget"));

		action.dragAndDrop(sourceCircle, targetCircle).perform();
		sleepInSecond(3);
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@id='droptarget'and text()= 'You did great!']")).isDisplayed());
		Assert.assertEquals(getHexaValue(driver.findElement(By.id("droptarget")).getCssValue("background-color")),
				"#03a9f4");

	}

	// @Test
	public void TC_03_Drag_Drop_HTML5_Css() throws IOException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		String sourceCss = "#column-a";
		String targetCss = "#column-b";

		String java_script = getFileContent(javascriptPath);
		// Inject Jquery lib to site
		// String jqueryscript=getFileContent(jqueryPath);
		// javascriptExecutor.excuteScript(jqueryscript);

		// A to B
		java_script = java_script + "$(\"" + sourceCss + "\").simulateDragDrop({ dropTarget: \"" + targetCss + "\"});";
		javascriptExecutor.executeScript(java_script);
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());

		// B to A
		javascriptExecutor.executeScript(java_script);
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='B']")).isDisplayed());
	}

	@Test
	public void TC_04_Drag_Drop_HTML5_Xpath() throws IOException, AWTException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");

		// A to B
		drag_the_and_drop_html5_by_xpath("//div[@id='column-a']", "//div[@id='column-b']");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());
		// B to A
		drag_the_and_drop_html5_by_xpath("//div[@id='column-a']", "//div[@id='column-b']");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='B']")).isDisplayed());
	}

	public void drag_the_and_drop_html5_by_xpath(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();
	

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 110 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 110 + yCentreTarget;

		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x,
				((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}

	public String getRGBValue(String hexaValue) {

		return Color.fromString(hexaValue).asRgb();
	}

	public String getHexaValue(String rgbValue) {

		return Color.fromString(rgbValue).asHex();

	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getFileContent(String file) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(file);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
