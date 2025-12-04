package com.herokuapp.theinternet.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveTest {

    @Test
    public void loginTest() throws InterruptedException {
        System.out.println("Starting loginTest");

        //Create Chrome driver
        //System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();


        //maximize browser window
        driver.manage().window().maximize();

        //open test page
        String url = "https://the-internet.herokuapp.com/login";

        driver.get(url);
        System.out.println("Page is open");

        //enter user name
        String strUsername = "tomsmith";
        WebElement username = driver.findElement(new By.ById("username"));
        System.out.println("Username: ".concat(strUsername));
        username.sendKeys(strUsername);

        //enter password
        String strPassword = "SuperSecretPassword!";
        WebElement password = driver.findElement(new By.ById("password"));
        System.out.println("Password: ".concat(strPassword));
        password.sendKeys(strPassword);

        //click login button
        driver.findElement(new By.ByXPath("//button[@class='radius']")).click();

        //verifications
        //- new url
        System.out.println("\nVerify URL");
        String strExpectedURL = "https://the-internet.herokuapp.com/secure";
        System.out.println("Expected: ".concat(strExpectedURL));
        System.out.println("Actual: ".concat(driver.getCurrentUrl()));
        if(!driver.getCurrentUrl().equals(strExpectedURL)) {
            System.out.println("Result: FAIL");
            Assert.fail();
        }else
            System.out.println("Result: PASS");


        //success message
        System.out.println("\nVerify Success Message");
        String strExpectedSuccessMessage = "You logged into a secure area!";
        WebElement successMessage = driver.findElement(new By.ById("flash"));
        String strActualSuccessMessage = successMessage.getText().replace("×", "").replace("\n","").trim();

        System.out.println("Expected: ".concat(strExpectedSuccessMessage));
        System.out.println("Actual: ".concat(strActualSuccessMessage));

        if (strExpectedSuccessMessage.compareTo(strActualSuccessMessage) != 0) {
            System.out.println("Result: FAIL");
            Assert.fail();
        }else
            System.out.println("Result: PASS");


        //Header
        System.out.println("\nVerify Header");
        String strExpectedHeader = "Secure Area";
        WebElement expectedHeader = driver.findElement(By.xpath("//div[@id=\"content\"]/div/h2"));
        String strActualHeader = expectedHeader.getText().trim();

        System.out.println("Expected: ".concat(strExpectedHeader));
        System.out.println("Actual: ".concat(strActualHeader));

        if (strExpectedHeader.compareTo(strActualHeader) != 0 ){
            System.out.println("Result: FAIL");
            Assert.fail();
        }else
            System.out.println("Result: PASS");

        //Content
        System.out.println("\nVerify Content");
        String strExpectedContent = "Welcome to the Secure Area. When you are done click logout below.";
        WebElement expectedContent = driver.findElement(By.xpath("//div[@id=\"content\"]/div/h4"));
        String strActualContent = expectedContent.getText().trim();

        System.out.println("Expected: ".concat(strExpectedContent));
        System.out.println("Actual: ".concat(strActualContent));

        if (strExpectedContent.compareTo(strActualContent) != 0 ){
            System.out.println("Result: FAIL");
            Assert.fail();
        }else
            System.out.println("Result: PASS");


        //Verify Button is Visible & Clickable
        System.out.println("\nVerify Logout Button");

        WebElement btnLogout = driver.findElement(By.xpath("//div[@id=\"content\"]/div/a"));

        if (!btnLogout.isDisplayed()){
            System.out.println("Result: FAIL - button is not displayed");
            Assert.fail();
        }else
            System.out.println("Result: PASS - button is displayed.");

        if (!btnLogout.isEnabled()){
            System.out.println("Result: FAIL - button is not enabled.");
            Assert.fail();
        }else
            System.out.println("Result: PASS - button is enabled.");

        //Logout
        System.out.println("\nVerify Logout Action");
        btnLogout.click();

        String strExpectedSuccessLogout = "You logged out of the secure area!";
        WebElement successLogout = driver.findElement(By.id("flash"));
        String strActualSuccessLogout = successLogout.getText().replace("×","").replace("\n","").trim();

        System.out.println("Verify Logout Action - Success Message");
        System.out.println("Expected: ".concat(strExpectedSuccessLogout));
        System.out.println("Actual: ".concat(strActualSuccessLogout));

        if (strExpectedSuccessLogout.compareTo(strActualSuccessLogout) != 0){
            System.out.println("Result: FAIL");
            Assert.fail();
        }else
            System.out.println("Result: PASS");

        System.out.println("Verify Logout Action - URL");
        String strExpectedURLLogout = "https://the-internet.herokuapp.com/login";
        String strActualURLLogout = driver.getCurrentUrl();

        System.out.println("Expected: ".concat(strExpectedURLLogout));
        System.out.println("Actual: ".concat(strActualURLLogout));

        if (strExpectedURLLogout.compareTo(strActualURLLogout) != 0){
            System.out.println("Result: FAIL");
            Assert.fail();
        }else
            System.out.println("Result: PASS");


        //Close the browser
        System.out.println("\n*END TEST*");
        Thread.sleep(3000);
        driver.quit();
    }
}
