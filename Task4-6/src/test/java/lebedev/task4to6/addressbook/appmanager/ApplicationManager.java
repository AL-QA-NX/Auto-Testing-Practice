package lebedev.task4to6.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;

public class ApplicationManager {

    private final Browser selectedBrowser;
    public WebDriver wd;

    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private ContactHelper contactHelper;

    public ApplicationManager(Browser selectedBrowser) {
        this.selectedBrowser = selectedBrowser;
    }

    public void initialize() {
        if (selectedBrowser.equals(Browser.FIREFOX)) {
            wd = new FirefoxDriver();
        } else if (selectedBrowser.equals(Browser.CHROME)) {
            wd = new ChromeDriver();
        } else if (selectedBrowser.equals(Browser.EDGE)) {
            wd = new EdgeDriver();
        }

        sessionHelper = new SessionHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        groupHelper = new GroupHelper(wd);
        contactHelper = new ContactHelper(wd);
        wd.get("http://localhost/addressbook/");
        sessionHelper.login("admin","secret");
    }

    public void stop() {
        sessionHelper.logout();
        wd.quit();
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper(){
        return navigationHelper;
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }
}
