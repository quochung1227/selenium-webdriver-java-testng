package package1;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class Topic_17_Wait_II__find_Element {
	WebDriver driver;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\BrowserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		//driver.get("https://www.facebook.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

	}

	// @Test
	public void TC_01_FindElement() {
		// 1. Tim duoc 1 Element
		System.out.println("Start - " + getDateTimeNow());
		driver.findElement(By.id("email"));
		System.out.println("End - " + getDateTimeNow());
		// 2. Khong tim thay Element
		System.out.println("Start - " + getDateTimeNow());
		driver.findElement(By.xpath("//input[@id='email' or @id='pass']"));
		System.out.println("End - " + getDateTimeNow());
		// 3. Tim thay nhieu hon 1 Element
		System.out.println("Start - " + getDateTimeNow());
		try {
			driver.findElement(By.xpath("//label"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("End - " + getDateTimeNow());
		}

	}

	//@Test
	public void TC_02_FindElements() {
		// 1- Tim duoc 1 element
		// Neu tim thay thi khong can cho het timeout
		// Neu khong tim thay thi tiep tuc cho= - trong thoi gian cho moi 0,5s tim lai 1
		// lan
		// neu khong tim thay thi tiep tuc tim cho den het timeout
		// Chuyen qua step tiep theo
		// Luu no vao list ( chi co duy nhat 1 Element)
		System.out.println("Start - " + getDateTimeNow());
		List<WebElement> elements = driver.findElements(By.id("email"));
		System.out.println(elements.size());
		System.out.println("End - " + getDateTimeNow());

		// 2- Tim duoc > 1 element
		System.out.println("Start - " + getDateTimeNow());
		elements = driver.findElements(By.xpath("//input"));
		System.out.println(elements.size());
		System.out.println("End - " + getDateTimeNow());

		// 3- Khong tim duoc element nao
		// Cho het timeout ma van khong tim thay
		// Khong danh failed testcase
		// Khong throw Exception
		// Chi la 1 list rong
		// Chuyen qua step tiep theo
		System.out.println("Start - " + getDateTimeNow());
		elements = driver.findElements(By.xpath("//label"));
		System.out.println(elements.size());
		System.out.println("End - " + getDateTimeNow());
	}
	@Test 
	public void TC_03_Find_Elements() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		List <WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
		System.out.println(checkboxes.size());
		
		for (WebElement checkbox : checkboxes) {
			checkbox.click();
			sleepInSecond(1);
		}
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

	public String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
	}
}
