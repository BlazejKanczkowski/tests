package tests;

import com.example.pages.HomePage;
import com.example.pages.TestCasesPage;
import com.example.utils.DriverFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VerifyTestCasesPageTest extends AbstractTest {

    private HomePage homePage;
    private TestCasesPage testCasesPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        homePage = new HomePage(driver);
        testCasesPage = new TestCasesPage(driver);
    }

    @Test
    public void testVerifyTestCasesPage() {
        homePage.open();
        homePage.acceptCookiesIfPresent();
        Assert.assertTrue(homePage.isSubscriptionSectionVisible(), "Home page is not visible");

        homePage.goToTestCasesPage();
        Assert.assertTrue(testCasesPage.isTestCasesPageVisible(), "Test Cases page is not visible");

    }
}
