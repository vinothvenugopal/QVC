package testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import common.ProjectSpecificMethods;
import pages.LandingPage;

public class TC_007_Reschedule extends ProjectSpecificMethods{
	
	@BeforeTest
	public void setExcelValue()
	{
		workbookName = "ModifySchedule.xlsx";
		worksheetName = "India";
	}
	
	@Test(dataProvider = "getVisaDetails")
	public void rescheduleMethod(String passPortNumber, String visaNumber, String center, String Date, String time) throws InterruptedException
	{
		new LandingPage(driver)
		.clickLanguageDropDown()
		.clickLanguage()
		.clickCountryDropDown()
		.clickCountry()
		.clickManageAppointmentLink()
		.clickRescheduleButton()
		.enterPassPortNumber(passPortNumber)
		.enterVisaNumber(visaNumber)
		.enterCaptcha()
		.clickSubmitButton()
		.selectQVCCenter(center)
		.pickDate(Date)
		.pickTime(time)
		.clickNextButton()
		.verifyVisaCenter(center)
		.verifyDate(Date)
		.verifyTime(time)
		.clickConfirmButton()
		.verifyHeader();
		
	}

}