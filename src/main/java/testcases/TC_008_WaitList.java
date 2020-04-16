package testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import common.ProjectSpecificMethods;
import pages.LandingPage;

public class TC_008_WaitList extends ProjectSpecificMethods{
	
	@BeforeTest
	public void setExcelValue()
	{
		testCaseName = "Waitlist";
		testDescription = "WaitList flow";
		nodes = "QVC";
		authors = "Vinoth";
		category = "Smoke";
		workbookName = "WaitlistData.xlsx";
		worksheetName = "India";
	}
	@Test(dataProvider = "getVisaDetails")
	public void waitListMethod(String lang, String ctry, String passPortNumber, String visaNumber, String waitListDate) throws InterruptedException
	{
		new LandingPage(driver, node, test)
		.checkLandingNotification()
		.clickLanguageDropDown()
		.clickLanguage(lang)
		.clickCountryDropDown()
		.clickCountry(ctry)
		.clickManageAppointmentLink()
		.clickWaitListButton()
		.enterPassportNumber(passPortNumber)
		.enterVisaNumber(visaNumber)
		.enterCaptcha()
		.clickSubmitButton()
		.selectWaitListDate(waitListDate)
		.clickSubmitButton();
	}

}
