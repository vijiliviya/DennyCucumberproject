package stepDefination;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.app.commonFunctionsLibrary.BaseUtil;
import com.app.commonFunctionsLibrary.CommonFunctions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.SAAS_Page;

public class SAASApplication extends Hooks { //BaseUtil

	private Hooks base;
	SAAS_Page saaspage = new SAAS_Page();  //base
	
	public SAASApplication() { //BaseUtil base
		// TODO Auto-generated constructor stub
		//this.base = base;
	}

	@Given("^Open chrome and start application$")
	public void open_chrome_and_start_application() throws Throwable {
		System.out.println("open_chrome_and_start_application");
		/*String url = lib.getProperty("SAASURL");	
		System.out.println("url  :"+url);*/
		saaspage.launchUrl("SAASURL");
	}

	@When("^User enter admin username and password$")
	public void user_enter_admin_username_and_password() throws Throwable {

		saaspage.sendCredentials();
		//saaspage.clickLoginButton();
		Thread.sleep(4000);

	}

	@Then("^user should be able to login successfully$")
	public void user_should_be_able_to_login_successfully() throws Throwable {
		//driver.close();
		System.out.println("User successfully on the home page");
	}


}
