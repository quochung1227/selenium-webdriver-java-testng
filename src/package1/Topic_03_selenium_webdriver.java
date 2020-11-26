package package1;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class Topic_03_selenium_webdriver {
WebDriver driver;
@Test
public void TC_01() throws InterruptedException {
    // 1. Mở trinh duyệt
    driver=new FirefoxDriver();
    // nhạp vao trang 
    driver.get("http://live.demoguru99.com/");
    // Click vào "My Account" để dăng nhập 
    //Theo X-path 
    driver.findElement(By.xpath(".//*[@id='top']/body/div/div/div[3]/div/div[4]/ul/li[1]/a")).click();
    // Click LOGIN button
    driver.findElement(By.id("send2")).click();
    // Kiem tra require co xuat hien ko 
    driver.findElement(By.id("advice-required-entry-email")).isDisplayed();
    driver.findElement(By.id("advice-required-entry-pass")).isDisplayed();
    Thread.sleep(4000);
    driver.close();
}
@Test
public void TC_02() throws InterruptedException {
	driver=new FirefoxDriver();
	driver.get("http://live.demoguru99.com/");
	driver.findElement(By.xpath(".//*[@id='top']/body/div/div/div[3]/div/div[4]/ul/li[1]/a")).click();
	driver.findElement(By.id("email")).sendKeys("123434234@12312.123123");
	driver.findElement(By.id("pass")).sendKeys("123456");
	driver.findElement(By.id("send2")).click();
	driver.findElement(By.id("advice-validate-email-email")).isDisplayed();
	Thread.sleep(4000);
	driver.close();
}
@Test
public void TC_03() throws InterruptedException {
	driver=new FirefoxDriver();
	driver.get("http://live.demoguru99.com/");
	driver.findElement(By.xpath(".//*[@id='top']/body/div/div/div[3]/div/div[4]/ul/li[1]/a")).click();
	driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
	driver.findElement(By.id("pass")).sendKeys("123");
	driver.findElement(By.id("send2")).click();
	driver.findElement(By.id("advice-validate-password-pass")).isDisplayed();
	Thread.sleep(5000);
	driver.close();
}
@Test
public void TC_04() throws InterruptedException {
	driver=new FirefoxDriver();
	driver.get("http://live.demoguru99.com/");
	driver.findElement(By.xpath(".//*[@id='top']/body/div/div/div[3]/div/div[4]/ul/li[1]/a")).click();
	driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
	driver.findElement(By.id("pass")).sendKeys("123123123");
	driver.findElement(By.id("send2")).click();
	driver.findElement(By.xpath(".//*[@id='top']/body/div/div/div[2]/div/div/div/ul/li/ul/li/span")).isDisplayed();
	Thread.sleep(5000);
	driver.close();
}
@Test
public void TC_05() {
	driver=new FirefoxDriver();
	driver.get("http://live.demoguru99.com/");
	driver.findElement(By.xpath(".//*[@id='top']/body/div/div/div[3]/div/div[4]/ul/li[1]/a")).click();
	driver.findElement(By.xpath("//a[@title=\"Create an Account\"]")).click();
	driver.findElement(By.id("firstname")).sendKeys("Hung");
	driver.findElement(By.id("middlename")).sendKeys("Quoc");
	driver.findElement(By.id("lastname")).sendKeys("Nguyen");
	driver.findElement(By.id("email_address")).sendKeys("nguyenquochung2712@gmail.com");
	driver.findElement(By.id("password")).sendKeys("123456");
	driver.findElement(By.id("confirmation")).sendKeys("123456");
	
}
}
