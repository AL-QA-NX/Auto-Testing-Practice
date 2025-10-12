package lebedev.addressbook.tests;

import lebedev.addressbook.model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {

    @Test
    public void contactCreation() {
        List<ContactData> beforeContactList = appManager.getContactHelper().getContactList();
        appManager.getNavigationHelper().goToContactPage();
        ContactData contactForList = new ContactData(
                "TestFirstName",
                "TestMiddleName",
                "TestLastName",
                "TestNickname",
                "TestCompany",
                "TestEmail",
                "GroupName"
        );
        appManager.getContactHelper().fillContactForm(contactForList, true);
        appManager.getContactHelper().submitContactCreation();
        appManager.getContactHelper().returnToHomePage();
        List<ContactData> afterContactList = appManager.getContactHelper().getContactList();
        Assertions.assertEquals(afterContactList.size(), beforeContactList.size() + 1);

        beforeContactList.add(contactForList);
        Comparator<? super ContactData> byLastAndFirstName = Comparator.comparing(ContactData::lastName).
                thenComparing(ContactData::firstName);
        beforeContactList.sort(byLastAndFirstName);
        afterContactList.sort(byLastAndFirstName);
        Assertions.assertEquals(beforeContactList, afterContactList);
    }
}
