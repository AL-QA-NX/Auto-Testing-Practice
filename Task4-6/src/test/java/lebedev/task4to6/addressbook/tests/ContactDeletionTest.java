package lebedev.task4to6.addressbook.tests;

import lebedev.task4to6.addressbook.model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactDeletionTest extends TestBase {

  @Test
  public void contactDeletion() {
    if (! app.getContactHelper().isContactExist()){
      app.getNavigationHelper().goToContactPage();
      app.getContactHelper().createContact(new ContactData("TestFirstName",
              "TestMiddleName", "TestLastName", "Test",
              "TestCompany", "test@mail.com", null));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactDeletion();
    app.getContactHelper().acceptContactDeletion();
  }
}
