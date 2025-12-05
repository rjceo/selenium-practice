package com.herokuapp.theinternet.test;

import com.herokuapp.theinternet.common.Common;
import com.herokuapp.theinternet.pom.LoginPageScreen;
import com.herokuapp.theinternet.pom.SecureAreaScreen;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SecureAreaTest {
    private Common common;
    private WebDriver driver;
    private SecureAreaScreen secureAreaScreen;
    private LoginPageScreen loginPageScreen;

    //Setup and teardown
    @BeforeMethod(alwaysRun = true)
    public void Setup(){
        common = new Common();
        driver = common.openBrowser("chrome");
        secureAreaScreen = new SecureAreaScreen(driver);
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
            description = "Verify that user logouts successfully"
    )
    public void verifySuccessfulLogout(){
        loginPageScreen.navigateToLoginPage();
        loginPageScreen.enterUserName(common.UserName);
        loginPageScreen.enterPassword(common.Password);
        loginPageScreen.clickLoginButton();
        secureAreaScreen.clickLogoutButton();
        Assert.assertTrue(loginPageScreen.isLoginPageLoaded());
        Assert.assertEquals(loginPageScreen.getFlashErrorMessage(), "You logged out of the secure area!\n×");
    }
}
