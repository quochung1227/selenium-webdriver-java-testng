package package1;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Topic_08_Custom_Dropdown_PartI {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	String month = "February";
	String[] firstMonths = { "March", "May", "September", "November" };
	String[] secondMonths = { "March", "May", "September" };

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Admin\\git\\selenium-webdriver-java-testng\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		// driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test(enabled = true)
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "13");
		Assert.assertEquals(driver
				.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text']")).getText(),
				"13");

		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "14");
		Assert.assertEquals(
				driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(),
				"14");

		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//div", "15");
		Assert.assertEquals(
				driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text']")).getText(),
				"15");

	}

//	- Hành vi của 1 dropdown
//	+ Click vào dropdown
//	+Chờ cho các item hiển thị
//	+Tìm item cần chọn -> Lấy ra text mình mong muốn -> So sánh với cái mong đợi -> Bằng nhau?
//		Item có nằm trong vùng nhìn thấy hay không-> Click luôn
//		Item không nằm trong viewport -> Scroll xuống -> Click
//	+ Bấm vào -> Kiểm tra xem đúng item được chọn không 	
//	
	@Test
	public void TC_02_NopComerce() {
		driver.get("https://demo.nopcommerce.com/register");
		selectItemInCustomDropdown("//select[@name='DateOfBirthDay']", "//select[@name='DateOfBirthDay']/option", "27");

		selectItemInCustomDropdown("//select[@name='DateOfBirthMonth']", "//select[@name='DateOfBirthMonth']/option",
				"August");

		selectItemInCustomDropdown("//select[@name='DateOfBirthYear']", "//select[@name='DateOfBirthYear']/option",
				"1995");

		sleepInSecond(5);
	}

	@Test
	public void TC_03_Angular() {
		driver.get(
				"https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
		selectItemInCustomDropdown("//ejs-dropdownlist[@id=\"games\"]", "//ul[@id='games_options']/li", "Basketball");
		sleepInSecond(3);
		Assert.assertEquals(getAngularDropdownSelectedText(), "Basketball");

		selectItemInCustomDropdown("//ejs-dropdownlist[@id=\"games\"]", "//ul[@id='games_options']/li", "Snooker");
		sleepInSecond(3);
		Assert.assertEquals(getAngularDropdownSelectedText(), "Snooker");
		selectItemInCustomDropdown("//ejs-dropdownlist[@id=\"games\"]", "//ul[@id='games_options']/li",
				"American Football");
		sleepInSecond(3);
		Assert.assertEquals(getAngularDropdownSelectedText(), "American Football");
	}

	@Test
	public void TC_04_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectItemInCustomDropdown("//div[@role='listbox']", "//div[@role='option']/span", "Christian");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Christian");

		selectItemInCustomDropdown("//div[@role='listbox']", "//div[@role='option']/span", "Matt");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Matt");

		selectItemInCustomDropdown("//div[@role='listbox']", "//div[@role='option']/span", "Justen Kitsune");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Justen Kitsune");
	}

	@Test
	public void TC_05_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectItemInCustomDropdown("//li[@class=\"dropdown-toggle\"]", "//ul[@class='dropdown-menu']//a",
				"Second Option");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Second Option");

		selectItemInCustomDropdown("//li[@class=\"dropdown-toggle\"]", "//ul[@class='dropdown-menu']//a",
				"First Option");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "First Option");
	}

	@Test
	public void TC_06_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		selectItemInEditableDropdown("//input[@class='search']", "//div[@role='option']/span", "Albania");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Albania");
	}

	@Test
	public void TC_07_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		//selectItemInEditableDropdown("//input[@class='search']", "//div[@role='option']/span", "Albania");
		// Sử dụng Tab key
		selectItemEditableDropdown("//input[@class='search']", "Albania");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Albania");
	}

	@Test
	public void TC_08_Multiple_Select() {
		driver.get("http://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");
		selectMultipleItemInCustomDropdown("//button[@class='ms-choice']",
				"(//div[@class='ms-drop bottom'])[1]//li//span", firstMonths);
		Assert.assertTrue(areItemSelected(firstMonths));

		driver.navigate().refresh();

		selectMultipleItemInCustomDropdown("//button[@class='ms-choice']",
				"(//div[@class='ms-drop bottom'])[1]//li//span", secondMonths);
		Assert.assertTrue(areItemSelected(secondMonths));
	}

	public void selectItemInCustomDropdown(String parentXpath, String allItemxpath, String expectedText) {
		// Click vào dropdown
		driver.findElement(By.xpath(parentXpath)).click();
		// Chờ cho các item được hiển thị ra trước khi chọn
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemxpath)));
		// Lấy hết tất cả các item con đưa vào 1 list để duyệt
		List<WebElement> allItem = driver.findElements(By.xpath(allItemxpath));

		// Dùng vòng lặp để duyệt qua từng item
		for (WebElement item : allItem) {
			// Duyệt qua các phần tử và getText
			// Nếu như text get ra bằng vs text mong muốn thì click và dừng lại
			// Thoát khỏi vòng lặp
			if (item.getText().equals(expectedText)) {
				item.click();
				break;
			}
		}
	}

	public void selectItemInEditableDropdown(String parentXpath, String allItemxpath, String expectedText) {
		// Xóa dữ liệu cũ
		driver.findElement(By.xpath(parentXpath)).clear();
		// Click vào dropdown
		driver.findElement(By.xpath(parentXpath)).sendKeys(expectedText);
		// Chờ cho các item được hiển thị ra trước khi chọn
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemxpath)));
		// Lấy hết tất cả các item con đưa vào 1 list để duyệt
		List<WebElement> allItem = driver.findElements(By.xpath(allItemxpath));

		// Dùng vòng lặp để duyệt qua từng item
		for (WebElement item : allItem) {
			// Duyệt qua các phần tử và getText
			// Nếu như text get ra bằng vs text mong muốn thì click và dừng lại
			// Thoát khỏi vòng lặp
			if (item.getText().equals(expectedText)) {
				item.click();
				sleepInSecond(1);
				break;
			}
		}
	}

	public void selectItemEditableDropdown(String parentXpath, String expectedText) {
		// Xóa dữ liệu cũ
		driver.findElement(By.xpath(parentXpath)).clear();
		// Click vào dropdown
		driver.findElement(By.xpath(parentXpath)).sendKeys(expectedText);
		sleepInSecond(2);
		// Send TAB key
		driver.findElement(By.xpath(parentXpath)).sendKeys(Keys.TAB);
	}

	public void selectMultipleItemInCustomDropdown(String parentXpath, String allItemxpath, String[] months) {

		// Click vào dropdown
		driver.findElement(By.xpath(parentXpath)).click();
		// Lấy hết tất cả các item con đưa vào 1 list để duyệt
		List<WebElement> allItem = explicitWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemxpath)));
		for (String month : months) {
			// Dùng vòng lặp để duyệt qua từng item
			for (WebElement item : allItem) {
				// Duyệt qua các phần tử và getText
				// Nếu như text get ra bằng vs text mong muốn thì click và dừng lại
				// Thoát khỏi vòng lặp
				if (item.getText().equals(month)) {
					item.click();
					sleepInSecond(1);
					break;
				}
			}
		}

	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getAngularDropdownSelectedText() {
		return (String) jsExecutor
				.executeScript("return document.querySelector(\"select[name='games']>option[selected]\").text");
	}

	public boolean areItemSelected(String[] months) {
		List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
		// Lấy ra số lượng item đã chọn =4
		int numberItemSelected = itemSelected.size();

		String allItemSelectedText = driver.findElement(By.xpath("//button[@class='ms-choice']/span[1]")).getText();

		System.out.println("Text da chon =" + allItemSelectedText);
		if (numberItemSelected <= 3 && numberItemSelected > 0) {
			for (String item : months) {
				if (allItemSelectedText.contains(item)) {
					break;
				}
			}
			return true;
		} else if (numberItemSelected > 12) {
			return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='All selected']"))
					.isDisplayed();
		} else if (numberItemSelected > 3 && numberItemSelected < 12) {
			return driver
					.findElement(By.xpath(
							"//button[@class='ms-choice']/span[text()='" + numberItemSelected + " of 12 selected']"))
					.isDisplayed();
		} else {
			new RuntimeException("No selected month!");
			return false;
		}
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}

}
