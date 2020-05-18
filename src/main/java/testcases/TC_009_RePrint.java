package testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import common.ProjectSpecificMethods;
import pages.LandingPage;

public class TC_009_RePrint extends ProjectSpecificMethods{
	
	@BeforeTest
	public void setExcelValue()
	{
		testCaseName = "Reprint";
		testDescription = "Appointment letter Re-print flow";
		nodes = "QVC";
		authors = "Vinoth";
		category = "Smoke";
		workbookName = "ReprintData.xlsx";
		worksheetName = "India";
	}
	@Test(dataProvider = "getVisaDetails")
	public void reprintMethod(String lang, String ctry,String passPortNumber, String visaNumber) throws InterruptedException
	{
		new LandingPage(node,test)
		.checkLandingNotification()
		.clickLanguageDropDown()
		.clickLanguage(lang)
		.clickCountryDropDown()
		.clickCountry(ctry)
		.clickManageAppointmentLink()
		.clickReprintButton()
		.enterPassPortNumber(passPortNumber)
		.enterVisaNumber(visaNumber)
		.enterCaptcha()
		.clickSubmitButton()
		.verifyHeader();
	}

}
