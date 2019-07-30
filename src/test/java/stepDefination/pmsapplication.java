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
import pages.PMS_Page;

public class pmsapplication extends BaseUtil {

	static String confvalue;
	static FileInputStream con;
	static Properties conf = new Properties();
	private BaseUtil base;
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

	@Given("^Open chrome and start PMS application$")
	public void open_chrome_and_start_PMS_application() throws Throwable {
		/*pmspage.launchUrl("PMSURL");
		String actele = pmspage.getLoginButton();
		Assert.assertEquals("Login", actele);*/

	}

	@When("^Login as HR on PMS application$")
	public void login_as_HR_on_PMS_application() throws Throwable {
		/*pmspage.sendCredentials("pmsHrUserName");

		pmspage.clickLoginButton();*/
	}

	@Then("^HR should able to see the PMS home page$")
	public void hr_should_able_to_see_the_PMS_home_page() throws Throwable {
		/*pmspage.verifypageTitle("pmsPageTitle");*/
	}

	@And("^Initiate an employee on confirmation review page$")
	public void initiate_an_employee_on_confirmation_review_page() throws Throwable {
		/*pmspage.clickReviewInitiation();

		String actval = lib.getValue("pmsReviewInitiatePage");

		Assert.assertEquals("Initiate Reviews", actval);
		pmspage.clickInitiationReview(getConfigValues
				("pmsConfirmationReview"));

		pmspage.clickToConfirmInitiateEmployee(getConfigValues
				("pmsPrabhToInitiate"));
		pmspage.clickOnSubmitButton();*/
	}

	@Then("^HR should able to initiate a employee successfully$")
	public void HR_should_able_to_initiate_a_employee_successfully() throws Throwable {

		/*pmspage.clickOnReviewLogs(getConfigValues("pmsReviewLogsName"));

		String actval = lib.getValue("pmsConfirmationReviewLogPageTitle");

		Assert.assertEquals("Confirmation Review Logs", actval);

		pmspage.verifyInitiatedEmployeeNameInReviewLogs(
				getConfigValues("pmsPrabhToInitiate"));

		pmspage.clickOnLogout();
		pmspage.closeBrowser();*/

	}

	@When("^Reviewer login into pms application and initiate reviewee to level 1$")
	public void Reviewer_login_into_pms_application_and_initiate_reviewee_to_level_3() throws InterruptedException, IOException
	{
		pmspage.openBrowser();
		pmspage.launchUrl("PMSURL");
		String actele = pmspage.getLoginButton();
		Assert.assertEquals("Login", actele);
		pmspage.sendCredentials
		(getConfigValues("pmsRdharamalingamUN"));
		pmspage.clickLoginButton();

		pmspage.verifypageTitle("pmsPageTitle");

		pmspage.clickOnEmployeeReview();

		pmspage.selectEmployeeReviewFilter("Value",
				(getConfigValues("pmsEmployeeReviewFilterOption")));
		
		pmspage.clickOnStartForInitiatedEmployee
		(getConfigValues("pmsPrabhToInitiate"));

		pmspage.clickAssignWorkflowNo();
		
		pmspage.clickAssignWorkflowView();
		
		pmspage.clickOnConfirmationTemplateSave();
		

	}

	@Then("^level 1 should able to rating for reviewee and submit$")
	public void level_1_should_able_to_rating_for_reviewee_and_submit() throws InterruptedException, IOException
	{
		
		pmspage.selectEmployeeReviewFilter("Value",
				(getConfigValues("pmsFilterWithPendingReviews")));
		
		pmspage.clickInitiationReview(getConfigValues
				("pmsConfirmationReview"));
		
		pmspage.clickOnStartForInitiatedEmployee
		(getConfigValues("pmsPrabhToInitiate"));
		
	}

	@And("^Reviewer save the reviewees template$")
	public void reviewer_save_the_reviewees_template() throws Throwable {
		System.out.println("Reviewer should able to save the reviewees template");
	}

}
