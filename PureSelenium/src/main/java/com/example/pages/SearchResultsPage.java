package com.example.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class SearchResultsPage extends BasePage {

    @FindBy(css = ".product-title")
    private List<ExtendedWebElement> productTitles;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getProductTitles() {
        return productTitles.stream()
                .map(ExtendedWebElement::getText)
                .collect(Collectors.toList());
    }

    public boolean hasResults() {
        return !productTitles.isEmpty();
    }
}
