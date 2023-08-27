package com.tutorialninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.qa.Base.BaseClass;
import com.tutorialninja.qa.utils.Utilities;
import com.tutorialsninja.qa.Pages.AccountPage;
import com.tutorialsninja.qa.Pages.HomePage;


public class RegisterTest extends BaseClass {
    
    public RegisterTest()
    {
	super();
    }
    

    public WebDriver  driver;
    HomePage homePage;
    AccountPage Account;

    @BeforeMethod
    public void setup()
    {
	driver = initializeBrowserAndOpenApplication(prop.getProperty("Browsername"));
	homePage = new HomePage(driver);
	homePage.clickOnMyAccount();
	Account = homePage.selectRegisterOption();
	
    }
    @AfterMethod
    public void teardown()
    {
	driver.quit();
    }

    @Test(priority=1)
    public void verifyRegisteringAccountWithMandatoryField() {



	driver.findElement(By.id("input-firstname")).sendKeys(dataprop.getProperty("firstname"));
	driver.findElement(By.id("input-lastname")).sendKeys(dataprop.getProperty("lastname"));
	driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailwitTimeStamp());
	driver.findElement(By.id("input-telephone")).sendKeys(dataprop.getProperty("telephone"));

	driver.findElement(By.id("input-password")).sendKeys(dataprop.getProperty("validpassword"));
	driver.findElement(By.id("input-confirm")).sendKeys(dataprop.getProperty("validpassword"));

	driver.findElement(By.xpath("//input[@type='checkbox']")).click();
	driver.findElement(By.xpath("//input[@type='submit']")).click();

	String ActualHeading = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
	Assert.assertEquals(ActualHeading, "Your Account Has Been Created!", "Account Success message is not displayed");

    }


    @Test(priority = 2)
    public void verifyRegisteringAccountByProvidingAllFields() {

	driver.findElement(By.id("input-firstname")).sendKeys(dataprop.getProperty("firstname"));
	driver.findElement(By.id("input-lastname")).sendKeys(dataprop.getProperty("lastname"));
	driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailwitTimeStamp());
	driver.findElement(By.id("input-telephone")).sendKeys(dataprop.getProperty("telephone"));

	driver.findElement(By.id("input-password")).sendKeys(dataprop.getProperty("validpassword"));
	driver.findElement(By.id("input-confirm")).sendKeys(dataprop.getProperty("validpassword"));



	driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();

	driver.findElement(By.xpath("//input[@type='checkbox']")).click();
	driver.findElement(By.xpath("//input[@type='submit']")).click();

	String ActualHeading = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
	Assert.assertEquals(ActualHeading, "Your Account Has Been Created!", "Account Success message is not displayed");

    }


    @Test(priority=3, dependsOnMethods= {"verifyRegisteringAccountByProvidingAllFields"})
    public void verifyRegisteringAccountWithExistingEmailAddress()
    {

	driver.findElement(By.id("input-firstname")).sendKeys(dataprop.getProperty("firstname"));
	driver.findElement(By.id("input-lastname")).sendKeys(dataprop.getProperty("lastname"));
	driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("UserName"));
	driver.findElement(By.id("input-telephone")).sendKeys(dataprop.getProperty("telephone"));

	driver.findElement(By.id("input-password")).sendKeys(dataprop.getProperty("validpassword"));
	driver.findElement(By.id("input-confirm")).sendKeys(dataprop.getProperty("validpassword"));


	driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();

	driver.findElement(By.xpath("//input[@type='checkbox']")).click();
	driver.findElement(By.xpath("//input[@type='submit']")).click();

	String ActualWarning = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
	Assert.assertTrue(ActualWarning.contains("Warning: E-Mail Address is already registered!"), "The Required warning Message is not Displayed");

    }

    @Test(priority = 4)
    public void verifyRegisteringAccountWithoutFillingAnyDetails() {

	driver.findElement(By.xpath("//input[@type='submit']")).click();

	String ActualErrorMsg = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
	Assert.assertTrue(ActualErrorMsg.contains("Warning: You must agree to the Privacy Policy!"), "The required error Message is not Available");

	String firstnameErrorMsg = driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling :: div")).getText();
	Assert.assertTrue(firstnameErrorMsg.contains("First Name must be between 1 and 32 characters!"), "The required firstname error Message is not Available");

	String lastnameErrorMsg = driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling :: div")).getText();
	Assert.assertTrue(lastnameErrorMsg.contains("Last Name must be between 1 and 32 characters!"), "The required lastname error Message is not Available");

	String EmailErrorMsg = driver.findElement(By.xpath("//input[@id='input-email']/following-sibling :: div")).getText();
	Assert.assertTrue(EmailErrorMsg.contains("E-Mail Address does not appear to be valid!"), "The required email error Message is not Available");

	String TelephoneErrorMsg = driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling :: div")).getText();
	Assert.assertTrue(TelephoneErrorMsg.contains("Telephone must be between 3 and 32 characters!"), "The required Telephone error Message is not Available");

	String PasswordErrorMsg = driver.findElement(By.xpath("//input[@id='input-password']/following-sibling :: div")).getText();
	Assert.assertTrue(PasswordErrorMsg.contains("Password must be between 4 and 20 characters!"), "The required Password error Message is not Available");

    }
}
