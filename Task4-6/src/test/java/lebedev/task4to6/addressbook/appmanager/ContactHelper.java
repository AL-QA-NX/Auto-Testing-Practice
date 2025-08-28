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
        click(By.linkText("home page"));
    }

    public void submitContactCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.firstName());
        type(By.name("middlename"), contactData.middleName());
        type(By.name("lastname"), contactData.lastName());
        type(By.name("nickname"), contactData.nickname());
        type(By.name("company"), contactData.company());
        type(By.name("email"), contactData.email());
    }

    public void submitContactDeletion() {
        click(By.xpath("(//input[@value='Delete'])"));
    }

    public void selectContact() {
        click(By.xpath("//input[@title='Select (TestFirstName TestLastName)']"));
    }

    public void acceptContactDeletion() {
      Alert contactDeletionAlert = wd.switchTo().alert();
      assertEquals("Delete 1 addresses?", contactDeletionAlert.getText());
      contactDeletionAlert.accept();
    }
}
