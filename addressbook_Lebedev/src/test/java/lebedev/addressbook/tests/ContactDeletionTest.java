package lebedev.addressbook.tests;

import lebedev.addressbook.model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactDeletionTest extends TestBase {

  @Test
  public void contactDeletion() {
    if (! appManager.getContactHelper().isContactExist()){
      appManager.getNavigationHelper().goToContactPage();
      appManager.getContactHelper().createContact(new ContactData("TestFirstName",
              "TestMiddleName", "TestLastName", "Test",
              "TestCompany", "test@mail.com", null));
    }
    appManager.getContactHelper().selectContact();
    appManager.getContactHelper().initContactDeletion();
    appManager.getContactHelper().acceptContactDeletion();
  }
}
