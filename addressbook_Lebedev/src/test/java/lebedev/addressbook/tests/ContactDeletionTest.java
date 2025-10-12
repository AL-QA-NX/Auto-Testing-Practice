package lebedev.addressbook.tests;

import lebedev.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ContactDeletionTest extends TestBase {

  @Test
  public void contactDeletion() {
    if (! appManager.getContactHelper().isContactExist()){
      appManager.getNavigationHelper().goToContactPage();
      appManager.getContactHelper().createContact(new ContactData("TestFirstName",
              "TestMiddleName", "TestLastName", "Test",
              "TestCompany", "test@mail.com", null));
    }
    List<ContactData> beforeContactList = appManager.getContactHelper().getContactList();
    appManager.getContactHelper().selectContact(beforeContactList.size() - 1);
    appManager.getContactHelper().initContactDeletion();
    appManager.getContactHelper().acceptContactDeletion();
    WebDriverWait wait = new WebDriverWait(appManager.webDriver, Duration.ofSeconds(4));
    wait.until(ExpectedConditions.urlToBe("http://localhost/addressbook/"));
    List<ContactData> afterContactList = appManager.getContactHelper().getContactList();
    Assertions.assertEquals(afterContactList.size(), beforeContactList.size() - 1);

    beforeContactList.remove(beforeContactList.size() - 1);
    Assertions.assertEquals(beforeContactList, afterContactList);
  }
}
