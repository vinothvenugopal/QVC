package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;

import base.BaseClass;

public class LandingPage extends BaseClass{

	public LandingPage(RemoteWebDriver driver, ExtentTest node, ExtentTest test) {
		this.driver = driver;
		this.node = node;
		this.test = test;
	}

	public LandingPage checkLandingNotification()
	{
		//WebElement ele = locateElement("xpath", "//button[text()='OK']");
		boolean isNotificationDisplayed = checkNotification("xpath","//button[text()='OK']");
		if(isNotificationDisplayed == true)
		{
			WebElement ele = locateElement("xpath", "//button[text()='OK']");
			click(ele);
			System.out.println("Notification Displayed. Notification Accepted");
		}
		else
		{
			System.out.println("No Notificaiton Displayed");
		}
		
		return this;
	}
	public LandingPage clickLanguageDropDown() throws InterruptedException
	{
			Thread.sleep(2000);
			WebElement ele = locateElement("xpath", "//div[@class='dropdown']//input");
			//WebElement ele = driver.findElementByXPath("//div[@class='dropdown']//input");
			click(ele);
			return this;
	}
	public LandingPage clickLanguage()
	{
		WebElement ele = locateElement("xpath", "//ul[@class='dropdown-menu']//a[text()='English']");
		//WebElement ele = driver.findElementByXPath("//ul[@class='dropdown-menu']//a[text()='English']");
		click(ele);
		return this;
	}
	public LandingPage clickCountryDropDown() throws InterruptedException
	{
		WebElement ele = locateElement("xpath", "(//div[@class='dropdown']//input)[2]");
		//WebElement ele = driver.findElementByXPath("(//div[@class='dropdown']//input)[2]");
		click(ele);
		return this;
	}
	public HomePage clickCountry()
	{
		WebElement ele = locateElement("xpath", "((//ul[@class='dropdown-menu'])[2])//a[text()='India']");
		//WebElement ele = driver.findElementByXPath("((//ul[@class='dropdown-menu'])[2])//a[text()='India']");
		click(ele);
		return new HomePage(driver,node,test);

	}
}
