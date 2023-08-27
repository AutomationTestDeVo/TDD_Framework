package com.tutorialsninja.qa.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    WebDriver driver;
    
    //Objects
    @FindBy(xpath="//span[text()='My Account']")
    private WebElement myAccountDropMenu;
    
    @FindBy(linkText="Register")
    private WebElement RegisterOption;
    
    @FindBy(linkText="Login")
    private WebElement LoginOption;
    
    
    public HomePage(WebDriver driver) {
	this.driver = driver;
	PageFactory.initElements(driver, this);
    }
    
    //Actions
    public void clickOnMyAccount() {
	myAccountDropMenu.click();
    }
    public AccountPage selectRegisterOption() {
	RegisterOption.click();
	return new AccountPage(driver);
    }
    public LoginPage selectLoginOption() {
	LoginOption.click();
	return new LoginPage(driver);
    }
}
