package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import base.BaseClass;

public class NotificationPage extends BaseClass {
	public NotificationPage(RemoteWebDriver driver) {
		this.driver = driver;
	}
	
	public LandingPage closeNotification()
	{
		WebElement ele = driver.findElementByXPath("//button[text()='OK']");
		click(ele);
		return new LandingPage(driver);
	}

}
