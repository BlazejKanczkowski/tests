package com.example.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends AbstractPage {

    @FindBy(xpath = "//h2[normalize-space()='Subscription']")
    private WebElement subscriptionHeader;

    @FindBy(css = "#susbscribe_email")
    private WebElement subscriptionInput;

    @FindBy(css = "#subscribe")
    private WebElement subscriptionButton;

    @FindBy(xpath = "//div[contains(@class, 'alert-success') and contains(text(), 'You have been successfully subscribed!')]")
    private WebElement successMessage;

    @FindBy(css = ".cart_product")
    private List<WebElement> cartItems;

    @FindBy(css = ".cart_total_price")
    private List<WebElement> productPrices;

    @FindBy(css = ".cart_quantity")
    private List<WebElement> productQuantities;

    @FindBy(css = ".cart_total_price")
    private WebElement totalPriceElement;




    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void scrollToSubscriptionSection() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", subscriptionHeader);
    }

    public boolean isSubscriptionSectionVisible() {
        return isElementDisplayed(subscriptionHeader);
    }
    public boolean isSubscriptionSuccessMessageVisible() {
        return isElementDisplayed(successMessage);
    }

    public void subscribe(String email) {
        sendKeys(subscriptionInput, email);
        click(subscriptionButton);
    }

    public boolean isProductInCart(int productIndex) {
        return cartItems.size() > productIndex && cartItems.get(productIndex).isDisplayed();
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
        for (WebElement priceElement : productPrices) {
            String priceText = priceElement.getText().replaceAll("[^\\d]", "");
            total += Integer.parseInt(priceText);
        }
        return "Rs. " + total;
    }
}
