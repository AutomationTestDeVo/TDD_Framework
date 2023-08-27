package com.tutorialninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.qa.Base.BaseClass;

public class SearchTest extends BaseClass {
    
    public SearchTest()
    {
	super();
    }
    
    
    public WebDriver  driver;
    
    @BeforeMethod
    public void setup()
    {
	driver = initializeBrowserAndOpenApplication(prop.getProperty("Browsername"));
    }
    @AfterMethod
    public void teardown()
    {
	driver.quit();
    }
    
    @Test(priority=1)
    public void verifySearchWithValidProduct() {
	
	driver.findElement(By.xpath("//input[contains(@class,'input-lg')]")).sendKeys(dataprop.getProperty("validproduct"));
	driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
	
	Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed());
    }
    
    @Test(priority=2)
    public void verifySearchwithInvalidProduct() {
	
	driver.findElement(By.xpath("//input[contains(@class,'input-lg')]")).sendKeys(dataprop.getProperty("InvalidProduct"));
	driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
	
	String ActualSearchMsg = driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
	Assert.assertTrue(ActualSearchMsg.contains("There is no product that matches the search criteria."), "Required Search Error Message is not Displayed");
	
    }
    
    @Test(priority=3)
    public void verifySearchWithoutAnyProduct() {
	
	driver.findElement(By.xpath("//input[contains(@class,'input-lg')]")).sendKeys("");
	driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click();
	
	String ActualSearchMsg = driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
	Assert.assertTrue(ActualSearchMsg.contains("There is no product that matches the search criteria."), "Required Search Error Message is not Displayed");
	
    }

}
