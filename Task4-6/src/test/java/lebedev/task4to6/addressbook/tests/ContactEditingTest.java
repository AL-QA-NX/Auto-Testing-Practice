package lebedev.task4to6.addressbook.tests;

import lebedev.task4to6.addressbook.model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactEditingTest extends TestBase {

    @Test
    public void contactEditing () {
        if (! app.getContactHelper().isContactExist()){
            app.getNavigationHelper().goToContactPage();
            app.getContactHelper().createContact(new ContactData("TestFirstName",
                "TestMiddleName", "TestLastName", "Test",
                "TestCompany", "test@mail.com", null));
        }
        app.getContactHelper().initContactEditing();
        app.getContactHelper().fillContactForm(new ContactData("TestFirstName",
                "TestMiddleName", "TestLastName", "Test",
                "TestCompany", "test@mail.com", null), false);
        app.getContactHelper().submitContactEditing();
        app.getContactHelper().returnToHomePage();
    }
}
