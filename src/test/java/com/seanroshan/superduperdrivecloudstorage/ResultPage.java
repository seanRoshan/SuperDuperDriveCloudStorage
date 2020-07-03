package com.seanroshan.superduperdrivecloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {


    @FindBy(xpath = "//a[contains(@href,'home')]")
    private WebElement continueButton;


    public ResultPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void clickOnContinueButton() throws InterruptedException {
        Thread.sleep(1000);
        this.continueButton.click();
    }

}
