package com.swaglabs.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadLocator {

	private Properties pro;
	public ReadLocator(String pagename)
	{
		String projectpath=System.getProperty("user.dir");
		String filepath=projectpath+"/src/main/resources/locators/"+pagename+".properties";
		File f = new File(filepath);
		if(!f.exists())
		{
			System.out.println("File not found : "+filepath);
			return;
		}
		try {
		FileInputStream instream = new FileInputStream(f);
		
		 pro = new Properties();
		pro.load(instream);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public String getLocator(String elementname)
	{
	return	pro.getProperty(elementname);
	}
	
	public static void main(String[] args) {
		ReadLocator read = new ReadLocator("loginpage");
		System.out.println(read.getLocator("jjjjj"));
	}
	
}
