package tests;

import com.example.pages.HomePage;
import com.example.pages.TestCasesPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VerifyTestCasesPageTest extends BaseTest {

    private HomePage homePage;
    private TestCasesPage testCasesPage;

    @Override
    protected void initPages() {
        homePage = new HomePage(getDriver());
        testCasesPage = new TestCasesPage(getDriver());
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
