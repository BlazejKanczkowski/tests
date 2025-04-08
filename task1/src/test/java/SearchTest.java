
import com.example.pages.HomePage;
import com.example.pages.ProductsPage;
import com.example.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTest {

    private WebDriver driver;
    private HomePage homePage;
    private ProductsPage productsPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        homePage = new HomePage(driver);
        productsPage = new ProductsPage(driver);
    }

    @Test
    public void testSearchForProduct() {
        homePage.open();
        homePage.acceptCookiesIfPresent();
        productsPage = homePage.goToProductsPage();
        productsPage.searchForProduct("jeans");
        Assert.assertTrue(productsPage.hasResults(), "No search results found!");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
