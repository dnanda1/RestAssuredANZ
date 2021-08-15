package com.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class TestBase  {
	public static WebDriver driver;
	
	public Properties prop;
	String path;

	
	public  TestBase() {
		
		prop = new Properties();
		FileInputStream ip;
		try {
			ip = new FileInputStream("config.properties");
			try {
				prop.load(ip);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			
		 catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		
		
	}
	
	public String getScreenshot() 
	{
		String timeStamp;
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		timeStamp = new SimpleDateFormat("yyyy MM dd_HH mm ss").format(Calendar.getInstance().getTime()); 
		File screenShotName = new File("./Screenshot//"+timeStamp+".png");
		try {
			FileUtils.copyFile(scrFile, screenShotName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		String filePath = screenShotName.toString();
	
		return path;
		
	}
	
	
}
