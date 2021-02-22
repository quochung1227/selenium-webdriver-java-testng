package package1;

import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_03_Run_On_Brower {
	WebDriver driver;
	String projectLocation = System.getProperty("user.dir");

	@Test
	public void TC_01_Run_On_Firefox() {
		// Firefox 47 + Selenium 2.xx + No geckodriver
		driver = new FirefoxDriver();
		driver.get("https://google.com");
		driver.quit();
	}

	// Firefox >version 48 + Selenium 3.xx + geckodriver
	public void TC_012_Run_On_Firefox() {
		// 01
		System.setProperty("webdriver.chrome.driver", "Path_of_geckodriver");
		driver = new ChromeDriver();
		driver.get("https://google.com");
		driver.quit();
	}

	@Test
	public void TC_02_Run_On_Chrome() {
		// 01- Only 1 computer
		// System.setProperty("webdriver.chrome.driver",
		// "C:\\Users\\Admin\\git\\selenium-webdriver-java-testng\\BrowserDrivers\\chromedriver.exe");
		// 02-All Computer
		System.setProperty("webdriver.chrome.driver",".\\BrowserDrivers\\chromedriver.exe");
		// .= Dai dien cho project location
		// 03- All computers
		//System.setProperty("webdriver.chrome.driver", projectLocation + "\\BrowerDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://google.com");
		driver.quit();
	}

}
