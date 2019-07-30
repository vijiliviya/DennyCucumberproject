package pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.app.commonFunctionsLibrary.BaseUtil;
import com.app.commonFunctionsLibrary.CommonFunctions;

public class HRMS_Page extends BaseUtil{


	public WebDriverWait wait = new WebDriverWait(driver, 40);
	private BaseUtil base;
	Properties prop=new Properties();
	static Properties object = null;
	static String locatorMethod = null;
	static String locatorValue = null;
	static String value = "";
	Properties val =new Properties();
	static String confvalue;
	static FileInputStream con;
	static Properties conf = new Properties();
	CommonFunctions lib = new CommonFunctions(); 

	public HRMS_Page(BaseUtil base) { 
		// TODO Auto-generated constructor stub
		this.base = base;
	}

	/*
	 *To read the values from config properties
	 */
	public String getConfigValues(String key) throws IOException {
		String object = System.getProperty(
				"user.dir") + "\\src\\test\\resources\\config\\config.properties";
		con = new FileInputStream(object);
		conf.load(con);
		confvalue = conf.getProperty(key);
		return confvalue;
	}

	/*
	 * launch the hrms site
	 */
	public void launchUrl(String url) throws InterruptedException, IOException {
		driver.get(getConfigValues(url));
	}


	/*
	 * enter username and password
	 */
	public void sendCredentials(String username) throws IOException{
		lib.enterText("hrmsUserName",
				getConfigValues(username));

		lib.enterText("hrmsPassWord",
				getConfigValues("hrmsPassword"));
	}


	/*
	 * Click on hrms login
	 */
	public void clickLoginButton()
	{
		lib.clickAnElement
		("saasLogin");
	}


	/*
	 * get login button
	 */
	public String getLoginButton() throws IOException
	{
		String logbutton = null;
		logbutton = lib.getValue("saasLogin");
		return logbutton;
	}

	public void clickUserTab()
	{
		lib.clickAnElement("hrmsUserTab");
	}


