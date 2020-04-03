package testcases;

import org.testng.annotations.Test;

import common.ProjectSpecificMethods;
import pages.NotificationPage;

public class TC_003_ApplicantDetails extends ProjectSpecificMethods {
	
	@Test(dataProvider = "getVisaDetails")
	public void applicantDetailsMethod(String passportNumber, String visaNumber, String phoneNumber, String emailID) throws InterruptedException
	{
		new NotificationPage(driver)
		.closeNotification()
		.clickLanguageDropDown()
		.clickLanguage()
		.clickCountryDropDown()
		.clickCountry()
		.clickBookAppointment()
	//	.clickHide()
		.clickIndividualAppointment()
		.enterPassportNumber(passportNumber)
		.enterVisaNumber(visaNumber)
		.enterCAPTCHA()
		.clickSubmit()
		.enterSponsorMobileNumber(phoneNumber)
		.enterSponsorEmailId(emailID)
		.enterApplicantEmailId(emailID)
		.clickConfirmationButton();
		
	}

}
