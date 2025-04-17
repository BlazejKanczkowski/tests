package com.example.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class TestCasesPage extends BasePage {

    @FindBy(css = "h2.title.text-center b")
    private ExtendedWebElement testCasesHeader;

    public TestCasesPage(WebDriver driver) {
        super(driver);
    }

    public boolean isTestCasesPageVisible() {
        return testCasesHeader.isElementPresent(5);
    }
}
