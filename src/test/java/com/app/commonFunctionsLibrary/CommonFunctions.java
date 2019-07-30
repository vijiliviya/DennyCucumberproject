package com.app.commonFunctionsLibrary;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
public class CommonFunctions extends BaseUtil{

	static Properties object = null;
	static boolean status= true;
	static String ActualTitle;
	static String locatorMethod = null;
	static String locatorValue = null;
	static String actvalues;
	String actelement;
	Properties prop=new Properties();
	public WebDriverWait wait = new WebDriverWait(driver, 30);

	public String getProperty(String key) throws IOException {
		String value = "";
		String propFileName = System.getProperty(
				"user.dir") + "\\src\\test\\resources\\config\\config.properties";
		FileInputStream file = new FileInputStream(propFileName);
		prop.load(file);
		value = prop.getProperty(key);

		return value;

	}

	/*
	 *To read locator attribute and attribute 
	 *values from locators properties
	 *
	 */
	public String[] readProperties(String locatorKey) {
		String[] locatorMethodName = null;
		try {
			String objectFileName = "src/test/resources/config/locators.properties";
			object = loadPropertiesFile(objectFileName);
			locatorKey = locatorKey.replace(" ", "").replace(":", "");
			String objectValue = object.getProperty(locatorKey);
			locatorMethodName = objectValue.split("#");
		} catch (Exception e) {
			System.out.println("Error on readProperties: "+e.getCause());
		}
		return locatorMethodName;
	}

	/**
	 * To load properties from application.properties file
	 * 
	 * @param propFilePath
	 * @return
	 */
	public Properties loadPropertiesFile(String propFilePath) {
		Properties properties = null;
		try {
			properties = new Properties();
			InputStream fis = new FileInputStream(propFilePath);
			properties.load(fis);
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

	/*
	 * Method to get the value
	 */
	public String getValue(String locatorKey) {
		try {
			waitInSleep();
			WebElement element = getWebElementWithWait(locatorKey);
			actelement=element.getText().trim();
		} catch (Exception e) {
			System.out.println("Error on get elements: "+e.getMessage());
		}
		return actelement;
	}


	/*
	 * Method to enter the text
	 */
	public void enterText(String locatorKey, String data) {
		try {
			WebElement element = getWebElementWithWait(locatorKey);
			element.sendKeys(data);
		} catch (Exception e) {
			System.out.println("Error on  enterText method: "+e.getCause());
		}
	}

	/*
	 * Method to enter the value and enter the key
	 */
	public void sendValueAndEnter(String locatorKey, String data) {
		try {
			WebElement element = getWebElementWithWait(locatorKey);
			element.sendKeys(data);
			element.sendKeys(Keys.ENTER);
			waitInSleep();
		} catch (Exception e) {
			System.out.println("Error on  sendValueAndEnter method: "+e.getCause());
		}
	}

	/*
	 * Method to enter the value and enter the key
	 */
	public void clearAndEnterValue(String locatorKey, String data) {
		try {
			WebElement element = getWebElementWithWait(locatorKey);
			element.sendKeys(data);
			element.clear();
			element.sendKeys(data);
			waitInSleep();
		} catch (Exception e) {
			System.out.println("Error on  sendValueAndEnter method: "+e.getCause());
		}
	}

	/*
	 * Method to verify page title
	 */
	public void verifyPageTitle(String ExpectedTitle) {
		try{
			waitInSleep();
			wait.until(ExpectedConditions.titleContains(ExpectedTitle));
			ActualTitle = driver.getTitle();
			System.out.println(""
					+ "Expected title: "+ExpectedTitle+" is matching with "+"Actual title: "+ActualTitle);
			Assert.assertEquals(ExpectedTitle, ActualTitle);
		}catch (Exception e){
			System.out.println(""
					+ "Expected title: "+ExpectedTitle+" is not matching with "+"Actual title: "+ActualTitle);
			Assert.assertNotEquals(ExpectedTitle, ActualTitle);
		}

	}

	/*
	 * Click an Element
	 */
	public boolean clickAnElement(String locatorKey) {
		WebElement element = null;
		boolean status = false;
		try {
			if (object.getProperty(locatorKey) != null && object.getProperty(locatorKey).contains("#")) {
				element = getWebElementWithWait(locatorKey);
			} else {
				element = getWebElementWithWait(locatorKey);
			}
			wait.until(ExpectedConditions.elementToBeClickable(element));
			status = element.isDisplayed();
			Assert.assertTrue(status);
			element.click();
			waitInSleep();
			System.out.println(locatorKey+" is clicked");
			return status;

		} catch (Exception e) {
			try {
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].click();", element);
				System.out.println(locatorKey+" is clicked by java script");
				waitInSleep();
			} catch (Exception e1) {
				System.out.println("Error on clickAnElement: "+e.getCause());

			}
		}
		return status;
	}

	/*
	 * wait for locator
	 */
	public WebElement getWebElementWithWait(String locatorKey) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebElement element=null;
		String locatorMethod = null;
		String locatorValue = null;
		try {
			String[] locatorMethodName = readProperties(locatorKey);
			locatorMethod = locatorMethodName[0];
			locatorValue = locatorMethodName[1];
		} catch (Exception e) {
			System.out.println("error on config properties:"+e);
		}
		try {

			switch (locatorMethod) {
			case "id":
				element = driver.findElement(By.id(locatorValue));
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locatorValue)));
				System.out.println(locatorValue+ " is present ");
				break;
			case "name":

