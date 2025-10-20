package lebedev.addressbook.tests;

import lebedev.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactEditingTest extends TestBase {

    @BeforeMethod
    public static void contactEditingPreconditionsCheck() {
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
    public void contactEditing () {
        List<ContactData> beforeContactList = appManager.contact().list();
        ContactData contactDataForEditing = (new ContactData()
                .withFirstName("TestFirstName").withMiddleName("TestMiddleName")
                .withLastName("TestLastName").withNickname("Test")
                .withCompany("TestCompany").withEmail("test@mail.com"));
        int index = beforeContactList.size() - 1;

        appManager.contact().edit(index, contactDataForEditing);
        appManager.goTo().homePage();

        List<ContactData> afterContactList = appManager.contact().list();
        Assertions.assertEquals(afterContactList.size(),beforeContactList.size());

        beforeContactList.remove(index);
        beforeContactList.add(contactDataForEditing);
        Comparator<? super ContactData> byLastAndFirstName = Comparator.comparing(ContactData::getLastName).
                thenComparing(ContactData::getFirstName);
        beforeContactList.sort(byLastAndFirstName);
        afterContactList.sort(byLastAndFirstName);
        Assertions.assertEquals(beforeContactList,afterContactList);
    }
}
