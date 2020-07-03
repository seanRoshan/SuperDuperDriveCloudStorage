package com.seanroshan.superduperdrivecloudstorage;

import com.seanroshan.superduperdrivecloudstorage.model.Credential;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CredentialPage {


    @FindBy(xpath = "//a[contains(@id,'nav-credentials-tab')]")
    private WebElement credentialTab;


    @FindBy(xpath = "//button[contains(text(),'Add a New Credential')]")
    private WebElement addNewCredentialButton;


    @FindBy(xpath = "//input[@name='url']")
    private WebElement urlField;

    @FindBy(xpath = "//input[@name='userName']")
    private WebElement userNameField;

    @FindBy(xpath = "//input[@name='unEncryptedPassword']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[contains(@id,'credential-submit-button')]")
    private WebElement saveChangesButton;

    @FindBy(xpath = "//button[contains(text(),'Edit') and contains(@credential-id,'')]")
    private WebElement editButton;

    @FindBy(xpath = "//a[contains(text(),'Delete')]")
    private WebElement deleteButton;

    @FindBy(xpath = "//td[contains(text(),'YOU DO NOT HAVE ANY CREDENTIAL')]")
    private WebElement noCredentialText;


    public CredentialPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }


    public void navigateToCredentialTab() throws InterruptedException {
        Thread.sleep(1000);
        this.credentialTab.click();
    }


    public Credential getCredential() throws InterruptedException {
        Thread.sleep(1000);
        Credential credential = new Credential();
        credential.setUrl(editButton.getAttribute("credential-url"));
        credential.setPassword(editButton.getAttribute("credential-password"));
        credential.setUserName(editButton.getAttribute("credential-username"));
        return credential;
    }

    public void addNewCredential(Credential credential) throws InterruptedException {
        Thread.sleep(1000);
        this.addNewCredentialButton.click();
        Thread.sleep(1000);
        this.urlField.sendKeys(credential.getUrl());
        this.userNameField.sendKeys(credential.getUserName());
        this.passwordField.sendKeys(credential.getUnEncryptedPassword());
        this.saveChangesButton.click();
    }

    public void editCredential(Credential credential) throws InterruptedException {
        Thread.sleep(1000);
        this.editButton.click();
        Thread.sleep(1000);
        this.urlField.clear();
        this.urlField.sendKeys(credential.getUrl());
        this.userNameField.clear();
        this.userNameField.sendKeys(credential.getUserName());
        this.passwordField.clear();
        this.passwordField.sendKeys(credential.getUnEncryptedPassword());
        this.saveChangesButton.click();
    }

    public void deleteCredential() throws InterruptedException {
        Thread.sleep(1000);
        this.deleteButton.click();
    }

    public String getNoCredentialText() throws InterruptedException {
        Thread.sleep(1000);
        return this.noCredentialText.getText();
    }

}
