package tests;

import com.zebrunner.carina.core.AbstractTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest extends AbstractTest {

    protected WebDriver driver;

    protected abstract void initPages();

    @BeforeMethod
    public void initTest() {
        driver = getDriver();
        initPages();
    }

}
