package lebedev.addressbook.tests;

import lebedev.addressbook.appmanager.ApplicationManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.Browser;

public class TestBase {

    protected final ApplicationManager appManager = new ApplicationManager(Browser.CHROME);

    @BeforeEach
    public void setUp() {
        appManager.initialize();
    }

    @AfterEach
    public void tearDown() {
        appManager.stop();
    }

}
