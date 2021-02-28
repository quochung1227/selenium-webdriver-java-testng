package package1;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_06_Textbox_Textarea_PartII {
	WebDriver driver;
	String loginPageUrl, userID, password, customerID;
	String name, dob, address, city, state, pin, phone, email;
	String editAddress, editCity, editState, editPin, editPhone, editEmail;
	By nameTextboxBy = By.name("name");
	By genderRadioBy = By.name("gender");
	By dobTextboxBy = By.name("dob");
	By addressTextAreaBy = By.name("addr");
	By cityTextboxBy = By.name("city");
	By stateTextboxBy = By.name("state");
	By pinTextboxBy = By.name("pinno");
	By phoneTextboxBy = By.name("telephoneno");
	By emailTextboxBy = By.name("emailid");
	By passwordTextboxBy = By.name("password");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.guru99.com/v4");
		loginPageUrl = driver.getCurrentUrl();

		name = "John Kennedy";
		dob = "1960-01-01";
		address = "564 Suitable Address";
		city = "New York";
		state = "Califonia";
		pin = "999666";
		phone = "0985692999";
		email = "automationFCg234109C@gmail.com";

		editAddress = "Thanh tri Ha Noi";
		editCity = "Viet Nam";
		editState = "Hanoi";
		editPin = "999666";
		editPhone = "0985692969";
		editEmail = "automationtest1@gmail.com";

	}

	@Test
	public void TC_01_Register() {
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.name("emailid")).sendKeys("mis.luv11@gmail.com");
		driver.findElement(By.name("btnLogin")).click();
		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

	}

	@Test
	public void TC_02_Login() {
		driver.get(loginPageUrl);
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Manger Id : mngr310657']")).isDisplayed());
		
	}

	@Test
	public void TC_03_New_Customer() throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(dobTextboxBy).sendKeys(dob);
		driver.findElement(addressTextAreaBy).sendKeys(address);
		driver.findElement(cityTextboxBy).sendKeys(city);
		driver.findElement(stateTextboxBy).sendKeys(state);
		driver.findElement(pinTextboxBy).sendKeys(pin);
		driver.findElement(phoneTextboxBy).sendKeys(phone);
		driver.findElement(emailTextboxBy).sendKeys(email);
		driver.findElement(passwordTextboxBy).sendKeys(password);
		Thread.sleep(5000);
		driver.findElement(By.name("sub")).click();
		
		Thread.sleep(5000);
		
		// Server process +response(Output)
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[text()='Customer Registered Successfully!!!']")).isDisplayed());

		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),
				dob);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),
				address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),
				state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),
				phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),
				email);
		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		
	}

	@Test
	public void TC_04_Edit_Customer() throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.name("cusid")).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();

		// Verify 3 fields is disable
		Assert.assertFalse(isElementEnabled(nameTextboxBy));
		Assert.assertFalse(isElementEnabled(genderRadioBy));
		Assert.assertFalse(isElementEnabled(dobTextboxBy));
		// Verify others
		Assert.assertEquals(driver.findElement(nameTextboxBy).getAttribute("value"), name);
		Assert.assertEquals(driver.findElement(dobTextboxBy).getAttribute("value"), dob);
		Assert.assertEquals(driver.findElement(cityTextboxBy).getAttribute("value"), city);
		Assert.assertEquals(driver.findElement(addressTextAreaBy).getText(), address);
		Assert.assertEquals(driver.findElement(stateTextboxBy).getAttribute("value"), state);
		Assert.assertEquals(driver.findElement(pinTextboxBy).getAttribute("value"), pin);
		Assert.assertEquals(driver.findElement(phoneTextboxBy).getAttribute("value"), phone);
		Assert.assertEquals(driver.findElement(emailTextboxBy).getAttribute("value"), email);
		//clear edit customer
		driver.findElement(addressTextAreaBy).clear();
		driver.findElement(cityTextboxBy).clear();
		driver.findElement(stateTextboxBy).clear();
		driver.findElement(pinTextboxBy).clear();
		driver.findElement(phoneTextboxBy).clear();
		driver.findElement(emailTextboxBy).clear();
		// Edit customer
		driver.findElement(addressTextAreaBy).sendKeys(editAddress);
		driver.findElement(cityTextboxBy).sendKeys(editCity);
		driver.findElement(stateTextboxBy).sendKeys(editState);
		driver.findElement(pinTextboxBy).sendKeys(editPin);
		driver.findElement(phoneTextboxBy).sendKeys(editPhone);
		driver.findElement(emailTextboxBy).sendKeys(editEmail);
		Thread.sleep(5000);
		driver.findElement(By.name("sub")).click();
		// Server process +response(Output)
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[text()='Customer details updated Successfully!!!']")).isDisplayed());
		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),
				dob);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),
				editAddress);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), editCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),
				editState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), editPin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),
				editPhone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),
				editEmail);
		
		Thread.sleep(5000);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public boolean isElementEnabled(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			return true;
		} else {
			return false;
		}
	}

}
