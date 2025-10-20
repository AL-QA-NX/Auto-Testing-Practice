package lebedev.addressbook.appmanager;

import lebedev.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactHelper extends BaseHelper {

    public List<ContactData> list(){
        List<ContactData> contactsGetContactList = new ArrayList <>();
        List<WebElement> elementsContacts = webDriver.findElements(By.cssSelector("tr[name='entry']"));
        for (WebElement element: elementsContacts ) {
            String lastName = element.findElement(By.cssSelector("td:nth-of-type(2)")).getText();
            String firstName = element.findElement(By.cssSelector("td:nth-of-type(3)")).getText();
            ContactData contacts = new ContactData().withFirstName(firstName).withLastName(lastName);
            contactsGetContactList.add (contacts);
        }
        return contactsGetContactList;
    };

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
        typeIntoField(By.name("firstname"), contactData.getFirstName());
        typeIntoField(By.name("middlename"), contactData.getMiddleName());
        typeIntoField(By.name("lastname"), contactData.getLastName());
        typeIntoField(By.name("nickname"), contactData.getNickname());
        typeIntoField(By.name("company"), contactData.getCompany());
        typeIntoField(By.name("email"), contactData.getEmail());

        if (creationOrEditingForm){
            if (contactData.getGroup() !=null) {
                new Select(webDriver.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
            }
        } else {
            Assertions.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void initContactDeletion() {
        clickOnElement(By.xpath("(//input[@value='Delete'])"));
    }

    public void selectContact(int index) {
        webDriver.findElements(By.name("selected[]")).get(index).click();
    }

    public void acceptContactDeletion() {
      Alert contactDeletionAlert = webDriver.switchTo().alert();
      assertEquals("Delete 1 addresses?", contactDeletionAlert.getText());
      contactDeletionAlert.accept();
    }

    public void initContactEditing (int index) {
        webDriver.findElements(By.xpath("//img[@title='Edit']")).get(index).click();
    }

    public void submitContactEditing () {
        clickOnElement(By.name("update"));
    }

    public void create(ContactData contactData) {
        fillContactForm (contactData, true);
        submitContactCreation();
    }

    public void delete(int index) {
        selectContact(index);
        initContactDeletion();
        acceptContactDeletion();
    }

    public void edit(int index, ContactData contactDataForEditing) {
        initContactEditing(index);
        fillContactForm(contactDataForEditing, false);
        submitContactEditing();
    }

    public boolean isContactExist() {
        return isElementPresent(By.name("selected[]"));
    }
}
