package com.example.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            Browser browser = Browser.valueOf(ConfigManager.getProperty(Constants.BROWSER).toUpperCase());

            switch (browser) {
                case CHROME:
                    WebDriverManager.chromedriver().setup();
                    driver.set(new ChromeDriver());
                    break;
                case FIREFOX:
                    WebDriverManager.firefoxdriver().setup();
                    driver.set(new FirefoxDriver());
                    break;
                case IE:
                    WebDriverManager.iedriver().setup();
                    driver.set(new InternetExplorerDriver());
                    break;
                case SAFARI:
                    WebDriverManager.safaridriver().setup();
                    driver.set(new SafariDriver());
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser " + browser);
            }

            driver.get().manage().window().maximize();
            int timeout = Integer.parseInt(ConfigManager.getProperty(Constants.IMPLICIT_WAIT_TIMEOUT));
            driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
        }
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
