package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;

import base.BaseClass;

public class NotificationPage extends BaseClass {
	public NotificationPage(ExtentTest node, ExtentTest test) {
		this.node = node;
		this.test = test;
	}
	
	public LandingPage closeNotification()
	{
		WebElement ele = getDriver().findElementByXPath("//button[text()='OK']");
		click(ele);
		return new LandingPage(node,test);
	}

}
