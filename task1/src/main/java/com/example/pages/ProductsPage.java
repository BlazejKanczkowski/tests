package com.example.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;


public class ProductsPage extends AbstractPage {

    @FindBy(css = "input[name='search']")
    private WebElement searchInput;

    @FindBy(css = "button#submit_search")
    private WebElement searchButton;

    @FindBy(css = ".productinfo.text-center p")
    private List<WebElement> productTitles;

    @FindBy(xpath = "//h2[normalize-space()='All Products']")
    private WebElement allProductsHeader;

    @FindBy(css = ".single-products")
    private List<WebElement> products;

    @FindBy(css = ".single-products .productinfo .add-to-cart")
    private List<WebElement> addToCartButtons;

    @FindBy(css = "a[href='/view_cart']")
    private WebElement cartButton;

    @FindBy(css = "button.btn.btn-success.close-modal[data-dismiss='modal']")
    private WebElement continueShoppingButton;

    @FindBy(css = ".single-products .price h2")
    private List<WebElement> productPrices;



    public List<String> getProductPrices() {
        List<String> prices = new ArrayList<>();
        for (WebElement price : productPrices) {
            prices.add(price.getText());  // Dodajemy tekst ceny do listy
        }
        return prices;
    }

    public ProductsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void waitForPageToLoad() {
        wait.until(ExpectedConditions.visibilityOf(allProductsHeader));
    }

    public void searchForProduct(String productName) {
        sendKeys(searchInput, productName);
        click(searchButton);
    }


    public boolean hasResults() {
        return !productTitles.isEmpty();
    }

    public void hoverAndAddToCart(int productIndex) {
        WebElement product = products.get(productIndex);
        Actions actions = new Actions(driver);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", product);

        actions.moveToElement(product).perform();

        WebElement addToCartButton = addToCartButtons.get(productIndex);
        click(addToCartButton);
        waitForElement(addToCartButton);

    }

    public void continueShopping() {
        click(continueShoppingButton);
    }
    public void goToCartPage() {
        click(cartButton);
    }
}
