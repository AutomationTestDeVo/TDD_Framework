package com.tutorialsninja.qa.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    
    WebDriver driver;
    
    //objects
    @FindBy(id="input-email")
    private WebElement LoginEmail;
    
    @FindBy(id="input-password")
    private WebElement LoginPassword;
    
    @FindBy(xpath="//input[@value='Login']")
    private WebElement LoginButton;
    
    @FindBy(xpath="//div[contains(@class,'alert-dismissible')] ")
    private WebElement InvalidCredentialMsg;
    
    public LoginPage(WebDriver driver)
    {
	this.driver = driver;
	PageFactory.initElements(driver, this);
    }
    
    //Action
    public void enterEmailId(String email) {
	LoginEmail.sendKeys(email);
    }
    public void enterPassword(String password) {
	LoginPassword.sendKeys(password);
    }
    public AccountPage clickLogin() {
	LoginButton.click();
	return new AccountPage(driver);
    }
    public String verifyInvalidCredentilsMsg() {
	String ActualMsg = InvalidCredentialMsg.getText();
	return ActualMsg;
    }

}
