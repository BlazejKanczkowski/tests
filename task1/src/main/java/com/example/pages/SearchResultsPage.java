package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class SearchResultsPage extends AbstractPage {

    @FindBy(css = ".product-title")
    private List<WebElement> productTitles;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public List<String> getProductTitles() {
        return productTitles.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public boolean hasResults() {
        return !productTitles.isEmpty();
    }
}
