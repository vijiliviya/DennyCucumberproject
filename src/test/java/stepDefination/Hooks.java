package stepDefination;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import com.app.commonFunctionsLibrary.BaseUtil;
import com.cucumber.listener.Reporter;
import com.google.common.io.Files;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks extends BaseUtil{

	private BaseUtil base;
	public Hooks() //BaseUtil base
	{
		this.base = base;
	}

	@Before
	public void InitializeTest()
	{

		System.setProperty(
				"webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver(); 
		//To maximize browser
		driver.manage().window().maximize();

		System.out.println("Chrome browser started...");
		//Implicit wait
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	//	@After
	//	public void TearDownTest() throws InterruptedException
	//	{
	//		Thread.sleep(6000);
	//		driver.close();
	//		//driver.quit();
	//	}
	//	
	@After()
	public void afterScenario(Scenario scenario) throws IOException{
		try
		{
			if (scenario.isFailed()) 

			{
				//This takes a screenshot from the driver at save it to the specified location
				String screenshotName = scenario.getName().replaceAll(" ", "_");

				File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); 

				DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");	   
				Date date = new Date(); 	 
				String ReportName= screenshotName+"_"+dateFormat.format(date);		
				//		reports=new ExtentReports("..\\POMWithBDDFramework\\Reports\\" + ReportName + ".html");
				//Building up the destination path for the screenshot to save
				//Also make sure to create a folder 'screenshots'
				File destinationPath = new File(System.getProperty("user.dir") + "/screenshot/" + ReportName + ".png");

				//Copy taken screenshot from source location to destination location
				Files.copy(sourcePath, destinationPath); 
				//This attach the specified screenshot to the test
				Reporter.addScreenCaptureFromPath(destinationPath.toString());
			}
		}

		catch(Exception e){
			scenario.write("WARNING. Failed to take screenshot with following exception : "+e.getMessage());

		}

		System.out.println("after");
		driver.close();
	}




}
