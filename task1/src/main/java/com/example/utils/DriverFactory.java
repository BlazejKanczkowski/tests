//package com.example.utils;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.remote.RemoteWebDriver;
//import org.openqa.selenium.safari.SafariDriver;
//
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.time.Duration;
//
//public class DriverFactory {
//
//    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
//
//    public static WebDriver getDriver() {
//        if (driver.get() == null) {
//            String executionMode = ConfigManager.getProperty(Constants.EXECUTION_MODE);
//            Browser browser = Browser.valueOf(ConfigManager.getProperty(Constants.BROWSER).toUpperCase());
//
//            if ("grid".equalsIgnoreCase(executionMode)) {
//                try {
//                    URL gridUrl = new URL(ConfigManager.getProperty(Constants.GRID_URL));
//                    switch (browser) {
//                        case CHROME:
//                            DesiredCapabilities chromeCaps = new DesiredCapabilities();
//                            chromeCaps.setBrowserName("chrome");
//                            driver.set(new RemoteWebDriver(gridUrl, chromeCaps));
//                            break;
//                        case FIREFOX:
//                            DesiredCapabilities firefoxCaps = new DesiredCapabilities();
//                            firefoxCaps.setBrowserName("firefox");
//                            driver.set(new RemoteWebDriver(gridUrl, firefoxCaps));
//                            break;
//                        default:
//                            throw new IllegalArgumentException("Unsupported browser in grid mode: " + browser);
//                    }
//                } catch (MalformedURLException e) {
//                    throw new RuntimeException("Grid URL is not valid", e);
//                }
//            } else {
//
//                switch (browser) {
//                    case CHROME:
//                        WebDriverManager.chromedriver().setup();
//                        driver.set(new ChromeDriver());
//                        break;
//                    case FIREFOX:
//                        WebDriverManager.firefoxdriver().setup();
//                        driver.set(new FirefoxDriver());
//                        break;
//                    case IE:
//                        WebDriverManager.iedriver().setup();
//                        driver.set(new InternetExplorerDriver());
//                        break;
//                    case SAFARI:
//                        WebDriverManager.safaridriver().setup();
//                        driver.set(new SafariDriver());
//                        break;
//                    default:
//                        throw new IllegalArgumentException("Unsupported browser " + browser);
//                }
//            }
//
//            driver.get().manage().window().maximize();
//            int timeout = Integer.parseInt(ConfigManager.getProperty(Constants.IMPLICIT_WAIT_TIMEOUT));
//            driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
//        }
//        return driver.get();
//    }
//
//    public static void quitDriver() {
//        if (driver.get() != null) {
//            driver.get().quit();
//            driver.remove();
//        }
//    }
//}
