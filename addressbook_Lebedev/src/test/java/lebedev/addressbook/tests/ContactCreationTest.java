package lebedev.addressbook.tests;

import lebedev.addressbook.model.ContactData;
import lebedev.addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

    @BeforeMethod
    public void contactCreationPreconditionsCheck() {
        appManager.goTo().homePage();
    }

    @Test
    public void contactCreation() {
        Contacts beforeContactList = appManager.contact().all();
        ContactData contactThatWillBeCreated = new ContactData()
                .withFirstName("TestFirstName").withMiddleName("TestMiddleName")
                .withLastName("TestLastName").withNickname("TestNickname")
                .withCompany("TestCompany").withAddress("TestAddress")
                .withFirstEmail("TestEmail").withSecondEmail("TestEmailSecond").withThirdEmail("TestEmailThird")
                .withGroup("GroupName").withHomePhone("123456").withMobilePhone("+79854612312").withWorkPhone("123456789");

        appManager.goTo().contactPage();
        appManager.contact().create(contactThatWillBeCreated);

        assertThat(appManager.contact().count(), equalTo(beforeContactList.size() + 1));
        Contacts afterContactList = appManager.contact().all();

        assertThat(afterContactList, equalTo(beforeContactList.withAdded(contactThatWillBeCreated.withId(afterContactList.stream()
                .mapToInt(ContactData::getId).max().getAsInt()))));
    }
}
