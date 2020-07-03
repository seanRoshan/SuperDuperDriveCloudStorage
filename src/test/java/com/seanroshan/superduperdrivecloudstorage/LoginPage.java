package com.seanroshan.superduperdrivecloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {


    @FindBy(xpath = "//h1[@class='display-5' and contains(text(),'Login')]")
    private WebElement pageHeader;

    @FindBy(xpath = "//a[contains(@href,'signup')]")
    private WebElement navigateToSignUpPageButton;

    @FindBy(xpath = "//input[@name='username']")
    private WebElement userNameField;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[contains(text(),'Login')]")
    private WebElement loginButton;


    public LoginPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public String getPageHeader() {
        return this.pageHeader != null ? this.pageHeader.getText() : "";
    }

    public void clickOnSignUpPage() throws InterruptedException {
        this.navigateToSignUpPageButton.click();
    }

    public void login(String userName, String password) {
        this.userNameField.sendKeys(userName);
        this.passwordField.sendKeys(password);
        this.loginButton.click();
    }

}
