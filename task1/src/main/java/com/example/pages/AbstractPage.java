package com.example.pages;

import com.example.utils.ConfigManager;
import com.example.utils.Constants;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public abstract class AbstractPage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(ConfigManager.getProperty(Constants.IMPLICIT_WAIT_TIMEOUT))));
    }

    protected String getAppUrl() {
        return ConfigManager.getProperty(Constants.APPLICATION_URL);
    }

    // Wrapper method: click
    protected void click(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (TimeoutException e) {
            throw e;
        }
    }

    // Wrapper method: sendKeys
    protected void sendKeys(WebElement element, String text) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            element.clear();
            element.sendKeys(text);
            logger.info("Sent keys '{}' to element: {}", text, describeElement(element));
        } catch (TimeoutException e) {
            logger.error("Element not visible to send keys: {}", describeElement(element), e);
            throw e;
        }
    }

    // Wrapper method: getText
    protected String getText(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            String text = element.getText();
            return text;
        } catch (TimeoutException e) {
            throw e;
        }
    }

    // Optional helper to log elements better
    private String describeElement(WebElement element) {
        try {
            String tagName = element.getTagName();
            String text = element.getText();
            String id = element.getAttribute("id");
            String classes = element.getAttribute("class");
            return String.format("[Tag: %s, Text: %s, ID: %s, Class: %s]", tagName, text, id, classes);
        } catch (StaleElementReferenceException e) {
            return "[Element became stale]";
        }
    }

    public void waitForElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public boolean isElementDisplayed(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

}
