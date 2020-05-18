package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;

import base.BaseClass;

public class ManageAppointmentPage extends BaseClass {
	public ManageAppointmentPage(ExtentTest node, ExtentTest test) {
		
		this.node = node;
		this.test = test;
	}
	
	public ModifyPrimaryContactPage clickModifyButton()
	{
		WebElement ele = locateElement("xpath", "//button[text()='Modify']");
		click(ele);
		return new ModifyPrimaryContactPage(node,test);
	}
	
	public ModifySchedulePage clickRescheduleButton()
	{
		WebElement ele = locateElement("xpath", "//button[text()='Reschedule']");
		click(ele);
		return new ModifySchedulePage(node,test);
	}
	public WaitListPage clickWaitListButton()
	{
		WebElement ele = locateElement("xpath", "//button[text()='Waitlist']");
		click(ele);
		return new WaitListPage(node, test);
	}
	public ReprintPage clickReprintButton()
	{
		WebElement ele = locateElement("xpath", "//button[text()='Reprint']");
		click(ele);
		return new ReprintPage(node, test);
	}
}
