package tests;

import com.example.pages.CartPage;
import com.example.pages.HomePage;
import com.example.utils.DriverFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartSubscriptionTest extends AbstractTest {

    private HomePage homePage;
    private CartPage cartPage;

    @BeforeMethod
    public void setUp() {
        homePage = new HomePage(DriverFactory.getDriver());
        cartPage = new CartPage(DriverFactory.getDriver());
    }

    @Test
    public void testSubscriptionInCartPage() {
        homePage.open();
        homePage.acceptCookiesIfPresent();
        Assert.assertTrue(homePage.isSubscriptionSectionVisible(), "Home page is not visible");

        homePage.goToCartPage();

        cartPage.scrollToSubscriptionSection();

        Assert.assertTrue(cartPage.isSubscriptionSectionVisible(), "Subscription section is not visible in Cart page");
        String randomEmail = "test" + System.currentTimeMillis() + "@example.com";
        cartPage.subscribe(randomEmail);
        Assert.assertTrue(cartPage.isSubscriptionSuccessMessageVisible(), "Success message is not visible after subscription");
    }
}
