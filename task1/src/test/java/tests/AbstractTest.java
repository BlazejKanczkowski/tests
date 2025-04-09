package tests;

import com.example.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractTest {

    protected WebDriver driver;
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        logger.info("Driver initialized: {}", driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            DriverFactory.quitDriver();
            logger.info("Driver quit successfully");
        }
    }
}
