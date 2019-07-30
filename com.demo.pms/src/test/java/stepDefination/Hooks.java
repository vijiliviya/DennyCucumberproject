package stepDefination;



import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.app.commonFunctionsLibrary.BaseUtil;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks extends BaseUtil{

	public static WebDriver driver;

	/*public WebDriver getDriver() throws IOException{
		System.out.println("ChromeDriver Intialization");
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver(); 
		//To maximize browser
		driver.manage().window().maximize();
		//Implicit wait
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return driver;

	}*/
	private BaseUtil base;
	public Hooks() //BaseUtil base
	{
		//this.base = base;
	}
	@Before
	public void InitializeTest()
	{
		System.out.println("ChromeDriver Intialization");
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver(); 
		//To maximize browser
		driver.manage().window().maximize();
		//Implicit wait
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	@After
	public void TearDownTest()
	{
		System.out.println("in after hook");
	}
	


}
