package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;

import base.BaseClass;

public class AddWaitListPage extends BaseClass {
	public AddWaitListPage(RemoteWebDriver driver, ExtentTest node, ExtentTest test) {
		this.driver = driver;
		this.node = node;
		this.test = test;
	}
	
	public AddWaitListPage verifyVisaNumber(String visaNumber)
	{
		WebElement ele = locateElement("xpath", "//div[@class='app-summary']//span");
		String fetchText = fetchText(ele);
		if(fetchText.equals(visaNumber))
		{
			System.out.println("Visa Number Verified");
		}
		else
		{
			System.out.println("Visa Number for this passport number is not matching");
		}
		return this;
	}
	
	public AddWaitListPage selectWaitListDate(String date)
	{
		pickWaitListDate(date);
		return this;
	}
	public ManageAppointmentPage clickSubmitButton()
	{
		WebElement ele = locateElement("xpath", "//button[text()='Submit']");
		click(ele);
		return new ManageAppointmentPage(driver, node, test);
	}

}
