package com.example.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends BasePage {

    @FindBy(css = "input[name='search']")
    private ExtendedWebElement searchInput;

    @FindBy(css = "button#submit_search")
    private ExtendedWebElement searchButton;

    @FindBy(css = ".productinfo.text-center p")
    private List<ExtendedWebElement> productTitles;

    @FindBy(xpath = "//h2[normalize-space()='All Products']")
    private ExtendedWebElement allProductsHeader;

    @FindBy(css = ".single-products")
    private List<ExtendedWebElement> products;

    @FindBy(css = ".single-products .productinfo .add-to-cart")
    private List<ExtendedWebElement> addToCartButtons;

    @FindBy(css = "a[href='/view_cart']")
    private ExtendedWebElement cartButton;

    @FindBy(css = "button.btn.btn-success.close-modal[data-dismiss='modal']")
    private ExtendedWebElement continueShoppingButton;

    @FindBy(css = ".single-products .price h2")
    private List<ExtendedWebElement> productPrices;

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getProductPrices() {
        List<String> prices = new ArrayList<>();
        for (ExtendedWebElement price : productPrices) {
            prices.add(price.getText());
        }
        return prices;
    }

    public void waitForPageToLoad() {
        allProductsHeader.assertElementPresent(5);
    }

    public void searchForProduct(String productName) {
        searchInput.type(productName);
        searchButton.click();
    }

    public boolean hasResults() {
        return !productTitles.isEmpty();
    }

    public void hoverAndAddToCart(int productIndex) {
        ExtendedWebElement product = products.get(productIndex);
        Actions actions = new Actions(driver);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", product.getElement());

        actions.moveToElement(product.getElement()).perform();

        ExtendedWebElement addToCartButton = addToCartButtons.get(productIndex);
        addToCartButton.click();
    }

    public void continueShopping() {
        continueShoppingButton.click();
    }

    public void goToCartPage() {
        cartButton.click();
    }
}
