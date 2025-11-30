package lebedev.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ApplicationManager {

    private final Browser selectedBrowser;
    private final Properties properties;
    public WebDriver webDriver;

    private SessionHelper sessionHelper;

    private NavigationHelper navigationHelper;

    private GroupHelper groupHelper;

    private ContactHelper contactHelper;

    public ApplicationManager(Browser selectedBrowser) {
        this.selectedBrowser = selectedBrowser;
        properties = new Properties();
    }

    public void initialize() throws IOException {
        String target = System.getProperty("target","local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
        if (selectedBrowser.equals(Browser.FIREFOX)) {
            webDriver = new FirefoxDriver();
        } else if (selectedBrowser.equals(Browser.CHROME)) {
            webDriver = new ChromeDriver();
        } else if (selectedBrowser.equals(Browser.EDGE)) {
            webDriver = new EdgeDriver();
        }

        sessionHelper = new SessionHelper(webDriver);
        navigationHelper = new NavigationHelper(webDriver);
        groupHelper = new GroupHelper(webDriver);
        contactHelper = new ContactHelper(webDriver);
        webDriver.get(properties.getProperty("web.baseUrl"));
        sessionHelper.login(properties.getProperty("web.adminLogin"),properties.getProperty("web.adminPassword"));
    }

    public void stop() {
        sessionHelper.logout();
        webDriver.quit();
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public GroupHelper group() {
        return groupHelper;
    }

    public ContactHelper contact() {
        return contactHelper;
    }
}
