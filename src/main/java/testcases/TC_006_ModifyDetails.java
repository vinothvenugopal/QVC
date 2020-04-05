package testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import common.ProjectSpecificMethods;
import pages.LandingPage;

public class TC_006_ModifyDetails extends ProjectSpecificMethods {

	@BeforeTest
	public void setExcelValues()
	{
		workbookName = "ModifyPrimaryDetails.xlsx";
		worksheetName = "India";
	}
	@Test(dataProvider = "getVisaDetails")
	public void modifyDetailsMethod(String passPortNumber, String visaNumber, String sponsorPhNumber, String sponsorEmailID,String applicantEmailId,String applicantPhNo) throws InterruptedException
	{
		new LandingPage(driver)
		.clickLanguageDropDown()
		.clickLanguage()
		.clickCountryDropDown()
		.clickCountry()
		.clickManageAppointmentLink()
		.clickModifyButton()
		.enterPassPortNumber(passPortNumber)
		.enterVisaNumber(visaNumber)
		.enterCaptcha()
		.clickSubmitButton()
		.enterSponsorMobileNumber(sponsorPhNumber)
		.enterSponsorEmailId(sponsorEmailID)
		.enterApplicantPhoneNumber(applicantPhNo)
		.enterApplicantEmailId(applicantEmailId)
		.clickConfirm()
		.verifyAlertMessage();
	}
	
}

