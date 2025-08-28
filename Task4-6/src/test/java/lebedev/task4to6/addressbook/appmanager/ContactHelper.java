package lebedev.task4to6.addressbook.appmanager;

import lebedev.task4to6.addressbook.model.ContactData;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToHomePage() {
        clickOnElement(By.linkText("home page"));
    }

    public void submitContactCreation() {
        clickOnElement(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void fillContactForm(ContactData contactData) {
        typeIntoField(By.name("firstname"), contactData.firstName());
        typeIntoField(By.name("middlename"), contactData.middleName());
        typeIntoField(By.name("lastname"), contactData.lastName());
        typeIntoField(By.name("nickname"), contactData.nickname());
        typeIntoField(By.name("company"), contactData.company());
        typeIntoField(By.name("email"), contactData.email());
    }

    public void initContactDeletion() {
        clickOnElement(By.xpath("(//input[@value='Delete'])"));
    }

    public void selectContact() {
        clickOnElement(By.xpath("//input[@title='Select (TestFirstName TestLastName)']"));
    }

    public void acceptContactDeletion() {
      Alert contactDeletionAlert = webDriver.switchTo().alert();
      assertEquals("Delete 1 addresses?", contactDeletionAlert.getText());
      contactDeletionAlert.accept();
    }

    public void initContactEditing () {
        clickOnElement(By.xpath("//img[@alt='Edit']"));
    }

    public void submitContactEditing () {
        clickOnElement(By.name("update"));
    }

    public void clearContactForm () {
        clearField(By.name("firstname"));
        clearField(By.name("middlename"));
        clearField(By.name("lastname"));
        clearField(By.name("nickname"));
        clearField(By.name("company"));
        clearField(By.name("email"));
    }
}
