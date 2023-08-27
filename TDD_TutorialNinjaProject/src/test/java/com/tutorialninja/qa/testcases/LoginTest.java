package com.tutorialninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialninja.qa.Base.BaseClass;
import com.tutorialninja.qa.utils.Utilities;
import com.tutorialsninja.qa.Pages.AccountPage;
import com.tutorialsninja.qa.Pages.HomePage;
import com.tutorialsninja.qa.Pages.LoginPage;

public class LoginTest extends BaseClass {
    
    public LoginTest()
    {
	super();
    }
    
    public WebDriver  driver;
    LoginPage Page;
    HomePage homepage;
    AccountPage Account;
    
    @AfterMethod
    public void tearDown()
    {
	driver.quit();
    }
    
    @BeforeMethod
    public void setup()
    {
	
	driver = initializeBrowserAndOpenApplication(prop.getProperty("Browsername"));
	homepage = new HomePage(driver);
	homepage.clickOnMyAccount();
	Page = homepage.selectLoginOption();
    }
    
    
    @Test(priority = 1, dataProvider="supplyTestData")
    public void verifyLoginWithValidCredentials(String email, String password) {
	
	Page.enterEmailId(email);
	Page.enterPassword(password);
	Account = Page.clickLogin();
	Assert.assertTrue(Account.VerifyeditYourAccountInformation(), "The Required Page is not Displayed");
	
    }
    
    @DataProvider
    public Object [][] supplyTestData()
    {
	Object [][] data = {{"hello@gmail.com","helloworld"}, 
		{"hello1@gmail.com","helloworld"}};
	
//	Object [][] data = Utilities.gettestdataFromExcel("Login");
	return data;
    }
    
    @Test(priority = 2)
    public void verifyLoginWithInvalidCredentials()
    {
	
	Page.enterEmailId(Utilities.generateEmailwitTimeStamp());
	Page.enterPassword(dataprop.getProperty("invalidpassword"));
	Page.clickLogin();
	
	String ActualMsg = Page.verifyInvalidCredentilsMsg();
	String ExpectedMsg = dataprop.getProperty("ExpectedMsg");
	Assert.assertTrue(ActualMsg.contains(ExpectedMsg), "Expected Message is not DIsplayed" );
	
    }

}
