package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import base.BaseClass;

public class ManageAppointmentPage extends BaseClass {
	public ManageAppointmentPage(RemoteWebDriver driver) {
		this.driver = driver;
	}
	
	public ModifyPrimaryContactPage clickModifyButton()
	{
		WebElement ele = locateElement("xpath", "//button[text()='Modify']");
		click(ele);
		return new ModifyPrimaryContactPage(driver);
	}
	
	public ModifySchedulePage clickRescheduleButton()
	{
		WebElement ele = locateElement("xpath", "//button[text()='Reschedule']");
		click(ele);
		return new ModifySchedulePage(driver);
	}
	public ManageAppointmentPage clickWaitListButton()
	{
		WebElement ele = locateElement("xpath", "//button[text()='Waitlist']");
		click(ele);
		return this;
	}
	public ReprintPage clickReprintButton()
	{
		WebElement ele = locateElement("xpath", "//button[text()='Reprint']");
		click(ele);
		return new ReprintPage(driver);
	}
}
