package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AbstractPage {

    @FindBy(xpath = "//h2[normalize-space()='Login to your account']")
    private WebElement loginHeader;

    @FindBy(css = "input[data-qa='login-email']")
    private WebElement emailInput;

    @FindBy(css = "input[data-qa='login-password']")
    private WebElement passwordInput;

    @FindBy(css = "button[data-qa='login-button']")
    private WebElement loginButton;

    @FindBy(xpath = "//*[contains(text(), 'Your email or password is incorrect!')]")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        wait.until(ExpectedConditions.visibilityOf(loginHeader));
    }

    public boolean isLoginHeaderVisible() {
        return isElementDisplayed(loginHeader);
    }

    public void enterLoginCredentials(String email, String password) {
        sendKeys(emailInput, email);
        sendKeys(passwordInput, password);
    }

    public void clickLoginButton() {
        click(loginButton);
    }

    public boolean isLoginErrorVisible() {
        return isElementDisplayed(errorMessage);
    }
}
