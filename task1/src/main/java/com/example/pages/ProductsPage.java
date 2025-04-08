package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class ProductsPage extends BasePage {

    @FindBy(css = "input[name='search']")
    private WebElement searchInput;

    @FindBy(css = "button#submit_search")
    private WebElement searchButton;

    @FindBy(css = ".productinfo.text-center p")
    private List<WebElement> productTitles;

    @FindBy(xpath = "//h2[normalize-space()='All Products']")
    private WebElement allProductsHeader;

    public ProductsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void waitForPageToLoad() {
        wait.until(ExpectedConditions.visibilityOf(allProductsHeader));
    }

    public void waitForResultsToLoad() {
        wait.until(ExpectedConditions.visibilityOfAllElements(productTitles));
    }

    public void searchForProduct(String productName) {
        searchInput.clear();
        searchInput.sendKeys(productName);
        searchButton.click();
    }

    public List<String> getProductTitles() {
        return productTitles.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public boolean hasResults() {
        return !productTitles.isEmpty();
    }

}
