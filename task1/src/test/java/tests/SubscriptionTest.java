package tests;

import com.example.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SubscriptionTest extends BaseTest {

    private HomePage homePage;


    @Override
    protected void initPages() {
        homePage = new HomePage(getDriver());
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
