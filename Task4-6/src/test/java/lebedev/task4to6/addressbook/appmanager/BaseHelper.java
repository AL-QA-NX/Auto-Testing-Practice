package lebedev.task4to6.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

public class BaseHelper {

    protected WebDriver webDriver;

    public BaseHelper(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    protected void typeIntoField(By locator, String text) {
        webDriver.findElement(locator).sendKeys(text);
    }

    protected void clickOnElement(By locator) {
        webDriver.findElement (locator).click();
    }

    protected void clearField (By locator) {
        webDriver.findElement(locator).clear();
    }

    public boolean isAlertPresent(){
        try {
            webDriver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
