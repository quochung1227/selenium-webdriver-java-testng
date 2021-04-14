package package1;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_07_Default_Dropdown {
	WebDriver driver;
	Select select;
	String firstName, lastName, emailAddress, companyName, password;
	String date, month, year;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();

		firstName = "hung";
		lastName = "nguyen";

		companyName = "Panasonic";
		password = "123456";

		date = "27";
		month = "December";
		year = "1992";

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/");

	}

	@Test
	public void TC_01_Register() {
		// 1-Mở trang Register
		driver.findElement(By.cssSelector(".ico-register")).click();

		/* Điền thông tin vào các field required */
		checkToCheckboxOrRadio(By.cssSelector("#gender-male"));
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);

		// Khởi tạo select để thao tác với Date
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']")));
		select.selectByVisibleText(date);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), date);

		// Khởi tạo select để chọn dropdown Month
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']")));
		select.selectByVisibleText(month);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);

		// Khởi tạo select để chọn dropdown Year
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']")));
		select.selectByVisibleText(year);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);

		emailAddress = "hungnguyen" + getRandomNumber() + "@hotmail.com";

		// Kiểm tra dropdown không cho chọn multiple
		// Assert.assertFalse(select.isMultiple());

		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Company")).sendKeys(companyName);

		checkToCheckboxOrRadio(By.id("Newsletter"));
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);

		/* 3-Đăng kí */

		driver.findElement(By.cssSelector("#register-button")).click();
		// driver.findElement(By.xpath(".//button[@id=\"register-button\"]")).click();
		/* 4- Kiểm tra message đăng kí thành công */
		Assert.assertEquals(driver.findElement(By.cssSelector(".result")).getText(), "Your registration completed");

		/* Vào trang account */
		driver.findElement(By.cssSelector(".ico-account")).click();
		/* Kiểm tra xem đúng thông tin hay chưa? */
		Assert.assertTrue(driver.findElement(By.cssSelector("#gender-male")).isSelected());
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), emailAddress);
		Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"), companyName);
		Assert.assertTrue(driver.findElement(By.cssSelector("#Newsletter")).isSelected());

		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), date);

		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);

		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);

		/* Đăng xuất */
		driver.findElement(By.cssSelector(".ico-logout")).click();
	}

	@Test
	public void TC_02_Multiple_Select() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		select = new Select(driver.findElement(By.xpath("//select[@id='job2']")));
		
		ArrayList<String> allItemText = new ArrayList<String>();
		allItemText.add("Automation");
		allItemText.add("Mobile");
		allItemText.add("Perfomance");
		allItemText.add("Functional UI");
		//Chonj 4 items
		for (String item : allItemText) {
			select.deselectByVisibleText(item);
		}
		sleepInSecond(5);
		// Kiểm tra dropdown có hỗ trợ multiselect hay không
		Assert.assertTrue(select.isMultiple());
		// Kiểm tra đã chọn đúng 4 item thành công
		List<WebElement> allSelectedItems = select.getAllSelectedOptions();
		ArrayList<String> allSelectedText = new ArrayList<String>();

		for (WebElement item : allSelectedItems) {
			allSelectedText.add(item.getText());
		}
		Assert.assertEquals(allSelectedText.size(), 4);
		Assert.assertEquals(allSelectedText, allItemText);
	}

	public void checkToCheckboxOrRadio(By by) {
		WebElement element = driver.findElement(by);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void uncheckToCheckbox(By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			element.click();
		}
	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}