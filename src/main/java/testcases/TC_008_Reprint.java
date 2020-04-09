package testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import common.ProjectSpecificMethods;
import pages.LandingPage;

public class TC_008_Reprint extends ProjectSpecificMethods{
	
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
	public void reprintMethod(String passPortNumber, String visaNumber) throws InterruptedException
	{
		new LandingPage(driver,node,test)
		.clickLanguageDropDown()
		.clickLanguage()
		.clickCountryDropDown()
		.clickCountry()
		.clickManageAppointmentLink()
		.clickReprintButton()
		.enterPassPortNumber(passPortNumber)
		.enterVisaNumber(visaNumber)
		.enterCaptcha()
		.clickSubmitButton()
		.verifyHeader();
	}

}
