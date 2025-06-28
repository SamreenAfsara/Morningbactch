package com.swaglabs.utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SmartFunctions {

	ReadLocator read;
	WebDriver driver;
	public SmartFunctions(WebDriver driver,String pagename)
	{
		this.driver=driver;
		read = new ReadLocator(pagename);
	}
	
	
	protected WebElement getElement(String elementname)
	{
		String locator=read.getLocator(elementname);
		String value[]=locator.split(":", 2);
		switch(value[0])
		{
		case "id": return driver.findElement(By.id(value[1])); 
		case "name":return driver.findElement(By.name(value[1]));
		case "classname":return driver.findElement(By.className(value[1]));
		case "linktext":return driver.findElement(By.linkText(value[1]));
		case "xpath":return driver.findElement(By.xpath(value[1]));
		case "css":return driver.findElement(By.cssSelector(value[1]));
		}
		
		return null;
	}
	
	protected List<WebElement> getElements(String elementname)
	{
		String locator=read.getLocator(elementname);
		String value[]=locator.split(":", 2);
		switch(value[0])
		{
		case "id": return driver.findElements(By.id(value[1])); 
		case "name":return driver.findElements(By.name(value[1]));
		case "classname":return driver.findElements(By.className(value[1]));
		case "linktext":return driver.findElements(By.linkText(value[1]));
		case "xpath":return driver.findElements(By.xpath(value[1]));
		case "css":return driver.findElements(By.cssSelector(value[1]));
		}
		
		return null;
	}
	
	protected void enterText(String elementname,String data)
	{
		getElement(elementname).sendKeys(data);
	}
	protected void click(String elementname)
	{
		getElement(elementname).click();
	}
	
	protected void actionClick(String elementname)
	{
		Actions action = new Actions(driver);
		action.moveToElement(getElement(elementname)).click().build().perform();
	}
	
	protected void jsClick(String elementname)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click()", getElement(elementname));
	}
	
	protected String getText(String elementname)
	{
		return getElement(elementname).getText();
	}
	
	protected boolean isDisplayed(String elementname)
	{
		try {
		return getElement(elementname).isDisplayed();
		}
		catch(Exception e)
		{
			return false;
		}
	}
}
