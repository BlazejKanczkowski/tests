package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TestCasesPage extends AbstractPage {

    @FindBy(css = "h2.title.text-center b")
    private WebElement testCasesHeader;

    public TestCasesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isTestCasesPageVisible() {
        return isElementDisplayed(testCasesHeader);
    }
}
