package com.example.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HomePage extends AbstractPage {


    @FindBy(css = "button[type='submit']")
    private WebElement searchButton;

    @FindBy(css = "button.fc-button.fc-cta-consent.fc-primary-button")
    private List<WebElement> cookieButtons;

    @FindBy(css = "a[href='/products']")
    private WebElement productsButton;

    @FindBy(xpath = "//h2[normalize-space()='Subscription']")
    private WebElement subscriptionHeader;

    @FindBy(css = "#susbscribe_email")
    private WebElement subscriptionInput;

    @FindBy(css = "#subscribe")
    private WebElement subscriptionButton;

    @FindBy(xpath = "//div[contains(@class, 'alert-success') and contains(text(), 'You have been successfully subscribed!')]")
    private WebElement successMessage;

    @FindBy(css = "a[href='/view_cart']")
    private WebElement cartButton;

    @FindBy(css = "a[href='/test_cases']")
    private WebElement testCasesButton;

    @FindBy(xpath = "//a[@href='/login']")
    private WebElement signupLoginButton;


    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get(getAppUrl());
    }

    public void acceptCookiesIfPresent() {
        if (!cookieButtons.isEmpty() && cookieButtons.get(0).isDisplayed()) {
            click(cookieButtons.get(0));
        }
    }

    public ProductsPage goToProductsPage() {
        click(productsButton);
        wait.until(ExpectedConditions.urlContains("/products"));
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.waitForPageToLoad();
        return productsPage;
    }

    public void scrollToSubscriptionSection() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", subscriptionHeader);
    }

    public void enterEmailForSubscription(String email) {
        logger.info("Entering email '{}' for subscription", email);
        sendKeys(subscriptionInput, email);
    }

    public void clickSubscribeButton() {
        click(subscriptionButton);
    }

    public boolean isSubscriptionSuccessMessageDisplayed() {
        logger.info("Checking if subscription success message is displayed");
        try {
            wait.until(ExpectedConditions.visibilityOf(successMessage));
            return successMessage.isDisplayed();
        } catch (TimeoutException e) {
            logger.warn("Subscription success message not displayed in time");
            return false;
        }
    }

    public boolean isSubscriptionSectionVisible() {
        logger.info("Checking if Subscription section is visible");
        try {
            wait.until(ExpectedConditions.visibilityOf(subscriptionHeader));
            return subscriptionHeader.isDisplayed();
        } catch (TimeoutException e) {
            logger.warn("Subscription section is not visible");
            return false;
        }
    }

    public void goToCartPage() {
        click(cartButton);
    }

    public void goToTestCasesPage() {
        click(testCasesButton);
    }

    public void goToLoginPage() {
        click(signupLoginButton);
    }
}
