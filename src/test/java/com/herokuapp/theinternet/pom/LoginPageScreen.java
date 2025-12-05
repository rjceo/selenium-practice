package com.herokuapp.theinternet.pom;

import com.herokuapp.theinternet.common.Common;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPageScreen {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Common common = new Common();

    //Locators
    @FindBy(xpath = "//h2[text()='Login Page']") private WebElement lblHeader;
    @FindBy(className = "subheader") private WebElement lblSubHeader;
    @FindBy(xpath = "//label[@for='username']") private WebElement lblUsername;
    @FindBy(id = "username") private WebElement txtUsername;
    @FindBy(xpath = "//label[@for='password']") private WebElement lblPassword;
    @FindBy(id = "password") private WebElement txtPassword;
    @FindBy(xpath = "//button[@type='submit']") private  WebElement btnLogin;
    @FindBy(id = "flash") private WebElement lblFlashMessage;

    //Setup
    public LoginPageScreen(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    //Page Actions
    public void navigateToLoginPage(){
        driver.get(common.baseURL);
    }

    public boolean isLoginPageLoaded(){
        try{
            wait.until(ExpectedConditions.visibilityOfAllElements(
                    txtUsername,
                    txtPassword,
                    btnLogin
            ));
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public void enterUserName(String username){
        wait.until(ExpectedConditions.visibilityOf(txtUsername));
        txtUsername.clear();
        txtUsername.sendKeys(username);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(txtPassword));
        txtPassword.clear();
        txtPassword.sendKeys(password);
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(btnLogin));
        btnLogin.click();
    }

    public String getFlashErrorMessage(){
        try {
            wait.until(ExpectedConditions.visibilityOf(lblFlashMessage));
            return lblFlashMessage.getText().trim();
        }
        catch (Exception e){
            return "";
        }
    }

    public void clearFields(){
        txtUsername.clear();
        txtPassword.clear();
    }
}