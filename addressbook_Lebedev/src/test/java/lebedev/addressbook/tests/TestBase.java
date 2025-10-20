package lebedev.addressbook.tests;

import lebedev.addressbook.appmanager.ApplicationManager;
import org.openqa.selenium.remote.Browser;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {

    protected static final ApplicationManager appManager = new ApplicationManager(Browser.CHROME);

    @BeforeSuite
    public void setUp() {
        appManager.initialize();
    }

    @AfterSuite
    public void tearDown() {
        appManager.stop();
    }

}
