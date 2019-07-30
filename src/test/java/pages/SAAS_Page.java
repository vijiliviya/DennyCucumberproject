package pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.app.commonFunctionsLibrary.BaseUtil;
import com.app.commonFunctionsLibrary.CommonFunctions;

import junit.framework.Assert;

public class SAAS_Page extends BaseUtil {  

	public WebDriverWait wait = new WebDriverWait(driver, 40);
	private BaseUtil base;
	static String value = "";
	Properties val =new Properties();
	static String confvalue;
	static FileInputStream con;
	static Properties conf = new Properties();
	CommonFunctions lib = new CommonFunctions(); 

	public SAAS_Page(BaseUtil base) { 
		// TODO Auto-generated constructor stub
		this.base = base;
	}

	/*
	 *To read the values from config properties
	 *
	 */
	public String getConfigValues(String key) throws IOException {
		String object = System.getProperty(
				"user.dir") + "\\src\\test\\resources\\config\\config.properties";
		con = new FileInputStream(object);
		conf.load(con);
		confvalue = conf.getProperty(key);
		return confvalue;
	}

	public void launchUrl(String url) throws InterruptedException, IOException {
		driver.get(getConfigValues(url));
		lib.waitForElementToBeClickable("saasLogin");
	}



	public void sendCredentials() throws IOException
	{

		lib.enterText("saasUsername",
				getConfigValues("usernameValue"));
		lib.enterText("saasPassword",
				getConfigValues("passwordValue"));

	}

	public void clickLoginButton()
	{
		lib.clickAnElement("saasLogin");

	}

	public void pageTitle() throws IOException{
		lib.verifyPageTitle(
				getConfigValues("saasPageTitle"));
	}

	public void createOrganization() throws IOException, InterruptedException
	{

		lib.clickAnElement("addOrganization");
		lib.enterText("enterOrganization",
				getConfigValues("organizationValue"));
		lib.enterText("enterOrgnaizationLogo",
				getConfigValues("organizationLogo"));
		lib.enterText("enterEmailId",
				getConfigValues("organizationEmailId"));
		lib.enterText("enterSecondaryEmailId",
				getConfigValues("organizationSecondaryEmailId"));
		lib.enterText("enterContactNumber",
				getConfigValues("organizationContactValue"));
		lib.enterText("enterPhoneNumber",
				getConfigValues("organizationPhoneValue"));
		lib.enterText("enterContactPerson",
				getConfigValues("organizationContactPerson"));
		lib.enterText("enterAddress1",
				getConfigValues("orgainzationAddress1"));
		lib.enterText("enterAddress2",
				getConfigValues("orgainzationAddress2"));
		lib.enterText("enterZipCode",
				getConfigValues("orgainzationZipCodeValue"));

		lib.selectDropdown("chooseOrganizationCountry",
				"value", getConfigValues("organizationCountryValue"));
		lib.selectDropdown("chooseOrganizationState",
				"Value", getConfigValues("organizationStateValue"));
		lib.enterText("enterOrganizationCity",
				getConfigValues("organizationCityValue"));

	}

	public void clickSubmit() throws InterruptedException
	{
		lib.clickAnElement(
				"organizationSubmit");
		lib.waitForInvisibilityElement(
				"organizationSubmit");

	}

	public void clickOrganizationView()
	{
		lib.clickAnElement(
				"clickOrganization");
		lib.clickAnElement(
				"clickview");

	}

	public void validateOrganizationName() throws IOException
	{
		lib.getOrgnizationValue(
				"organizationTable", getConfigValues("organizationValue"));
	}




}
