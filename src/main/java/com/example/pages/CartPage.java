package com.example.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends BasePage {

    @FindBy(xpath = "//h2[normalize-space()='Subscription']")
    private ExtendedWebElement  subscriptionHeader;

    @FindBy(css = "#susbscribe_email")
    private ExtendedWebElement  subscriptionInput;

    @FindBy(css = "#subscribe")
    private ExtendedWebElement  subscriptionButton;

    @FindBy(xpath = "//div[contains(@class, 'alert-success') and contains(text(), 'You have been successfully subscribed!')]")
    private ExtendedWebElement  successMessage;

    @FindBy(css = ".cart_product")
    private List<ExtendedWebElement > cartItems;

    @FindBy(css = ".cart_total_price")
    private List<ExtendedWebElement > productPrices;

    @FindBy(css = ".cart_quantity")
    private List<ExtendedWebElement > productQuantities;

    @FindBy(css = ".cart_total_price")
    private ExtendedWebElement  totalPriceElement;


    public CartPage(WebDriver driver) {
        super(driver);
        //PageFactory.initElements(driver, this);
    }

    public void scrollToSubscriptionSection() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", subscriptionHeader);
    }

    public boolean isSubscriptionSectionVisible() {
        return subscriptionHeader.isElementPresent(5);
    }

    public boolean isSubscriptionSuccessMessageVisible() {
        return successMessage.isElementPresent(5);
    }

    public void subscribe(String email) {
        subscriptionInput.type(email);
        subscriptionButton.click();
    }

    public boolean isProductInCart(int productIndex) {
        return cartItems.size() > productIndex && cartItems.get(productIndex).isElementPresent();
    }

    public boolean isPriceCorrect(int productIndex, String expectedPrice) {
        return productPrices.get(productIndex).getText().equals(expectedPrice);
    }

    public boolean isQuantityCorrect(int productIndex, int expectedQuantity) {
        return Integer.parseInt(productQuantities.get(productIndex).getText()) == expectedQuantity;
    }

    public boolean isTotalPriceCorrect(String expectedTotalPrice) {
        return totalPriceElement.getText().equals(expectedTotalPrice);
    }

    public String getProductPriceFromCart(int productIndex) {
        return productPrices.get(productIndex).getText();
    }

    public String getTotalPriceFromCart() {
        int total = 0;
        for (ExtendedWebElement priceElement : productPrices) {
            String priceText = priceElement.getText().replaceAll("[^\\d]", "");
            total += Integer.parseInt(priceText);
        }
        return "Rs. " + total;
    }
}
