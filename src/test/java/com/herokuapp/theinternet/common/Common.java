package com.herokuapp.theinternet.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class Common {
    public String baseURL;
    public String UserName;
    public String Password;
    public int ElementWaitMS;
    public int PageWaitMS;


    public Common(){
        readConfig();
    }

    // Reads the properties file.
    private void readConfig(){
        try {
            InputStream inputStream = new FileInputStream("config/test.properties");
            Properties prop = new Properties();

            prop.load(inputStream);
            baseURL = prop.getProperty("baseurl");
            UserName = prop.getProperty("username");
            Password = prop.getProperty("password");
            ElementWaitMS = Integer.parseInt(prop.getProperty("elementwaitms"));
            PageWaitMS = Integer.parseInt(prop.getProperty("pagewaitms"));

        } catch (FileNotFoundException e) {
            System.out.println("Property file not found!" + e.getMessage());
        } catch (Exception e){
            System.out.println("An error has been encountered!" + e.getMessage());
        }
    }

    // Function to launch different browsers.
    public WebDriver openBrowser(String browser){
        WebDriver tmpDriver;

        switch (browser.toLowerCase().trim()){
            case "edge":
                tmpDriver = new EdgeDriver();
            case "firefox":
                tmpDriver = new FirefoxDriver();
            default:
                tmpDriver = new ChromeDriver();

        }
        tmpDriver.manage().window().maximize();
        return tmpDriver;
    }
}
