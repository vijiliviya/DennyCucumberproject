package pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.app.commonFunctionsLibrary.BaseUtil;
import com.app.commonFunctionsLibrary.CommonFunctions;

import stepDefination.Hooks;

public class SAAS_Page extends Hooks {  //BaseUtil

	//WebDriver driver;
	//private BaseUtil base;
	Properties val =new Properties();
	Properties conf = new Properties();
	public String confi = "";
	CommonFunctions lib = new CommonFunctions();  //base


	// Dont change the constructor
	public SAAS_Page() { //BaseUtil base
		// TODO Auto-generated constructor stub
		//this.base = base;
	}


	/*
	 *To read the value form
	 * data properties
	 *
	 */
	public String getDataLocators(String key) throws IOException {
		String value = "";

		String object = System.getProperty(
				"user.dir") + "\\src\\test\\resources\\config\\data.properties";
		FileInputStream data = new FileInputStream(object);
		val.load(data);
		value = val.getProperty(key);
		return value;

	}

	/*
	 *To read the values from config properties
	 *
	 */
	public String getConfigValues(String key) throws IOException {


		String object = System.getProperty(
				"user.dir") + "\\src\\test\\resources\\config\\config.properties";
		FileInputStream con = new FileInputStream(object);
		conf.load(con);
		confi = conf.getProperty(key);
		return confi;

	}

	public void launchUrl(String url) throws InterruptedException {

		driver.get("http://saasadmin.encoress.com/login/");    //(conf.getProperty(url));
		Thread.sleep(4000);
		System.out.println("Hello I am ok");
	}

	public void sendCredentials()
	{
		System.out.println("Enter Credentials");
		lib.enterText("saasUsername", val.getProperty("usernameValue"));
		//lib.enterText("saaspassword", val.getProperty("usernameValue"));

	}

	/*public void clickLoginButton()
	{
		lib.clickAnElement("saasLogin");

	}*/


}
