package lebedev.addressbook.tests;

import lebedev.addressbook.appmanager.ApplicationManager;
import org.openqa.selenium.remote.Browser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    private static final String browserName = System.getProperty("browser", "chrome").toLowerCase();

    protected static final ApplicationManager appManager = new ApplicationManager(getBrowser(browserName));

    private static Browser getBrowser(String browser) {
        return switch (browser) {
            case "chrome" -> Browser.CHROME;
            case "firefox" -> Browser.FIREFOX;
            case "edge" -> Browser.EDGE;
            default -> Browser.CHROME;
        };
    }

    @BeforeSuite
    public void setUp() throws IOException {
        appManager.initialize();
    }

    @AfterSuite (alwaysRun = true)
    public void tearDown() {
        appManager.stop();
    }

    @BeforeMethod
    public void logTestStart(Method method, Object[] objects){
        logger.info("Start test {}", method.getName() + " with values " + Arrays.asList(objects));
    }

    @AfterMethod (alwaysRun = true)
    public void logTestFinish(Method method){
        logger.info("Finished test {}", method.getName());
    }

}
