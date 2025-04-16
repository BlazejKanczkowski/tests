package com.example.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//h2[normalize-space()='Login to your account']")
    private ExtendedWebElement loginHeader;

    @FindBy(css = "input[data-qa='login-email']")
    private ExtendedWebElement emailInput;

    @FindBy(css = "input[data-qa='login-password']")
    private ExtendedWebElement passwordInput;

    @FindBy(css = "button[data-qa='login-button']")
    private ExtendedWebElement loginButton;

    @FindBy(xpath = "//*[contains(text(), 'Your email or password is incorrect!')]")
    private ExtendedWebElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoginHeaderVisible() {
        return loginHeader.isElementPresent(5);
    }

    public void enterLoginCredentials(String email, String password) {
        emailInput.type(email);
        passwordInput.type(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public boolean isLoginErrorVisible() {
        return errorMessage.isElementPresent(5);
    }
}
