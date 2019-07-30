package stepDefination;

import java.io.IOException;

import com.app.commonFunctionsLibrary.BaseUtil;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.PMS_Page;
import pages.SAAS_Page;

public class SAASApplication extends BaseUtil { 

	private BaseUtil base;
	SAAS_Page saaspage = new SAAS_Page(base);
	PMS_Page pmspage = new PMS_Page(base);

	@Given("^Open chrome and start application$")
	public void open_chrome_and_start_application() throws Throwable {

		saaspage.launchUrl("SAASURL");
	}

	@When("^User enter admin username and password$")
	public void user_enter_admin_username_and_password() throws Throwable {

		saaspage.sendCredentials();
		saaspage.clickLoginButton();
		saaspage.pageTitle();
	}
	@And("^User able to create new organization$")
	public void User_able_to_create_new_organization() throws Throwable {

		saaspage.createOrganization();
		saaspage.clickSubmit();
	}

	@Then("^user should able to see the created organization$")
	public void user_should_able_to_see_the_created_organization() throws Throwable {
		saaspage.clickOrganizationView();
		saaspage.validateOrganizationName();
	}

}
