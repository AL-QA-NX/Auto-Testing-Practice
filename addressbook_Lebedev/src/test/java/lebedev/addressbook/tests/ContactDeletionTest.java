package lebedev.addressbook.tests;

import lebedev.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ContactDeletionTest extends TestBase {

  @BeforeMethod
  public static void contactDeletionPreconditionsCheck() {
    appManager.goTo().homePage();
    if (appManager.contact().list().isEmpty()){
      appManager.goTo().contactPage();
      appManager.contact().create(new ContactData()
                      .withFirstName("TestFirstName").withMiddleName("TestMiddleName")
                      .withLastName("TestLastName").withNickname("Test")
                      .withCompany("TestCompany").withEmail("test@mail.com"));
    }
  }

  @Test
  public void contactDeletion() {
    List<ContactData> beforeContactList = appManager.contact().list();
    int index = beforeContactList.size() - 1;

    appManager.contact().delete(index);
    appManager.goTo().homePage();

    List<ContactData> afterContactList = appManager.contact().list();
    Assertions.assertEquals(afterContactList.size(), beforeContactList.size() - 1);

    beforeContactList.remove(index);
    Assertions.assertEquals(beforeContactList, afterContactList);
  }

}
