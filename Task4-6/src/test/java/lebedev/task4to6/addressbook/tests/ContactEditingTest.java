package lebedev.task4to6.addressbook.tests;

import lebedev.task4to6.addressbook.model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactEditingTest extends TestBase {

    @Test
    public void contactEditing () {
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactEditing();
        app.getContactHelper().clearContactForm();
        app.getContactHelper().fillContactForm(new ContactData("EditFirstName", "EditMiddleName", "EditLastName", "EditTest", "EditCompany", "editTest@mail.com"));
        app.getContactHelper().submitContactEditing();
        app.getContactHelper().returnToHomePage();
    }

}
