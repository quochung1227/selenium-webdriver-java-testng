package javaTester;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.testng.Assert;

public class Topic_11_List {
	public static void main(String[] args ) {
		List<String> address = new ArrayList<>();
		address.add("HCM");
		address.add("HA NOI ");
		address.add("Can Tho");
		System.out.println(address.size());
		Assert.assertEquals(address.size(), 3);
		
		//List<WebElement> checkboxes = driver.findElements(By.xpath(""));
	}
}
