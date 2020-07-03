package com.seanroshan.superduperdrivecloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

    @FindBy(xpath = "//h1[@class='display-5' and contains(text(),'Sign Up')]")
    private WebElement pageHeader;

    @FindBy(xpath = "//input[@name='firstName']")
    private WebElement firstNameInputElement;

    @FindBy(xpath = "//input[@name='lastName']")
    private WebElement lastNameInputElement;

    @FindBy(xpath = "//input[@name='userName']")
    private WebElement userNameInputElement;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordInputElement;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//div[contains(@class,'alert alert-danger')]//span")
    private WebElement errorMessage;

    @FindBy(xpath = "//div[contains(@class,'alert alert-dark')]//span")
    private WebElement successMessage;


    public SignupPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public String getPageHeader() {
        return pageHeader != null ? pageHeader.getText() : "";
    }

    public String getErrorMessage() {
        return errorMessage != null ? errorMessage.getText() : "";
    }

    public String getSuccessMessage() {
        return successMessage != null ? successMessage.getText() : "";
    }

    public void signup(String firstName, String lastName, String username, String password) {
        this.firstNameInputElement.sendKeys(firstName);
        this.lastNameInputElement.sendKeys(lastName);
        this.userNameInputElement.sendKeys(username);
        this.passwordInputElement.sendKeys(password);
        this.submitButton.click();
    }


}
