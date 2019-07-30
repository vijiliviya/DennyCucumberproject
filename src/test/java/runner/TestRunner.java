package runner;

import java.io.File;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"},
		format = {"pretty","html:test-output"},
		features="features//hrmsapplication.feature"
		,glue={"stepDefination"}
		,monochrome=true
		)
public class TestRunner {
	@AfterClass
	public static void setup() throws IOException
	{
		Reporter.loadXMLConfig(new File("..\\com.demo.pms\\src\\test\\resources\\config\\extent-config.xml"));
		Reporter.setSystemInfo("User Name", "Cucumber User");
		Reporter.setSystemInfo("Application Name", "HRMS");
		Reporter.setSystemInfo("Environment", "Production");
		Reporter.setSystemInfo("Machine", "Windows 10" +"  "+ "64 Bit");
		Reporter.setSystemInfo("Selenium", "3.7.0");
		Reporter.setSystemInfo("Maven", "3.5.2");
		Reporter.setSystemInfo("Java Version", "1.8.0_151");
		Reporter.setTestRunnerOutput("Test Execution Cucumber Report");
}

}
