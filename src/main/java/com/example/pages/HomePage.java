package com.example.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {

    @FindBy(css = "button.fc-button.fc-cta-consent.fc-primary-button")
    private List<ExtendedWebElement> cookieButtons;

    @FindBy(css = "a[href='/products']")
    private ExtendedWebElement productsButton;

    @FindBy(xpath = "//h2[normalize-space()='Subscription']")
    private ExtendedWebElement subscriptionHeader;

    @FindBy(css = "#susbscribe_email")
    private ExtendedWebElement subscriptionInput;

    @FindBy(css = "#subscribe")
    private ExtendedWebElement subscriptionButton;

    @FindBy(xpath = "//div[contains(@class, 'alert-success') and contains(text(), 'You have been successfully subscribed!')]")
    private ExtendedWebElement successMessage;

    @FindBy(css = "a[href='/view_cart']")
    private ExtendedWebElement cartButton;

    @FindBy(css = "a[href='/test_cases']")
    private ExtendedWebElement testCasesButton;

    @FindBy(xpath = "//a[@href='/login']")
    private ExtendedWebElement signupLoginButton;

    public HomePage(WebDriver driver) {
        super(driver);
        setPageURL("/");
    }

    public void open() {
        openURL(getAppUrl());
    }

    public void acceptCookiesIfPresent() {
        if (!cookieButtons.isEmpty() && cookieButtons.get(0).isElementPresent(3)) {
            cookieButtons.get(0).click();
        }
    }

    public ProductsPage goToProductsPage() {
        productsButton.click();
        return new ProductsPage(driver);
    }

    public void scrollToSubscriptionSection() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", subscriptionHeader.getElement());
    }

    public void enterEmailForSubscription(String email) {
        subscriptionInput.type(email);
    }

    public void clickSubscribeButton() {
        subscriptionButton.click();
    }

    public boolean isSubscriptionSuccessMessageDisplayed() {
        return successMessage.isElementPresent(5);
    }

    public boolean isSubscriptionSectionVisible() {
        return subscriptionHeader.isElementPresent(5);
    }

    public void goToCartPage() {
        cartButton.click();
    }

    public void goToTestCasesPage() {
        testCasesButton.click();
    }

    public void goToLoginPage() {
        signupLoginButton.click();
    }
}
