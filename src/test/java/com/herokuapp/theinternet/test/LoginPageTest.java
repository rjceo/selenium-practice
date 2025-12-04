package com.herokuapp.theinternet.test;

import com.herokuapp.theinternet.common.Common;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.time.Duration;

public class LoginPageTest {
    Common config = new Common();

    @Test(
            groups = "positive",
            description = "Verify fields of the Login Page are displayed and have correct default values"
    )
    public void verifyLoginPageFields() throws InterruptedException{
        Assert.fail("Need to write tests for this function");
    }

    @Test(
            groups = { "positive" },
            description = "Verify user is able to successfully login to the secure page"
    )
    public void verifySuccessfulLogin() throws InterruptedException{
        WebDriver driver = config.openBrowser(browser);
        //Set page implicit load
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(config.PageWaitMS));

        driver.get(config.baseURL.concat("/login"));
        System.out.println("Opening URL ".concat(config.baseURL.concat("/login")));
        System.out.println();

        //Setup wait for Elements
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(config.ElementWaitMS));

        // Enter username
        WebElement username = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("username"))));
        System.out.println("Username: ".concat(config.UserName));
        username.sendKeys(config.UserName);

        // Enter password
        WebElement password = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("password"))));
        System.out.println("Password: ".concat(config.Password));
        password.sendKeys(config.Password);
        System.out.println();

        // Click login button
        if (wait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[@class='radius']"))),
                ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[@class='radius']")))
        )))
            driver.findElement(By.xpath("//button[@class='radius']")).click();
        else
            Assert.fail("Login button is not visible and/or clickable.");

        //Secure Page
        System.out.println("Verify URL");
        String strExpectedURL = config.baseURL.concat("/secure");
        String strActualURL = driver.getCurrentUrl().trim().toLowerCase();
        System.out.println("Expected: ".concat(strExpectedURL));
        System.out.println("Actual: ".concat(strActualURL));
        if(!strExpectedURL.equals(strActualURL))
            Assert.fail("URL is not as expected!");
        System.out.println();

        System.out.println("Verify Success Message");
        String strExpectedMsg = "You logged into a secure area!";
        //WebElement successMsg = driver.findElement(By.xpath("//div[@id='flash'][@class='flash success']"));
        WebElement successMsg = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@id='flash'][@class='flash success']"))));
        String[] strActualMsg = successMsg.getText().trim().split("\n");
        System.out.println("Expected: ".concat(strExpectedMsg));
        System.out.println("Actual: ".concat(strActualMsg[0]));
        if (!strExpectedMsg.equals(strActualMsg[0]))
            Assert.fail("Message is not as expected!");
        System.out.println();

        // Logout
        if (wait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a/i[@class='icon-2x icon-signout']"))),
                ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a/i[@class='icon-2x icon-signout']")))
        )))
            driver.findElement(By.xpath("//a/i[@class='icon-2x icon-signout']")).click();
        else
            Assert.fail("Logout button is not visible and/or clickable.");

        driver.quit();
    }

    String browser = "chrome";
}
