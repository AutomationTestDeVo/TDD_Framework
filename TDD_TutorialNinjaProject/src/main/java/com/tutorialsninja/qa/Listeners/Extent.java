package com.tutorialsninja.qa.Listeners;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Extent {
    
    static ExtentReports extentReport;

    public static ExtentReports generateExtentReport()
    {
	ExtentReports extentReport = new ExtentReports();
	File extentReportFile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
	ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
	
	sparkReporter.config().setTheme(Theme.DARK);
	sparkReporter.config().setReportName("TutorialsNinja Test Automation");	
	sparkReporter.config().setDocumentTitle("TN Automation Report");
	sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
	
	extentReport.attachReporter(sparkReporter);
	
	Properties prop = new Properties();
	File Configprop = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\configuration.properties");
	try {
	    FileInputStream fis = new FileInputStream(Configprop);
	    prop.load(fis);
	}
	catch(Throwable e){
	    e.printStackTrace();
	}
	extentReport.setSystemInfo("Application URL",prop.getProperty("URL"));
	extentReport.setSystemInfo("Browser Name",prop.getProperty("Browsername"));
	extentReport.setSystemInfo("Email",prop.getProperty("UserName"));
	extentReport.setSystemInfo("Password",prop.getProperty("Password"));
	extentReport.setSystemInfo("Operating System",System.getProperty("os.name"));
	extentReport.setSystemInfo("User Name: ",System.getProperty("user.name"));
	extentReport.setSystemInfo("Java Version: ",System.getProperty("java.version"));
	
	return extentReport;
    }
}
