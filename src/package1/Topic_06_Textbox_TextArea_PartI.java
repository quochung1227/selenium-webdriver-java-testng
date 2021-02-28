package package1;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_06_Textbox_TextArea_PartI {
 WebDriver driver;
 
  @BeforeClass
  public void beforeClass() {
	  driver =new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  driver.get("https://automationfc.github.io/basic-form/");
  }
  @Test
  public void TC_01_ValidateCurrentUrl() {
	  //Textbox
	  driver.findElement(By.name("user_email")).sendKeys("automationfc@gmail.com");
	  
	  //TextArea
	  driver.findElement(By.name("user_edu")).sendKeys("Automation\nTesting\nAdvanced");
	  
	  
  }

  @AfterClass
  public void afterClass() {
  }

}
