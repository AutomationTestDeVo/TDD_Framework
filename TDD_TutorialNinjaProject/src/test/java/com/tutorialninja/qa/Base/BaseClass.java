package com.tutorialninja.qa.Base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.tutorialninja.qa.utils.Utilities;

public class BaseClass {
    
    WebDriver driver;
    public Properties prop, dataprop;
    
    
    //Loading Properties file
    public BaseClass()
    {
	prop = new Properties();
	File propFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\configuration.properties");
	try {
	    FileInputStream fis = new FileInputStream(propFile);
	    prop.load(fis);
	}
	catch(Throwable e){
	    e.printStackTrace();
	}

	dataprop = new Properties();
	File dataPropFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\testdata.properties");
	try {
	    FileInputStream fis2 = new FileInputStream(dataPropFile);
	    dataprop.load(fis2);
	}catch(Throwable e) {
	    e.printStackTrace();
	}

    }
    
    
    public WebDriver initializeBrowserAndOpenApplication(String browsername) {
	
	
	if(browsername.equalsIgnoreCase("chrome"))
	{
	    driver = new ChromeDriver();
	}
	else if(browsername.equalsIgnoreCase("firefox"))
	{
	    driver = new FirefoxDriver();
	}
	else if(browsername.equalsIgnoreCase("edge"))
	{
	    driver = new EdgeDriver();
	}
	else if(browsername.equalsIgnoreCase("safari"))
	{
	    driver = new SafariDriver();
	}
	
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_WAIT_TIME));
	driver.get(prop.getProperty("URL"));
	
	return driver;
    }
}
