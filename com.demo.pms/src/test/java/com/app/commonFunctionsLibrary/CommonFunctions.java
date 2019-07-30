package com.app.commonFunctionsLibrary;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonFunctions extends stepDefination.Hooks{
	
	public CommonFunctions() { //BaseUtil base
		//super(base);
		// TODO Auto-generated constructor stub
	}

	
	static Properties object = null;
	
	Properties prop=new Properties();

	public WebDriverWait wait = new WebDriverWait(driver, 120);
	public WebElement element = null;

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

	public String getlocators(String key) throws IOException {
		String locators = "";
		String object = System.getProperty(
				"user.dir") + "\\src\\test\\resources\\config\\locators.properties";
		FileInputStream file = new FileInputStream(object);
		prop.load(file);
		locators = prop.getProperty(key);
		return locators;

	}

	public String[] readProperties(String locatorKey) {
		String[] locatorMethodName = null;
		try {
			locatorKey = locatorKey.replace(" ", "").replace(":", "");
			String objectValue = object.getProperty(locatorKey);
			locatorMethodName = objectValue.split("#");
		} catch (Exception e) {

		}
		return locatorMethodName;
	}


	// Method To Enter Text
	public void enterText(String locatorKey, String data) {
		try {
			WebElement element = getWebElementWithWait(locatorKey);
			waitForElementUsingPresence(locatorKey);
			element.sendKeys(data);

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Click an Element
	public void clickAnElement(String locatorKey) {
		WebElement element = null;
		try {
			if (object.getProperty(locatorKey) != null && object.getProperty(locatorKey).contains("#")) {
				element = getWebElementWithWait(locatorKey);
			} else {
				element = getWebElementWithWait(locatorKey);
			}
			wait.until(ExpectedConditions.elementToBeClickable(element));
			element.click();

		} catch (Exception e) {
			try {
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].click();", element);

			} catch (Exception e1) {
				System.out.println(e);

			}

		}
	}


	public WebElement getWebElementWithWait(String locatorKey) throws InterruptedException {

		WebElement element = null;
		String locatorMethod = null;
		String locatorValue = null;

		try {
			String[] locatorMethodName = readProperties(locatorKey);
			locatorMethod = locatorMethodName[0];
			locatorValue = locatorMethodName[1];
		} catch (Exception e) {
			System.out.println(e);
		}
		try {
			switch (locatorMethod) {
			case "id":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
				element = driver.findElement(By.id((locatorValue)));
				break;
			case "name":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
				element = driver.findElement(By.name((locatorValue)));
				break;
			case "class":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(locatorValue)));
				element = driver.findElement(By.className((locatorValue)));
				break;
			case "linkText":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(locatorValue)));
				element = driver.findElement(By.linkText((locatorValue)));
				break;
			case "partiallinkText":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(locatorValue)));
				element = driver.findElement(By.partialLinkText((locatorValue)));
				break;
			case "tagname":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(locatorValue)));
				element = driver.findElement(By.tagName((locatorValue)));
				break;
			case "css":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locatorValue)));
				element = driver.findElement(By.cssSelector((locatorValue)));
				break;
			case "xpath":
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
				element = driver.findElement(By.xpath((locatorValue)));
				break;

			default:
				break;
			}
		} catch (NoSuchWindowException e) {
			System.out.println("Window Already closed and elment is not visible further ...");
		} catch (Exception e) {
			System.out.println(e);
		}
		return element;
	}

	public void waitForElementUsingPresence(String locatorKey) {
		WebElement element = null;
		String locatorMethod = null;
		String locatorValue = null;
		try {
			String[] locatorMethodName = readProperties(locatorKey);
			locatorMethod = locatorMethodName[0];
			locatorValue = locatorMethodName[1];
		} catch (Exception e) {
			System.out.println(e);
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
			System.out.println(e);
		}
	}


}
