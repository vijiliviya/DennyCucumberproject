package pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.app.commonFunctionsLibrary.BaseUtil;
import com.app.commonFunctionsLibrary.CommonFunctions;

public class PMS_Page extends BaseUtil
{
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

	public PMS_Page(BaseUtil base) { 
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
	 * launch the pms site
	 */
	public void launchUrl(String url) throws InterruptedException, IOException {
		driver.get(getConfigValues(url));
	}

	/*
	 * get the page title
	 */
	public void verifypageTitle(String pagetitle) throws IOException{
		lib.verifyPageTitle(
				getConfigValues(pagetitle));
	}

	/*
	 * enter username and password
	 */
	public void sendCredentials(String username) throws IOException{
		lib.enterText("pmsUsername",getConfigValues(username));

		lib.enterText("saasPassword",
				getConfigValues("pmsPassword"));
	}
	
	
	/*
	 * Click on pms login
	 */
	public void clickLoginButton()
	{
		lib.clickAnElement
		("pmsLogin");
	}

	/*
	 * click on review initiation for employee review
	 */
	public void clickReviewInitiation()
	{
		lib.clickAnElement
		("pmsReviewInitiation");
	}

	/*
	 * get login button
	 */
	public String getLoginButton() throws IOException
	{
		String actelement = null;
		actelement = lib.getValue("pmsLogin");
		return actelement;
	}
	
	
	/*
	 * get employee name for review employee name
	 */
	public void clickInitiationReview(String expreview) throws IOException
	{
		lib.getInitiationReviewValues
		("pmsReviewInitiateTable",expreview);

	}
	
	/*
	 * close the browser
	 */
	public void closeBrowser() throws InterruptedException
	{
		lib.waitInSleep();
		lib.driver.close();
		lib.waitInSleep();
	}

	public void openBrowser() throws InterruptedException
	{
		System.setProperty(
				"webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver(); 
		//To maximize browser
		driver.manage().window().maximize();
		lib.waitInSleep();
	}

	public void clickToConfirmInitiateEmployee(String initiateemployeename){

		lib.clickToInitiateAnEmployee
		("pmsConfirmationTable", initiateemployeename);
	}

	/*
	 * get employee name from review logs
	 */
	public void verifyInitiatedEmployeeNameInReviewLogs(String logname)
	{
		try {
			WebElement logsTable = driver.findElement(By.xpath
					(getConfigValues("pmsReviewLogsTable")));
			List<WebElement>tablerows = logsTable.findElements(By.tagName("tr"));

			String actlogXpath= getConfigValues("reviewTableRows");
			for(int tr=1;tr<=tablerows.size(); tr++)
			{
				String lognames = driver.findElement(By.xpath(actlogXpath+tr+"]/td[2]")).getText();

				if(lognames.equals(logname))
				{
					Assert.assertEquals(lognames+ " is present in review logs", logname, lognames);
					break;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
	

	/*
	 * Click on pms logout screen
	 */
	public void clickOnLogout()
	{
		lib.clickAnElement("pmsLogoutScreen");
		lib.waitForElementUsingPresence("pmsLogoutToolTip");
		lib.clickAnElement("pmsLogoutToolTip");
	}

	/*
	 * click on pms submit button
	 */
	public void clickOnSubmitButton()
	{
		lib.clickAnElement("organizationSubmit");
	}


	/*
	 * click on review logs
	 */
	public void clickOnReviewLogs(String logname) throws IOException
	{
		String actlog = null;
		lib.clickAnElement("pmsReviewLogs");

		WebElement logsParent = driver.findElement(
				By.xpath(getConfigValues("reviewlogsParent")));

		List<WebElement> logsList = logsParent.findElements(By.tagName("li"));

		String actxpath = getConfigValues("reviewLogsActXpath");
		for(int li=1; li<=logsList.size(); li++)
		{
			actlog = driver.findElement(By.xpath(actxpath+li+"]/a/span")).getText();

			if(actlog.equals(logname))
			{
				driver.findElement(By.xpath(actxpath+li+"]/a/span")).click();
				break;
			}
		}

	}



	/*
	 * click on employee review and verify the employee review page
	 */
	public void clickOnEmployeeReview()
	{
		lib.clickAnElement("pmsEmployeeReview");
		lib.verifyPageTitle("Employee Review");
	}



	/*
	 * select the filter to list the employee
	 */
	public void selectEmployeeReviewFilter(String Option, String data) throws InterruptedException
	{
		lib.selectDropdown("pmsEmployeeReviewListFilter", Option, data);
	}

	/*
	 * get employee name from review logs
	 */
	public void clickOnStartForInitiatedEmployee(String initiatedemployeename)
	{
		
		lib.getInitiatedEmployeeNameForReview
		("pmsStartInitiateTable", initiatedemployeename);
	}
	
	/*
	 * click on No button for level 1 review update
	 */
	public void clickAssignWorkflowNo()
	{
		lib.clickAnElement("pmsAssignWorkflowNo");
	}
	
	/*
	 * click on view button on employee review template
	 */
	public void clickAssignWorkflowView()
	{
		lib.clickAnElement("pmsAssignWorkflowView");
		
	}
	
	/*
	 * click on save button to save the employee template 
	 */
	public void clickOnConfirmationTemplateSave() throws InterruptedException
	{
		lib.clickAnElement("pmsConfirmationTemplateSave");
		lib.waitInSleep();
	}
}
		
			
	
