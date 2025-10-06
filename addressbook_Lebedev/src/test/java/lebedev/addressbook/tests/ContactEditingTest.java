package lebedev.addressbook.tests;

import lebedev.addressbook.model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactEditingTest extends TestBase {

    @Test
    public void contactEditing () {
        if (! appManager.getContactHelper().isContactExist()){
            appManager.getNavigationHelper().goToContactPage();
            appManager.getContactHelper().createContact(new ContactData("TestFirstName",
                "TestMiddleName", "TestLastName", "Test",
                "TestCompany", "test@mail.com", null));
        }
        appManager.getContactHelper().initContactEditing();
        appManager.getContactHelper().fillContactForm(new ContactData("TestFirstName",
                "TestMiddleName", "TestLastName", "Test",
                "TestCompany", "test@mail.com", null), false);
        appManager.getContactHelper().submitContactEditing();
        appManager.getContactHelper().returnToHomePage();
    }
}
