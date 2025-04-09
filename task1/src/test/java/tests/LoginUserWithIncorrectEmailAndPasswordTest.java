package tests;

import com.example.pages.HomePage;
import com.example.pages.LoginPage;
import com.example.utils.DriverFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginUserWithIncorrectEmailAndPasswordTest extends AbstractTest {

    private HomePage homePage;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        homePage = new HomePage(driver);
    }

    @Test
    public void testLoginWithIncorrectCredentials() {
        homePage.open();
        homePage.acceptCookiesIfPresent();

        Assert.assertTrue(homePage.isSubscriptionSectionVisible(), "Home page is not visible");

        homePage.goToLoginPage();

        loginPage = new LoginPage(driver);

        Assert.assertTrue(loginPage.isLoginHeaderVisible(), "'Login to your account' header is not visible");

        String wrongEmail = "wrongemail@example.com";
        String wrongPassword = "wrongpassword";
        loginPage.enterLoginCredentials(wrongEmail, wrongPassword);

        loginPage.clickLoginButton();

        Assert.assertTrue(loginPage.isLoginErrorVisible(), "Error message 'Your email or password is incorrect!' is not visible");
    }
}
