package com.swaglabs.utils;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

public class Browser {
protected static WebDriver driver;


public void launchBrowser(String browsername)
{
//	ChromeOptions options = new ChromeOptions();
//	Map<String,Object> prefs = new HashMap<>();
//	prefs.put("credntials_enable_service", false);
//	prefs.put("profile.password_manager_enabled", false);
//	options.addArguments("user-data-dir=/path/to/empty/profile");
//	
//	options.setExperimentalOption("prefs", prefs);
//	options.addArguments("--disable-infobars");
//	options.addArguments("--disable-notifications");
	//options.addArguments("--disable-save-password-bubble");
	switch(browsername)
	{
	case "chrome" : driver=new ChromeDriver();
	break;
	case "edge": driver=new EdgeDriver();
	break;
	default: System.out.println("Invalid browsername");
	}
	driver.manage().window().maximize();
}

public void openURL()
{
	driver.get("https://www.saucedemo.com/");
}

public void openURL(String url)
{
	driver.get(url);
}

public void close()
{
	driver.close();
}
}

