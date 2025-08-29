package lebedev.task4to6.addressbook.appmanager;

import lebedev.task4to6.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

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

    public void fillContactForm(ContactData contactData, boolean creationOrEditingForm) {
        typeIntoField(By.name("firstname"), contactData.firstName());
        typeIntoField(By.name("middlename"), contactData.middleName());
        typeIntoField(By.name("lastname"), contactData.lastName());
        typeIntoField(By.name("nickname"), contactData.nickname());
        typeIntoField(By.name("company"), contactData.company());
        typeIntoField(By.name("email"), contactData.email());

        if (creationOrEditingForm){
            if (contactData.group() !=null) {
                new Select(webDriver.findElement(By.name("new_group"))).selectByVisibleText(contactData.group());
            }
        } else {
            Assertions.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void initContactDeletion() {
        clickOnElement(By.xpath("(//input[@value='Delete'])"));
    }

    public void selectContact() {
        clickOnElement(By.name("selected[]"));
    }

    public void acceptContactDeletion() {
      Alert contactDeletionAlert = webDriver.switchTo().alert();
      assertEquals("Delete 1 addresses?", contactDeletionAlert.getText());
      contactDeletionAlert.accept();
    }

    public void initContactEditing () {
        clickOnElement(By.xpath("//img[@title='Edit']"));
    }

    public void submitContactEditing () {
        clickOnElement(By.name("update"));
    }

    public void createContact(ContactData contactData) {
        fillContactForm (contactData, true);
        submitContactCreation();
        returnToHomePage();
    }

    public boolean isContactExist() {
        return isElementPresent(By.name("selected[]"));
    }
}
