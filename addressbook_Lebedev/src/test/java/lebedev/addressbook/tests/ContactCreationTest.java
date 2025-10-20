package lebedev.addressbook.tests;

import lebedev.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {

    @BeforeMethod
    public void contactCreationPreconditionsCheck() {
        appManager.goTo().homePage();
    }

    @Test
    public void contactCreation() {
        List<ContactData> beforeContactList = appManager.contact().list();

        appManager.goTo().contactPage();

        ContactData contactData = new ContactData().withFirstName("TestFirstName").withMiddleName("TestMiddleName")
                .withLastName("TestLastName").withNickname("TestNickname")
                .withCompany("TestCompany").withEmail("TestEmail").withGroup("GroupName");

        appManager.contact().create(contactData);
        appManager.goTo().homePage();

        List<ContactData> afterContactList = appManager.contact().list();
        Assertions.assertEquals(afterContactList.size(), beforeContactList.size() + 1);

        beforeContactList.add(contactData);
        Comparator<? super ContactData> byLastAndFirstName = Comparator.comparing(ContactData::getLastName).
                thenComparing(ContactData::getFirstName);
        beforeContactList.sort(byLastAndFirstName);
        afterContactList.sort(byLastAndFirstName);
        Assertions.assertEquals(beforeContactList, afterContactList);
    }
}
