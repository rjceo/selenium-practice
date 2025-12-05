package com.herokuapp.theinternet.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.time.Duration.*;

public class SecureAreaScreen {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(id = "flash") private WebElement lblSuccessLogin;
    @FindBy(xpath = "//h2[contains(text(),'Secure Area')]") private WebElement lblSecureArea;
    @FindBy(xpath = "//h4[contains(text(),'Welcome to the Secure Area. When you are done click logout below.')]")
        private WebElement lblBodyDesc;
    @FindBy(linkText = "Logout") private WebElement btnLogout;


    public SecureAreaScreen(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    public boolean isSecurePageLoaded(){
        try{
            wait.until(ExpectedConditions.visibilityOfAllElements(
                    lblSuccessLogin,
                    lblSecureArea,
                    lblBodyDesc,
                    btnLogout
            ));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
