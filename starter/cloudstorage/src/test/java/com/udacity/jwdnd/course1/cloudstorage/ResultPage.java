package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {
    @FindBy(id = "changeSuccess")
    private WebElement changeSuccess;

    @FindBy(id = "changeError")
    private WebElement changeError;

    @FindBy(id = "error")
    private WebElement error;

    public ResultPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void changeSuccessGoToHome() {
        this.changeSuccess.click();
    }
}
