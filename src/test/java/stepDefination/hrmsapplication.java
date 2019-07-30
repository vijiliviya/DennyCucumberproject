package stepDefination;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.junit.Assert;

import com.app.commonFunctionsLibrary.BaseUtil;
import com.app.commonFunctionsLibrary.CommonFunctions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.HRMS_Page;
import pages.PMS_Page;

public class hrmsapplication extends BaseUtil {

	static String confvalue;
	static FileInputStream con;
	static Properties conf = new Properties();
	private BaseUtil base;
	HRMS_Page hrmspage = new HRMS_Page(base);
	PMS_Page pmspage = new PMS_Page(base);
	CommonFunctions lib = new CommonFunctions();

	/*
	 * To read the values from config properties
	 *
	 */
	public String getConfigValues(String key) throws IOException {
		String object = System.getProperty("user.dir") + 
				"\\src\\test\\resources\\config\\config.properties";
		con = new FileInputStream(object);
		conf.load(con);
		confvalue = conf.getProperty(key);
		return confvalue;
	}

	@Given("^Open chrome and start HRMS application$")
	public void open_chrome_and_start_HRMS_application() throws Throwable {
		/*hrmspage.launchUrl("HRMSURL");
		String actLogValue = hrmspage.getLoginButton();
		Assert.assertEquals("Login", actLogValue);*/
	}

	@When("^Login as HR on HRMS application$")
	public void login_as_HR_on_HRMS_application() throws IOException 
	{
		/*hrmspage.sendCredentials("pmsHrUserName");
		hrmspage.clickLoginButton();*/
	}

	@Then("^HR should able to see the HRMS home page$")
	public void hr_should_able_to_see_the_HRMS_home_page() throws Throwable {

		/*pmspage.verifypageTitle("hrmsAdminPageTitle");*/

	}

	@And("^create a new user and verify$")
	public void create_a_new_user_and_verify() throws Throwable {
		/*hrmspage.clickUserTab();

		String actval = lib.getValue("hrmsUserTitle");

		Assert.assertEquals("Users", actval);*/

	}

	@Then("^HR should able to see created user$")
	public void hr_should_able_to_see_created_user() throws Throwable 
	{

		/*hrmspage.createNewUserAndVerify();

		hrmspage.logoutHrms();*/
	}

	@And("^Login as created user and update the details$")
	public void Login_as_created_user_and_update_the_details() throws Throwable {

		/*hrmspage.launchUrl("HRMSURL");
		String actLogValue = hrmspage.getLoginButton();
		Assert.assertEquals("Login", actLogValue);
		hrmspage.sendCredentials("hrmsEmailUser");
		hrmspage.clickLoginButton();

		pmspage.verifypageTitle("hrmsRegisteredPageTitle");

		hrmspage.updateBasicDetails();
		hrmspage.updateAcademic();
		hrmspage.updateEmployment();
		hrmspage.updateCertification();
		hrmspage.updateFamily();
		hrmspage.updateEmployeeDocuments();*/

	}

	@Then("^User should able to see the updated details$")
	public void User_should_able_to_see_the_updated_details() throws Throwable {

		/*hrmspage.updateEmployeeSkills
		(getConfigValues("hrmsSkillSet"));*/

	}

	@And("^Logout the application and login as HR$")
	public void Logout_the_application_and_login_as_HR()
	{
		/*hrmspage.logoutHrms();*/

	}

	@Then("^Verify the created employee details$")
	public void Verify_the_created_employee_details() throws InterruptedException, IOException
	{
		hrmspage.launchUrl("HRMSURL");

		String actLogValue = hrmspage.getLoginButton();
		Assert.assertEquals("Login", actLogValue);
		hrmspage.sendCredentials("pmsHrUserName");
		hrmspage.clickLoginButton();
		pmspage.verifypageTitle("hrmsAdminPageTitle");
		hrmspage.clickOnEmployeesSideMenuAndChooseActiveEmployee();
		hrmspage.searchEmployee(getConfigValues("hrmsEmailUser"));
		hrmspage.validateExistingUser();
		hrmspage.logoutHrms();

	}




}
