package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import base.BaseClass;

public class ModifyPrimaryContactPage extends BaseClass{
	public ModifyPrimaryContactPage(RemoteWebDriver driver) {
		this.driver = driver;
	}
	
	public ModifyPrimaryContactPage enterSponsorMobileNumber(String mobileNumber) throws InterruptedException
	{
		//Thread.sleep(1000);
		WebElement ele = locateElement("id", "phone");
		//WebElement ele = driver.findElementById("phone");
		clearAndEnter(ele, mobileNumber);
		return this;
	}
	public ModifyPrimaryContactPage enterSponsorEmailId(String emailID)
	{
		WebElement ele = locateElement("id", "email");
		//WebElement ele = driver.findElementById("email");
		clearAndEnter(ele, emailID);
		return this;
	}
	public ModifyPrimaryContactPage enterApplicantEmailId(String secondEmailID)
	{
		WebElement ele = locateElement("id", "emailId");
		//WebElement ele = driver.findElementById("emailId");
		clearAndEnter(ele, secondEmailID);
		return this;
	}
	public ModifyPrimaryContactPage enterApplicantPhoneNumber(String mobileNumber)
	{
		WebElement ele = locateElement("id", "contactNumber");
		clearAndEnter(ele, mobileNumber);
		return this;
	}
	public ModifyPrimaryContactPage clickConfirm()
	{
		WebElement ele = locateElement("xpath", "//button[text()='Confirm']");
		click(ele);
		return this;
	}
	public ManageAppointmentPage verifyAlertMessage()
	{
		WebElement ele = locateElement("xpath", "//div[@class='col-md-12']//modal-content");
		boolean trueFalse = verifyUIValue("Updated Successfully...", ele);
		if(trueFalse == true)
		{
			System.out.println("Modifications updated successfully");
		}
		else
		{
			System.out.println("Could not update the modifications");
		}
		return new ManageAppointmentPage(driver);
	}
}
