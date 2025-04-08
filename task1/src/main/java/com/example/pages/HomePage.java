package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HomePage extends BasePage {


    @FindBy(css = "button[type='submit']")
    private WebElement searchButton;

    @FindBy(css = "button.fc-button.fc-cta-consent.fc-primary-button")
    private List<WebElement> cookieButtons;

    @FindBy(css = "a[href='/products']")
    private WebElement productsButton;

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get(getAppUrl());
    }

    public void acceptCookiesIfPresent() {
        if (!cookieButtons.isEmpty() && cookieButtons.get(0).isDisplayed()) {
            cookieButtons.get(0).click();
        }
    }

    public ProductsPage goToProductsPage() {
        productsButton.click();
        wait.until(ExpectedConditions.urlContains("/products"));
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.waitForPageToLoad();
        return productsPage;
    }
}
