package tests;

import com.example.pages.HomePage;
import com.example.pages.TestCasesPage;
import com.example.utils.DriverFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SubscriptionTest extends AbstractTest {

    private HomePage homePage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        homePage = new HomePage(driver);

    }
    @Test
    public void testSubscriptionOnHomePage() {

        homePage.open();
        homePage.acceptCookiesIfPresent();
        homePage.scrollToSubscriptionSection();
        Assert.assertTrue(homePage.isSubscriptionSectionVisible(), "Subscription section is not visible!");

        String randomEmail = "test" + System.currentTimeMillis() + "@example.com";
        homePage.enterEmailForSubscription(randomEmail);
        homePage.clickSubscribeButton();

        Assert.assertTrue(homePage.isSubscriptionSuccessMessageDisplayed(), "Subscription success message was not displayed!");
    }
}
