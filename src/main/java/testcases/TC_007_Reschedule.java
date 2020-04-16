package testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import common.ProjectSpecificMethods;
import pages.LandingPage;

public class TC_007_Reschedule extends ProjectSpecificMethods{
	
	@BeforeTest
	public void setExcelValue()
	{
		testCaseName = "Reschedule";
		testDescription = "Apointment reschedule flow";
		nodes = "QVC";
		authors = "Vinoth";
		category = "Smoke";
		workbookName = "ModifySchedule.xlsx";
		worksheetName = "India";
	}
	
	@Test(dataProvider = "getVisaDetails")
	public void rescheduleMethod(String lang, String ctry, String passPortNumber, String visaNumber, String center, String Date, String time) throws InterruptedException
	{
		new LandingPage(driver,node,test)
		.checkLandingNotification()
		.clickLanguageDropDown()
		.clickLanguage(lang)
		.clickCountryDropDown()
		.clickCountry(ctry)
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
