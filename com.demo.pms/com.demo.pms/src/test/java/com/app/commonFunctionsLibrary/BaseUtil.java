package com.app.commonFunctionsLibrary;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.Before;

public class BaseUtil {
	public String  StepInfo;
	
	public static WebDriver driver;
	
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

}
