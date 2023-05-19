package com.myparallel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.HashMap;
import java.util.Map;

public class BaseCollection {
    private static Map<String, WebDriver> browsers = new HashMap<>();

    public static void initializeBrowser(String browserName) {
        String driverDirectory = System.getProperty("user.dir")+"\\drivers\\";
        String driverLocation = System.getProperty("driverLocation",driverDirectory);

        WebDriver driver;
        switch (browserName.toLowerCase()) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", driverLocation + "chromedriver");
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                System.setProperty("webdriver.ie.driver", driverLocation + "IEDriverServer.exe");
                driver = new EdgeDriver();
                break;
            case "ie":
                System.setProperty("webdriver.ie.driver", driverLocation + "IEDriverServer.exe");
                driver = new InternetExplorerDriver();
                break;
            default:
                throw new IllegalArgumentException("Invalid browser name: " + browserName);
        }
        browsers.put(browserName, driver);
    }

    public static WebDriver getBrowser(String browserName) {
        return browsers.get(browserName);
    }

    public static void closeBrowser(String browserName) {
        WebDriver driver = browsers.remove(browserName);
        if (driver != null) {
            driver.quit();
        }
    }
}
