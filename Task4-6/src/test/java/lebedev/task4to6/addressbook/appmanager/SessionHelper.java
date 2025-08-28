package lebedev.task4to6.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends BaseHelper {

    public SessionHelper(WebDriver wd) {
        super(wd);
    }

    public void login(String username, String password) {
        typeIntoField(By.name("user"),username);
        typeIntoField(By.name("pass"), password);
        clickOnElement(By.xpath("//input[@value='Login']"));
    }

    public void logout() {
        clickOnElement(By.linkText("Logout"));
    }
}
