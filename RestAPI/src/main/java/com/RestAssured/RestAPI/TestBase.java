package com.RestAssured.RestAPI;

import java.io.FileInputStream;
import java.util.Properties;

public class TestBase {
	
	public Properties prop;
	
	public TestBase()
	{
		prop = new Properties();
		try {
			FileInputStream fip = new FileInputStream("D:\\E-Commerce\\RestAPI\\config.properties");
			prop.load(fip);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}