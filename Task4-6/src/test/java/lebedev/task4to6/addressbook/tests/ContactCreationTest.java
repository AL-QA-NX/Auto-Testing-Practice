package lebedev.task4to6.addressbook.tests;

import lebedev.task4to6.addressbook.model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactCreationTest extends TestBase {

    @Test
    public void contactCreation() {
        app.getNavigationHelper().goToContactPage();
        app.getContactHelper().fillContactForm(new ContactData("TestFirstName", "TestMiddleName", "TestLastName", "Test", "TestCompany", "test@mail.com", "testName"), true);
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToHomePage();
    }
}
