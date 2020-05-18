package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;

import base.BaseClass;

public class AddWaitListPage extends BaseClass {
	public AddWaitListPage(ExtentTest node, ExtentTest test) {
		
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
	public ManageAppointmentPage clickSubmitButton() throws InterruptedException
	{
		WebElement ele = locateElement("xpath", "//button[text()='Submit']");
		click(ele);
		
		boolean checkNotification = checkNotification("xpath", "//div[@class='modal-content']");
		if (checkNotification==true)
		{
			WebElement notificationTextele = locateElement("xpath", "//div[@class='modal-content']//modal-content");
			String fetchedText = fetchText(notificationTextele);
			System.out.println("Notification: "+fetchedText+" is displayed. Could not proceed further with waitlisting");
			reportStep(fetchedText, "fail");
			WebElement ele1 = locateElement("xpath", "(//div[@class='modal-content']//button)[2]");
			click(ele1);
			return null;
			//throw new RuntimeException();
		}
		else
		return new ManageAppointmentPage( node, test);
	}
	
	/*
	 * public ManageAppointmentPage checkNotification() throws InterruptedException
	 * {
	 * 
	 * boolean checkNotification = checkNotification("xpath",
	 * "//div[@class='modal-content']"); if (checkNotification==true) { WebElement
	 * notificationTextele = locateElement("xpath",
	 * "//div[@class='modal-content']//modal-content"); String fetchedText =
	 * fetchText(notificationTextele); System.out.println("Notification: "
	 * +fetchedText+" is displayed. Could not proceed further with waitlisting");
	 * WebElement ele = locateElement("xpath",
	 * "(//div[@class='modal-content']//button)[2]"); click(ele);
	 * reportStep(fetchedText, "fail"); throw new RuntimeException(); } else return
	 * new ManageAppointmentPage(driver, node, test); }
	 */
}
