package lebedev.addressbook.tests;

import lebedev.addressbook.model.ContactData;
import lebedev.addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTest extends TestBase {

  @BeforeMethod
  public static void contactDeletionPreconditionsCheck() {
    appManager.goTo().homePage();
    if (appManager.contact().all().isEmpty()){
      appManager.goTo().contactPage();
      appManager.contact().create(new ContactData()
              .withFirstName("TestFirstName").withMiddleName("TestMiddleName")
              .withLastName("TestLastName").withNickname("TestNickname")
              .withCompany("TestCompany").withAddress("TestAddress").
              withFirstEmail("TestEmail").withGroup("GroupName")
              .withHomePhone("123456").withMobilePhone("+79854612312").withWorkPhone("123456789"));
    }
  }

  @Test
  public void contactDeletion() {
    Contacts beforeContactList = appManager.contact().all();
    ContactData deletedContact = beforeContactList.iterator().next();

    appManager.contact().delete(deletedContact);
    appManager.goTo().homePage();
    assertThat(appManager.contact().count(), equalTo(beforeContactList.size() - 1));
    Contacts afterContactList = appManager.contact().all();
    assertThat(afterContactList, equalTo(beforeContactList.without(deletedContact)));
  }
}
