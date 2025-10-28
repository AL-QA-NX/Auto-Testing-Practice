package lebedev.addressbook.tests;

import lebedev.addressbook.model.ContactData;
import lebedev.addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEditingTest extends TestBase {

    @BeforeMethod
    public static void contactEditingPreconditionsCheck() {
        appManager.goTo().homePage();
        if (appManager.contact().all().isEmpty()){
            appManager.goTo().contactPage();
            appManager.contact().create(new ContactData()
                    .withFirstName("TestFirstName").withMiddleName("TestMiddleName")
                    .withLastName("TestLastName").withNickname("TestNickname")
                    .withCompany("TestCompany").withAddress("TestAddress").
                    withFirstEmail("TestEmail").withGroup("GroupName")
                    .withHomePhone("123456").withMobilePhone("+79854612312").withWorkPhone("123456789"));
        }
    }

    @Test
    public void contactEditing () {
        Contacts beforeContactList = appManager.contact().all();
        ContactData editedContact = beforeContactList.iterator().next();
        ContactData contactDataForEditing = (new ContactData().withId(editedContact.getId())
                .withFirstName("Test1Name").withMiddleName("Test2Name")
                .withLastName("Test3Name").withNickname("Test4Nickname")
                .withCompany("Test5Company").withAddress("Test6Address")
                .withFirstEmail("test7@mail.com").withGroup("GroupName")
                .withHomePhone("")).withMobilePhone("").withWorkPhone("");

        appManager.contact().edit(contactDataForEditing);
        assertThat(appManager.contact().count(), equalTo(beforeContactList.size()));
        Contacts afterContactList = appManager.contact().all();
        assertThat(afterContactList, equalTo(beforeContactList.without(editedContact).withAdded(contactDataForEditing)));
    }
}
