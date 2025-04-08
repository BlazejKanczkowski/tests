package com.example.pages;

import com.example.utils.ConfigManager;
import com.example.utils.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(ConfigManager.getProperty(Constants.IMPLICIT_WAIT_TIMEOUT))));
    }

    protected String getAppUrl() {
        return ConfigManager.getProperty(Constants.APPLICATION_URL);
    }
}