	/*
	 * HR creats new user and verify user table
	 */
	public void createNewUserAndVerify() throws IOException, InterruptedException{

		String secnames=null;

		lib.clickAnElement("hrmsNewUserbutton");
		lib.enterText("hrmsEnterEmail",
				getConfigValues("hrmsNewEmail"));
		lib.waitInSleep();
		lib.clickAnElement("hrmsEmailSaveButton");
		lib.waitInSleep();
		lib.clickAnElement("hrmsNewUserbutton");
		lib.sendValueAndEnter("hrmsUserSearch", 
				getConfigValues("hrmsEmailUser"));
		lib.waitInSleep();

		try {
			WebElement searchTable = driver.findElement(By.xpath
					(getConfigValues("hrmsSearchTable")));
			List<WebElement>tablerows = searchTable.findElements(By.tagName("tr"));

			String actsecXpath= getConfigValues("hrmsSeTableActXpath");
			for(int tr=1;tr<=tablerows.size(); tr++)
			{
				secnames = driver.findElement(By.xpath(actsecXpath+tr+"]/td[2]")).getText().trim();

				if(getConfigValues("hrmsEmailUser").equals(secnames))
				{
					lib.waitInSleep();
					Assert.assertEquals(secnames+ " is present in user search table", 
							getConfigValues("hrmsEmailUser"), secnames);
					break;
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Assert.assertNotEquals(secnames+ " is not present in user search table", 
					getConfigValues("hrmsEmailUser"), secnames);
		}
	}


	public void logoutHrms(){
		String logbutton=null;
		lib.clickAnElement("hrmsLogout");
		lib.clickAnElement("hrmsLogoutDropDown");

		try {
			logbutton = lib.getValue("saasLogin");

			Assert.assertEquals("HRMS logout successfully", 
					logbutton, "Login");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Assert.assertNotEquals("HRMS is not logout", 
					logbutton, "Login");
		}
	}

	public void updateBasicDetails() throws IOException, InterruptedException
	{
		lib.enterText("hrmsFirstName",
				getConfigValues("hrmsEmailUser"));
		lib.enterText("hrmsLastName",
				getConfigValues("hrmsLastNam"));
		lib.selectDropdown("hrmsGender", "Value",
				getConfigValues("hrmsGenderValue"));
		lib.clickAnElement("hrmsDOB");
		lib.clickAnElement("hrmsYearPrevious");
		lib.clickAnElement("hrmsYearPrevious");
		lib.clickAnElement("hrmsChooseYear");
		lib.clickAnElement("hrmsChooseMonth");
		lib.clickAnElement("hrmsChooseDay");
		lib.selectDropdown("hrmsMaritalStatus", "Value",
				getConfigValues("hrmsMaritalStatusValue"));
		lib.selectDropdown("hrmsBloodGroup", "Value",
				getConfigValues("hrmsBloodGroup"));
		lib.enterText("hrmsMobileNumber", 
				getConfigValues("hrmsMobileValue"));
		lib.enterText("hrmsLandLine",
				getConfigValues("hrmsLandlineValue"));
		lib.enterText("hrmsPersonalEmail",
				getConfigValues("hrmsPersonalEmailValue"));
		lib.clickAnElement("hrmsJoinDate");
		lib.clickAnElement("hrmsJoinDay");
		lib.selectDropdown("hrmsJobTitle", "Value",
				getConfigValues("hrmsJobTitleNumber"));
		lib.enterText("hrmsTotalExperience",
				getConfigValues("hrmsTotalExperienceNumber"));
		lib.enterText("hrmsRelaventExperience",
				getConfigValues("hrmsRelaventExperienceNumber"));
		lib.enterText("hrmsStreetName",
				getConfigValues("hrmsStreetName"));
		lib.enterText("hrmsLocality",
				getConfigValues("hrmsLocality"));

		lib.selectDropdown("hrmsSelectCountry", "Value",
				getConfigValues("hrmsCountryName"));

		lib.selectDropdown("hrmsSelectState", "Value",
				getConfigValues("hrmsStateName"));
		lib.selectDropdown("hrmsCityName", "Value",
				getConfigValues("hrmsCityName"));
		lib.enterText("hrmsPinNumber",
				getConfigValues("hrmsPinValue"));
		lib.clickAnElement("hrmsSameAsPermanentCheckBox");
		lib.enterText("hrmsEmergencyName",
				getConfigValues("hrmsEmergencyName"));
		lib.selectDropdown("hrmsRelationship","Value", 
				getConfigValues("hrmsRelationship"));
		lib.enterText("hrmsEmergencyNumber", 
				getConfigValues("hrmsEmergencyNumber"));
		lib.selectDropdown("hrmsEmergencyAddress","Value",
				getConfigValues("hrmsEmergencyAddress"));
		lib.clickAnElement("hrmsNext");

	}
	public void updateAcademic() throws InterruptedException, IOException{

		String academic = lib.getValue("hrmsNewAcademicButton");

		Assert.assertEquals("Page in Academic", 
				academic, "New Academic");

		lib.selectDropdown("hrmsCourse", "Value",
				getConfigValues("hrmsCourse10"));
		lib.selectDropdown("hrmsCourseType","Value",
				getConfigValues("hrmsCourseType"));
		lib.enterText("hrmsSpecification",
				getConfigValues("hrmsSpecification"));
		lib.enterText("hrmsInstitue",
				getConfigValues("hrmsInstitue"));
		lib.enterText("hrmsUniversity",
				getConfigValues("hrmsUniversity"));
		lib.selectDropdown("hrmsPassedYear", "Value",
				getConfigValues("hrmsPassedYear"));
		lib.enterText("hrmsMarks",
				getConfigValues("hrmsMarks"));

		lib.clickAnElement("hrmsNext");

	}

	public void updateEmployment() throws IOException {

		String employee = lib.getValue("hrmsEmploymentButton");

		Assert.assertEquals("Page in New Employment", 
				employee, "Add Employment");

		lib.clickAnElement("hrmsEmploymentButton");

		lib.enterText("hrmsEmploymentName",
				getConfigValues("hrmsEmployeeName"));

		lib.enterText("hrmsEmployDesignation", 
				getConfigValues("hrmsEmployeDesignation"));

		lib.clickAnElement("hrmsEmployeeJoinDate");

		lib.clickAnElement("hrmsEmployeeJoinedYear");
		lib.clickAnElement("hrmsEmployeeJoinedMonth");
		lib.clickAnElement("hrmsEmployeeJoinedDate");
		lib.clickAnElement("hrmsEmployeeRelieved");

		lib.clickAnElement("hrmsEmployeeRelivedYear");
		lib.clickAnElement("hrmsEmployeeRelivedMonth");
		lib.clickAnElement("hrmsEmployeeRelivedDate");
		lib.enterText("hrmsHRName", 
				getConfigValues("hrmsHrName"));
		lib.enterText("hrmsHrDesignation", 
				getConfigValues("hrmsHrDesig"));
		lib.enterText("hrmsHrPhoneNumber", 
				getConfigValues("hrPhoneNumber"));
		lib.enterText("hrmsHrEmail", 
				getConfigValues("hrEmail"));

		lib.enterText("hrmsReportingToName", 
				getConfigValues("hrReportingName"));
		lib.enterText("hrmsReportingToDesig", 
				getConfigValues("hrReportingDesign"));
		lib.enterText("hrmsReportingToPN", 
				getConfigValues("hrmsReportingPN"));
		lib.enterText("hrmsReportingToMail", 
				getConfigValues("hrmsReportingMail"));

		lib.clickAnElement("hrmsNext");

	}

	public void updateCertification() throws IOException {

		String certificate = lib.getValue("hrmsCertificationButton");

		Assert.assertEquals("Page in Certification", 
				certificate, "Add Certification");

		lib.clickAnElement("hrmsCertificationButton");
		lib.enterText("hrmsCertificationNm", 
				getConfigValues("hrmsCertificationName"));
		lib.enterText("hrmsCertificationVendor", 
				getConfigValues("hrmsCertificationVendor"));
		lib.enterText("hrmsCertificationScore", 
				getConfigValues("hrmsScores"));
		lib.clickAnElement("hrmsCertificationClickDate");
		lib.clickAnElement
		("hrmsCertificateCompletionYear");
		lib.clickAnElement
		("hrmsCertificateCompletionMonth");
		lib.clickAnElement
		("hrmsCertificateCompletionDate");
		lib.clickAnElement
		("hrmsCertificateValidTo");
		lib.clickAnElement
		("hrmsValidToYear");
		lib.clickAnElement
		("hrmsValidToMonth");
		lib.clickAnElement
		("hrmsValidToDate");

		lib.clickAnElement("hrmsNext");


	}


	public void updateFamily() throws IOException, InterruptedException
	{

		String family = lib.getValue("hrmsAddFamilyButton");

		Assert.assertEquals("Page in Family", 
				family, "Add Family Member");

		lib.clickAnElement("hrmsAddFamilyButton");
		lib.enterText("hrmsFamilyName",
				getConfigValues("hrmsFamilyNam"));

		lib.selectDropdown("hrmsFamilyRelationship",
				"Value",getConfigValues("hrmsRelationshipValue"));
		lib.clickAnElement("hrmsDOBClick");
		lib.clickAnElement("hrmsYearPrevious");
		lib.clickAnElement("hrmsYearPrevious");
		lib.clickAnElement("hrmsYearPrevious");
		lib.clickAnElement("hrmsYearPrevious");
		lib.clickAnElement("hrmsFamilyDOBYear");
		lib.clickAnElement("hrmsFamilyDOBMonth");
		lib.clickAnElement("hrmsFamilyDOBDate");
		lib.clickAnElement("hrmsNext");



	}


	public void updateEmployeeDocuments() throws IOException, InterruptedException
	{

		String certificate = lib.getValue("hrmsDocumentPanLabel");

		Assert.assertEquals("Page in Employee Skill", 
				certificate, "PAN Number");

		lib.enterText("hrmsDocumentPanCard",
				getConfigValues("hrmsPanNumber"));

		lib.enterText("hrmsDocumentAadharNumber",
				getConfigValues("hrmsAadharNumber"));

		lib.clickAnElement("hrmsPassportNo");

		lib.clickAnElement("hrmsNext");

	}

	public void updateEmployeeSkills(String skill) throws IOException, InterruptedException
	{
		String skillset = lib.getValue("hrmsAddTechnicalSkill");


		Assert.assertEquals("Page in Technical skill", 
				skillset, "Add Technical Skills");

		lib.clickAnElement("hrmsAddTechSkills");

		lib.clickAnElement("hrmsClickSkillSet");

		lib.sendValueAndEnter("hrmsSkillField",skill);

		lib.clearAndEnterValue("hrmsTotalYrsExp",
				getConfigValues("hrmsTotalYearsExp"));

		lib.clearAndEnterValue("hrmsTotalMonthsExp",
				getConfigValues("hrmsTotalMonExp"));

		lib.clickAnElement("hrmsCurrentSkillSet");
		lib.clearAndEnterValue("hrmsSupportTotalYrsExp",
				getConfigValues("hrmsTotalYearsExp"));
		lib.clearAndEnterValue("hrmsSupportTotalMonth",
				getConfigValues("hrmsTotalMonExp"));
		lib.clickAnElement("hrmsSupportYes");
		lib.clickAnElement("hrmsSkillSetSave");

		String nonskillset = lib.getValue("hrmsNonTechSkillSet");

		Assert.assertEquals("Page in non Technical skill", 
				nonskillset, "Add Non-Technical Skills");

		lib.clickAnElement("hrmsNonTecSkillSet");
		lib.clickAnElement("hrmsNonTech");
		lib.sendValueAndEnter("hrmsSkillField",
				getConfigValues("hrmsNonTechSkillValue"));

		lib.clearAndEnterValue("hrmsNonTotalExp",
				getConfigValues("hrmsTotalYearsExp"));

		lib.clearAndEnterValue("hrmsNonTotalMonth",
				getConfigValues("hrmsTotalMonExp"));
		lib.clickAnElement("hrmsNonSupportYes");

		lib.clickAnElement("hrmsSkillSetSave");

		String domainname = lib.getValue("hrmsDomainButtonName");

		Assert.assertEquals( 
				domainname, "Add Domain Skills");
		lib.clickAnElement("hrmsAddDomainButton");
		lib.clickAnElement("hrmsDomainSelect");

		lib.sendValueAndEnter("hrmsSkillField",
				getConfigValues("hrmsDomainvalue"));

		lib.clearAndEnterValue("hrmsNonTotalExp",
				getConfigValues("hrmsTotalYearsExp"));

		lib.clearAndEnterValue("hrmsNonTotalMonth",
				getConfigValues("hrmsTotalMonExp"));

		lib.clickAnElement("hrmsNonSupportYes");

		lib.clickAnElement("hrmsSkillSetSave");

		lib.clickAnElement("hrmsRegisterButton");

		String successfulmsg = lib.getValue("hrmsSuccessfulMsg");

		Assert.assertEquals( 
				successfulmsg, "Congratulations!");
		System.out.println(successfulmsg+ " is present");

	}

	public void clickOnEmployeesSideMenuAndChooseActiveEmployee() throws IOException, InterruptedException{
		lib.clickAnElement("hrmsSideMenuEmployees");
		lib.verifyPageTitle(
				getConfigValues("hrmsEmployeePageTitle"));
		lib.selectDropdown("hrmsFilterEmployees",
				"Value", getConfigValues("hrmsEmergencyAddress"));

	}

	public void searchEmployee(String employeename)
	{
		lib.sendValueAndEnter("hrmsSearchEmploye", employeename);
		lib.clickAnElement("hrmsClickEmployee");
	}

	public void validateExistingUser() throws IOException
	{
		try {
			String peremail = lib.getValue("hrmspersonEmail");
			if(getConfigValues("hrmsPersonalEmailValue").equals(peremail))
			{
				System.out.println(peremail+ " is registered in admin site");
			}
			lib.clickAnElement("hrmsHRFamily");
			String hrfamilyname = lib.getValue("hrmsHRFamilyName");
			if(getConfigValues("hrmsFamilyNam").equals(hrfamilyname))
			{
				System.out.println(hrfamilyname+ " is registered in admin site");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error on validate existing user "+e.getCause());
		}

	}
}