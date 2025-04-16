package tests;

import com.example.pages.HomePage;
import com.example.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    private HomePage homePage;
    private ProductsPage productsPage;


    @Override
    protected void initPages() {
        homePage = new HomePage(getDriver());
        productsPage = new ProductsPage(getDriver());
    }

    @Test
    public void testSearchForProduct() {
        homePage.open();
        homePage.acceptCookiesIfPresent();

        productsPage = homePage.goToProductsPage();
        productsPage.searchForProduct("jeans");

        Assert.assertTrue(productsPage.hasResults(), "No search results found!");
    }
}
