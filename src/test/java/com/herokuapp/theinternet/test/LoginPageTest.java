package com.herokuapp.theinternet.test;

import com.herokuapp.theinternet.common.Common;
import com.herokuapp.theinternet.pom.LoginPageScreen;
import com.herokuapp.theinternet.pom.SecureAreaScreen;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginPageTest {
    private Common common;
    private WebDriver driver;
    private LoginPageScreen loginPageScreen;


    //Setup and teardown
    @BeforeMethod(alwaysRun = true)
    public void setup(){
        common = new Common();
        driver = common.openBrowser("chrome");
        loginPageScreen = new LoginPageScreen(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        if (driver != null)
            driver.quit();
    }

    //Scenarios
    @Test(
            groups = "positive",
            description = "Verify successful login using valid credentials"
    )
    public void verifySuccessfulLogin(){
        loginPageScreen.navigateToLoginPage();
        loginPageScreen.enterUserName(common.UserName);
        loginPageScreen.enterPassword(common.Password);
        loginPageScreen.clickLoginButton();
        Assert.assertTrue(new SecureAreaScreen(driver).isSecurePageLoaded());
    }

    @Test(
            groups = "negative",
            description = "Verify error message is displayed Username and Password are blank"
    )
    public void verifyErrorBlankCredentials(){
        loginPageScreen.navigateToLoginPage();
        loginPageScreen.clickLoginButton();
        Assert.assertEquals(loginPageScreen.getFlashErrorMessage(), "Your username is invalid!\n×");
    }

    @Test(
            groups = "negative",
            description = "Verify error message when Password is blank"
    )
    public void verifyErrorBlankPassword(){
        loginPageScreen.navigateToLoginPage();
        loginPageScreen.clearFields();
        loginPageScreen.enterUserName(common.UserName);
        loginPageScreen.clickLoginButton();
        Assert.assertEquals(loginPageScreen.getFlashErrorMessage(), "Your password is invalid!\n×");
    }

    @Test(
            groups = "negative",
            description = "Verify error message when Password is invalid"
    )
    public void verifyErrorInvalidPassword(){
        loginPageScreen.navigateToLoginPage();
        loginPageScreen.clearFields();
        loginPageScreen.enterUserName(common.UserName);
        loginPageScreen.clickLoginButton();
        Assert.assertEquals(loginPageScreen.getFlashErrorMessage(), "Your password is invalid!\n×");
    }
}
