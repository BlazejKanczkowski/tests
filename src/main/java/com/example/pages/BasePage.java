package com.example.pages;


import com.zebrunner.carina.utils.R;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage extends com.zebrunner.carina.webdriver.gui.AbstractPage {
    protected WebDriver driver;
    protected WebDriverWait wait;


    public BasePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    protected String getAppUrl() {
        return R.CONFIG.get("url");
    }
}
