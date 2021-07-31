package package1;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_15_Javascript_Executer {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", ".\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 15);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		jsExecutor = (JavascriptExecutor) driver;
	}

	// @Test
	public void TC_01_Click_Hidden_Element() {
		driver.get("https://www.zingpoll.com/");
		// Click to Samdung GalaxyS20(Hidden)
		WebElement vietnameseLanguage = driver
				.findElement(By.xpath("//li[@class='dropdown hidden-xs']//a[contains(string(),'Tiếng Việt')]"));
		jsExecutor.executeScript("arguments[0].click();", vietnameseLanguage);
		sleepInSecond(10);
	}

	// @Test
	public void TC_02() {
		navigateToUrlByJS(driver, "http://live.demoguru99.com/");
		String homepageDomain = (String) executeForBrowser(driver, "return document.domain;");
		Assert.assertEquals(homepageDomain, "live.demoguru99.com");

		highlightElement(driver, "//a[text()='Mobile']");
		clickToElementByJS(driver, "//a[text()='Mobile']");

		highlightElement(driver,
				"//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		clickToElementByJS(driver,
				"//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");

		highlightElement(driver, "//li[@class='success-msg']//span");
		Assert.assertTrue(getInnerText(driver).contains("Samsung Galaxy was added to your shopping cart."));

		highlightElement(driver, "//a[text()='Customer Service']");
		clickToElementByJS(driver, "//a[text()='Customer Service']");

		highlightElement(driver, "//input[@id='newsletter']");
		scrollToElement(driver, "//input[@id='newsletter']");
		sendkeyToElementByJS(driver, "//input[@id='newsletter']", getEmailRandom());

		highlightElement(driver, "//button[@title='Subscribe']");
		clickToElementByJS(driver, "//button[@title='Subscribe']");

		highlightElement(driver, "//li[@class='success-msg']//span");
		Assert.assertTrue(getInnerText(driver).contains("Thank you for your subscription."));

		navigateToUrlByJS(driver, "http://demo.guru99.com/v4/");

		String demoGuruDomain = (String) executeForBrowser(driver, "return document.domain;");
		Assert.assertEquals(demoGuruDomain, "demo.guru99.com");

	}

	//@Test
	public void TC_03() {
		navigateToUrlByJS(driver, "https://login.ubuntu.com/");
		clickToElementByJS(driver, "//button[@data-qa-id='login_button']");
		Assert.assertEquals(getHTML5ValidationMessage(driver, "//input[@name='email']"), "Please fill out this field.");

		sendkeyToElementByJS(driver, "//input[@name='email']", "aa");
		clickToElementByJS(driver, "//button[@data-qa-id='login_button']");
		Assert.assertEquals(getHTML5ValidationMessage(driver, "//input[@name='email']"),
				"Please include an '@' in the email address. 'aa' is missing an '@'.");

		sendkeyToElementByJS(driver, "//input[@name='email']", "aa@");
		clickToElementByJS(driver, "//button[@data-qa-id='login_button']");
		Assert.assertEquals(getHTML5ValidationMessage(driver, "//input[@name='email']"), 
				"Please enter a part following '@'. 'aa@' is incomplete.");

		sendkeyToElementByJS(driver, "//input[@name='email']", "123@...");
		clickToElementByJS(driver, "//button[@data-qa-id='login_button']");
		Assert.assertEquals(getHTML5ValidationMessage(driver, "//input[@name='email']"),
				"'.' is used at a wrong position in '...'.");

		sendkeyToElementByJS(driver, "//input[@name='email']", "hung@gmail.com");
		clickToElementByJS(driver, "//button[@data-qa-id='login_button']");
		sleepInSecond(10);
		Assert.assertEquals(getHTML5ValidationMessage(driver, "//input[@name='password']"),
				"Please fill out this field.");
	}

	@Test
	public void TC_04() {
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

		name = "John Kennedy";
		dob = "1960-01-01";
		address = "564 Suitable Address";
		city = "New York";
		state = "Califonia";
		pin = "999666";
		phone = "0985692999";
		email = getEmailRandom();
		


		driver.get("https://demo.guru99.com/v4");
		loginPageUrl = driver.getCurrentUrl();

		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.name("emailid")).sendKeys("mis.luv11@gmail.com");
		driver.findElement(By.name("btnLogin")).click();
		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

		driver.get(loginPageUrl);
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Manger Id : mngr310657']")).isDisplayed());

		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		//Input
		driver.findElement(nameTextboxBy).sendKeys(name);
		//Remmove atribute(type=date)
		removeAttributeInDOM(driver, "//input[@name='dob']", "type");
		sleepInSecond(5);
		driver.findElement(dobTextboxBy).sendKeys(dob);
		driver.findElement(addressTextAreaBy).sendKeys(address);
		driver.findElement(cityTextboxBy).sendKeys(city);
		driver.findElement(stateTextboxBy).sendKeys(state);
		driver.findElement(pinTextboxBy).sendKeys(pin);
		driver.findElement(phoneTextboxBy).sendKeys(phone);
		driver.findElement(emailTextboxBy).sendKeys(email);
		driver.findElement(passwordTextboxBy).sendKeys(password);
		sleepInSecond(5);
		;
		driver.findElement(By.name("sub")).click();

		sleepInSecond(5);

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
	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public Object executeForBrowser(WebDriver driver, String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText(WebDriver driver) {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(WebDriver driver, String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElement(WebDriver driver, String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(WebDriver driver,String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public String getHTML5ValidationMessage(WebDriver driver, String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getElement(locator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}

	public String getEmailRandom() {
		Random rand = new Random();
		return "automation" + rand.nextInt() + "@gmail.com";
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
