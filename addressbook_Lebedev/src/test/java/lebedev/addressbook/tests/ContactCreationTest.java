package lebedev.addressbook.tests;

import lebedev.addressbook.model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactCreationTest extends TestBase {

    @Test
    public void contactCreation() {
        appManager.getNavigationHelper().goToContactPage();
        appManager.getContactHelper().fillContactForm(new ContactData("TestFirstName", "TestMiddleName", "TestLastName", "Test", "TestCompany", "test@mail.com", "testName"), true);
        appManager.getContactHelper().submitContactCreation();
        appManager.getContactHelper().returnToHomePage();
    }
}
