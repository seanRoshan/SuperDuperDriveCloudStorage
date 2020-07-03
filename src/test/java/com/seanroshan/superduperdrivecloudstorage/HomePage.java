package com.seanroshan.superduperdrivecloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @FindBy(xpath = "//button[contains(text(),'Logout')]")
    private WebElement logoutButton;

    public HomePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void logout() throws InterruptedException {
        Thread.sleep(1000);
        this.logoutButton.click();
    }


}
