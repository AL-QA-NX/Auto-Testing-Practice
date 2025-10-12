package lebedev.addressbook.tests;

import lebedev.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

public class ContactEditingTest extends TestBase {

    @Test
    public void contactEditing () {
        ContactData contactDataForEditing = new ContactData("TestFirstName",
                "TestMiddleName", "TestLastName", "Test",
                "TestCompany", "test@mail.com", null);
        if (! appManager.getContactHelper().isContactExist()){
            appManager.getNavigationHelper().goToContactPage();
            appManager.getContactHelper().createContact(contactDataForEditing);
        }
        List<ContactData> beforeContactList = appManager.getContactHelper().getContactList();
        appManager.getContactHelper().initContactEditing(beforeContactList.size() - 1);
        appManager.getContactHelper().fillContactForm(contactDataForEditing, false);
        appManager.getContactHelper().submitContactEditing();
        appManager.getContactHelper().returnToHomePage();
        List<ContactData> afterContactList = appManager.getContactHelper().getContactList();
        Assertions.assertEquals(afterContactList.size(),beforeContactList.size());

        beforeContactList.remove(beforeContactList.size() - 1);
        beforeContactList.add(contactDataForEditing);
        Comparator<? super ContactData> byLastAndFirstName = Comparator.comparing(ContactData::lastName).
                thenComparing(ContactData::firstName);
        beforeContactList.sort(byLastAndFirstName);
        afterContactList.sort(byLastAndFirstName);
        Assertions.assertEquals(beforeContactList,afterContactList);
    }
}
