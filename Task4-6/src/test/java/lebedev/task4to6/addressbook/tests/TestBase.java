package lebedev.task4to6.addressbook.tests;

import lebedev.task4to6.addressbook.appmanager.ApplicationManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.Browser;

public class TestBase {

    protected final ApplicationManager app = new ApplicationManager(Browser.CHROME);

    @BeforeEach
    public void setUp() {
        app.initialize();
    }

    @AfterEach
    public void tearDown() {
        app.stop();
    }

}
