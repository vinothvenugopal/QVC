package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;

import base.BaseClass;

public class NotificationPage extends BaseClass {
	public NotificationPage(RemoteWebDriver driver,ExtentTest node, ExtentTest test) {
		this.driver = driver;
		this.node = node;
		this.test = test;
	}
	
	public LandingPage closeNotification()
	{
		WebElement ele = driver.findElementByXPath("//button[text()='OK']");
		click(ele);
		return new LandingPage(driver,node,test);
	}

}
