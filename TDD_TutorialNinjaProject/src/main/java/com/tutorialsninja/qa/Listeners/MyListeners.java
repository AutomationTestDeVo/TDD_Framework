package com.tutorialsninja.qa.Listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


public class MyListeners implements ITestListener{
    
    static ExtentReports report;
    static ExtentTest extentTest;
    static WebDriver driver = null ;
    String testname;
    @Override
    public void onTestSuccess(ITestResult result) {
	extentTest.log(Status.PASS, testname+" got successfully executed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
	
	System.out.println("Screenshort taken");
	
	try {
	driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
	}catch(IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e)
	{
	    e.printStackTrace();
	}
	File srcScreenshort = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	String destinationScreenShortPath = System.getProperty("user.dir")+"\\ScreenShort\\"+testname+".png";
	try {
	    FileHandler.copy(srcScreenshort, new File(destinationScreenShortPath));
	} catch (IOException e) {
	    e.printStackTrace();
	}
	//If screenshort call from utilities file
//	String destinationpath = Utilities.CaptureScreenShort(driver, String testName)
//	
	extentTest.addScreenCaptureFromPath(destinationScreenShortPath);
	extentTest.log(Status.INFO, result.getThrowable());
	extentTest.log(Status.FAIL, testname+" got failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
	extentTest.log(Status.INFO, result.getThrowable());
	extentTest.log(Status.SKIP, testname+" got Skipped");
    }

    @Override
    public void onStart(ITestContext context) {
	//
	report = Extent.generateExtentReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
	testname = result.getName();
	extentTest = report.createTest(testname);
	extentTest.log(Status.INFO, testname+ " Started executing");
	}

    @Override
    public void onFinish(ITestContext context) {
	report.flush();
	String pathofExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
	File ExtentReport = new File(pathofExtentReport);
	try {
	    Desktop.getDesktop().browse(ExtentReport.toURI());
	} catch (IOException e) {
	    e.printStackTrace();
	}
    } 
}
