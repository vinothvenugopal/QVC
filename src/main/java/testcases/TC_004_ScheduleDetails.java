package testcases;

import org.testng.annotations.Test;

import common.ProjectSpecificMethods;
import pages.NotificationPage;

public class TC_004_ScheduleDetails extends ProjectSpecificMethods {
	
	@Test(dataProvider = "getVisaDetails")
	public void applicantDetailsMethod(String passportNumber, String visaNumber, String phoneNumber, String emailID, String category, String center, String date, String timeSlot) throws InterruptedException
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
		.clickConfirmationButton()
		.selectQVCCenter(center)
		.selectCategory(category)
		.selectDate(date);
		
	}

}
