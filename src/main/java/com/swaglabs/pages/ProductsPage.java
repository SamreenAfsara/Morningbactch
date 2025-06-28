package com.swaglabs.pages;

import org.openqa.selenium.WebDriver;

import com.swaglabs.utils.SmartFunctions;

public class ProductsPage extends SmartFunctions {

	public ProductsPage(WebDriver driver, String pagename) {
		super(driver, pagename);
	}
	
	public boolean isProductHeadingVisible()
	{
		return isDisplayed("productsheading");
	}

}
