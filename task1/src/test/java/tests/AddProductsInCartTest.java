package tests;

import com.example.pages.HomePage;
import com.example.pages.CartPage;
import com.example.pages.ProductsPage;
import com.example.utils.DriverFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddProductsInCartTest extends AbstractTest {

    private HomePage homePage;
    private CartPage cartPage;
    private ProductsPage productsPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        homePage = new HomePage(driver);
        cartPage = new CartPage(driver);
        productsPage = new ProductsPage(driver);
    }

    @Test
    public void testAddProductsToCart() {
        homePage.open();
        homePage.acceptCookiesIfPresent();
        Assert.assertTrue(homePage.isSubscriptionSectionVisible(), "Home page is not visible");
        productsPage = homePage.goToProductsPage();
        productsPage.waitForPageToLoad();
        productsPage.hoverAndAddToCart(0);
        productsPage.continueShopping();
        productsPage.hoverAndAddToCart(1);
        productsPage.goToCartPage();
        Assert.assertTrue(cartPage.isProductInCart(0), "First product is not in the cart");
        Assert.assertTrue(cartPage.isProductInCart(1), "Second product is not in the cart");
        Assert.assertEquals(cartPage.getProductPriceFromCart(0), "Rs. 500", "Price of the first product is incorrect");
        Assert.assertEquals(cartPage.getProductPriceFromCart(1), "Rs. 400", "Price of the second product is incorrect");
        Assert.assertTrue(cartPage.isQuantityCorrect(0, 1), "Quantity of the first product is incorrect");
        Assert.assertTrue(cartPage.isQuantityCorrect(1, 1), "Quantity of the second product is incorrect");
        Assert.assertEquals(cartPage.getTotalPriceFromCart(), "Rs. 900", "Total price is incorrect");
    }
}
