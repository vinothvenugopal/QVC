package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import base.BaseClass;

public class LandingPage extends BaseClass{

	public LandingPage(RemoteWebDriver driver) {
		this.driver = driver;
	}

	public LandingPage clickLanguageDropDown()
	{
		if(driver.findElementByXPath("//button[text()='OK']").isDisplayed()==true)
		{
			WebElement ele = driver.findElementByXPath("//button[text()='OK']");
			click(ele);
		}
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
		return new HomePage(driver);

	}
}