				element = driver.findElement(By.name(locatorValue));
				wait.until(ExpectedConditions.presenceOfElementLocated(By.name(locatorValue)));
				System.out.println(locatorValue+ " is present ");
				break;
			case "class":
				element = driver.findElement(By.className(locatorValue));
				wait.until(ExpectedConditions.presenceOfElementLocated(By.className(locatorValue)));
				System.out.println(locatorValue+ " is present ");
				break;
			case "linkText":
				element = driver.findElement(By.linkText(locatorValue));
				wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(locatorValue)));
				System.out.println(locatorValue+ " is present ");
				break;
			case "partiallinkText":
				element = driver.findElement(By.partialLinkText(locatorValue));
				wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(locatorValue)));
				System.out.println(locatorValue+ " is present ");
				break;
			case "tagname":
				element = driver.findElement(By.tagName(locatorValue));
				wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(locatorValue)));
				System.out.println(locatorValue+ " is present ");
				break;
			case "css":
				element = driver.findElement(By.cssSelector(locatorValue));
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locatorValue)));
				System.out.println(locatorValue+ " is present ");
				break;
			case "xpath":
				element = driver.findElement(By.xpath(locatorValue));
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locatorValue)));
				System.out.println(locatorValue+ " is present ");
				break;

			default:
				break;
			}
		} catch (NoSuchWindowException e) {
			System.out.println("Window Already closed and elment is not visible further ...");
		} catch (Exception e) {
			System.out.println("Error on  getWebElementWithWait method: "+e.getCause());
		}
		return element;
	}

	/*
	 * wait for presence of element
	 */
	public void waitForElementUsingPresence(String locatorKey) {
		WebElement element = null;
		String locatorMethod = null;
		String locatorValue = null;
		try {
			String[] locatorMethodName = readProperties(locatorKey);
			locatorMethod = locatorMethodName[0];
			locatorValue = locatorMethodName[1];
		} catch (Exception e) {
			System.out.println("Error on waitForElementUsingPresence: "+e.getCause());
			System.exit(1);
		}
		try {
			switch (locatorMethod) {
			case "id":
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locatorValue)));
				break;
			case "name":
				wait.until(ExpectedConditions.presenceOfElementLocated(By.name(locatorValue)));
				break;
			case "class":
				wait.until(ExpectedConditions.presenceOfElementLocated(By.className(locatorValue)));
				break;
			case "linkText":
				wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(locatorValue)));
				break;
			case "partiallinkText":
				wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(locatorValue)));
				break;
			case "tagname":
				wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(locatorValue)));
				break;
			case "css":
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locatorValue)));
				break;
			case "xpath":
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locatorValue)));
				break;

			default:
				break;
			}
		} catch (Exception e) {
			System.out.println(
					"Error on waitForElementUsingPresence: "+e.getCause());
		}
	}

	/*
	 * wait for presence of element
	 */
	public void waitForInvisibilityElement(String locatorKey) {
		WebElement element = null;
		String locatorMethod = null;
		String locatorValue = null;
		try {
			String[] locatorMethodName = readProperties(locatorKey);
			locatorMethod = locatorMethodName[0];
			locatorValue = locatorMethodName[1];
		} catch (Exception e) {
			System.out.println("Error on waitForElementUsingPresence: "+e.getCause());
			System.exit(1);
		}
		try {
			switch (locatorMethod) {
			case "id":
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(locatorValue)));
				System.out.println(locatorMethod+" has been invisible");
				break;
			case "name":
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.name(locatorValue)));
				System.out.println(locatorMethod+" has been invisible");
				break;
			case "class":
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className(locatorValue)));
				System.out.println(locatorMethod+" has been invisible");
				break;
			case "linkText":
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.linkText(locatorValue)));
				System.out.println(locatorMethod+" has been invisible");
				break;
			case "partiallinkText":
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.partialLinkText(locatorValue)));
				System.out.println(locatorMethod+" has been invisible");
				break;
			case "tagname":
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.tagName(locatorValue)));
				System.out.println(locatorMethod+" has been invisible");
				break;
			case "css":
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(locatorValue)));
				System.out.println(locatorMethod+" has been invisible");
				break;
			case "xpath":
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locatorValue)));
				System.out.println(locatorMethod+" has been invisible");
				break;

			default:
				break;
			}
		} catch (Exception e) {
			System.out.println(
					"Error on wait for invisibility of element: "+e.getCause());
		}
	}

	/*
	 * wait for element to be clicked
	 */
	public void waitForElementToBeClickable(String locatorKey) {
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, 30);
		String locatorMethod = null;
		String locatorValue = null;
		try {
			String[] locatorMethodName = readProperties(locatorKey);
			locatorMethod = locatorMethodName[0];
			locatorValue = locatorMethodName[1];
		} catch (Exception e) {
			System.out.println("Error on elementToBeClickable: "+e.getCause());
			System.exit(1);
		}
		try {
			switch (locatorMethod) {
			case "id":
				wait.until(ExpectedConditions.elementToBeClickable(By.id(locatorValue)));
				System.out.println(locatorValue+" is present ");
				break;
			case "name":
				wait.until(ExpectedConditions.elementToBeClickable(By.name(locatorValue)));
				System.out.println(locatorValue+" is present ");
				break;
			case "class":
				wait.until(ExpectedConditions.elementToBeClickable(By.className(locatorValue)));
				System.out.println(locatorValue+" is present ");
				break;
			case "linkText":
				wait.until(ExpectedConditions.elementToBeClickable(By.linkText(locatorValue)));
				System.out.println(locatorValue+" is present ");
				break;
			case "partiallinkText":
				wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(locatorValue)));
				System.out.println(locatorValue+" is present ");
				break;
			case "tagname":
				wait.until(ExpectedConditions.elementToBeClickable(By.tagName(locatorValue)));
				System.out.println(locatorValue+" is present ");
				break;
			case "css":
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(locatorValue)));
				System.out.println(locatorValue+" is present ");
				break;
			case "xpath":
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorValue)));
				System.out.println(locatorValue+" is present ");
				break;

			default:
				break;
			}
		} catch (Exception e) {
			System.out.println(
					"Error on waitForElementUsingPresence: "+e.getMessage());
		}
	}

	/*
	 * get organization column values
	 */
	public void getOrgnizationValue(String locatorKey, String expvalue)
	{
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, 30);
		String locatorMethod = null;
		String locatorValue = null;

		try {
			String[] locatorMethodName = readProperties(locatorKey);
			locatorMethod = locatorMethodName[0];
			locatorValue = locatorMethodName[1];
		} catch (Exception e) {
			System.out.println("Error on waitForElementUsingPresence: "+e.getCause());
		}

		try {
			switch (locatorMethod) {
			case "xpath":
				element = driver.findElement(By.xpath(locatorValue));
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locatorValue)));
				List<WebElement> trList = element.findElements(By.tagName("tr"));

				for(int row=1; row<=trList.size(); row++)
				{
					List<WebElement> tdList = trList.get(row).findElements(By.tagName("td"));

					actvalues =  tdList.get(0).getText().trim();
					if(actvalues.equals(expvalue))
					{
						System.out.println
						("Created "+actvalues+" value is present in organization column");
						break;
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Assert.assertEquals(actvalues, expvalue);
			System.out.println("error on organization table: "+e.getMessage());
		}
	}

	/*
	 * get initiation reviews values
	 */
	public void getInitiationReviewValues(String locatorKey, String expvalue)
	{
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, 30);
		String locatorMethod = null;
		String locatorValue = null;
		String tdList = null;

		try {
			String[] locatorMethodName = readProperties(locatorKey);
			locatorMethod = locatorMethodName[0];
			locatorValue = locatorMethodName[1];
		} catch (Exception e) {
			System.out.println("Error on waitForElementUsingPresence: "+e.getCause());
		}

		try {
			switch (locatorMethod) {
			case "xpath":
				element = driver.findElement(By.xpath(locatorValue));
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locatorValue)));
				List<WebElement> initiList = element.findElements(By.tagName("a"));

				for(int row=0; row<=initiList.size(); row++)
				{
					tdList = initiList.get(row).getText().trim();


					if(tdList.equals(expvalue))
					{
						waitInSleep();
						initiList.get(row).click();
						System.out.println(tdList+" value is present in review initiation page");
						break;
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Assert.assertEquals(tdList, expvalue);
			System.out.println("error on  review initiate table: "+e.getMessage());
		}
	}


	/**
	 * Verify RadioButton or CheckBox is selected
	 */
	public void verifyRadioButtonOrCheckBoxIsSelected(String locatorKey) {
		try {
			WebElement element = getWebElementWithWait(locatorKey);
			if (element.isSelected()) 
			{
				System.out.println(element+" is selected");
			} 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}


	/**
	 * Click RadioButton or CheckBox
	 */
	public void clickRadioButtonOrCheckBox(String locatorKey) {
		try {
			WebElement element = getWebElementWithWait(locatorKey);
			if (!element.isSelected()) {
				element.click();
				System.out.println(element+" is selecting...");
			} 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void waitInSleep() throws InterruptedException
	{
		Thread.sleep(5000);
	}


	/*
	 * Clicking on check box to initiate
	 */
	public void clickToInitiateAnEmployee(String locatorKey, String empname )
	{
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, 30);
		String locatorMethod = null;
		String locatorValue = null;
		String actnames=null;
		boolean checstatus;

		try {
			String[] locatorMethodName = readProperties(locatorKey);
			locatorMethod = locatorMethodName[0];
			locatorValue = locatorMethodName[1];
		} catch (Exception e) {
			System.out.println
			("Error on getting locator values "+e.getCause());
		}

		try {
			switch (locatorMethod) {
			case "xpath":
				element = driver.findElement(By.xpath(locatorValue));
				wait.until(ExpectedConditions.presenceOfElementLocated
						(By.xpath(locatorValue)));
				List<WebElement> iniTableRow = element.findElements
						(By.tagName("tr"));

				for(int row=1; row<=iniTableRow.size(); row++)
				{
					WebElement inicolname = driver.findElement(
							By.xpath(locatorValue+"/tr["+row+"]/td[2]"));

					actnames = inicolname.getText();

					if(actnames.equals(empname))
					{

						WebElement chebox = driver.findElement(
								By.xpath(locatorValue+"/tr["+row+"]/td[6]"));

						checstatus =chebox.isSelected();

						if(checstatus==false)
						{
							chebox.click();
						}
						break;
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Assert.assertEquals(actnames, empname);
			System.out.println("error on  review initiate table: "+e.getMessage());
		}

	}

	/**
	 * Select drop down value
	 * @throws InterruptedException 
	 */
	public void selectDropdown(String locatorKey, String Option, String data) throws InterruptedException {
		WebElement element = getWebElementWithWait(locatorKey);
		Select sel = new Select(element);
		try {
			if (Option.equalsIgnoreCase("VisibleText")) {
				sel.selectByVisibleText(data);

			} else if (Option.equalsIgnoreCase("Value")) {
				sel.selectByValue(data);
			} else if (Option.equalsIgnoreCase("Index")) {
				int index = Integer.parseInt(data);
				sel.selectByIndex(index);
			}
			waitInSleep();
			System.out.println("Select value from " + locatorKey + " Listbox "+ data +
					"Expected value " + data + " is selected in the listbox");
		} catch (Exception e) {
			System.out.println("Select value from " + locatorKey + " Listbox "+ data +
					"Expected value " + data + " is not present in the listbox");
		}
	}



	/*
	 * click on start button for initiated employees
	 */
	public void getInitiatedEmployeeNameForReview(String locatorKey, String initiatedemployeename)
	{
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, 30);
		String locatorMethod = null;
		String locatorValue = null;
		String actnames=null;

		try {
			String[] locatorMethodName = readProperties(locatorKey);
			locatorMethod = locatorMethodName[0];
			locatorValue = locatorMethodName[1];
		} catch (Exception e) {
			System.out.println
			("Error on getting locator values "+e.getCause());
		}

		try {
			switch (locatorMethod) {
			case "xpath":
				element = driver.findElement(By.xpath(locatorValue));
				wait.until(ExpectedConditions.presenceOfElementLocated
						(By.xpath(locatorValue)));
				List<WebElement> iniTableRow = element.findElements
						(By.tagName("tr"));

				for(int row=1; row<=iniTableRow.size(); row++)
				{
					WebElement inicolname = driver.findElement(
							By.xpath(locatorValue+"/tr["+row+"]/td[2]"));

					actnames = inicolname.getText();

					if(actnames.equals(initiatedemployeename))
					{
						driver.findElement(By.xpath
								(locatorValue+"/tr["+row+"]/td[4]/button")).click();
						waitInSleep();

						break;
					}
				}
			}
		}catch (Exception e) {

			Assert.assertEquals(actnames, initiatedemployeename);
		}

	}


}
