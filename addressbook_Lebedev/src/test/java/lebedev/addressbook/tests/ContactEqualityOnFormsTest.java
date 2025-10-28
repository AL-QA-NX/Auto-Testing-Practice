package lebedev.addressbook.tests;

import lebedev.addressbook.model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEqualityOnFormsTest extends TestBase {

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
    public void checkValueEquality() {
        ContactData contactList = appManager.contact().all().iterator().next();
        ContactData contactCheckEquality = appManager.contact().infoFromEditForm(contactList);

        assertThat(contactList.getFirstName(),equalTo(contactCheckEquality.getFirstName()));
        //assertThat(contactList.getMiddleName(),equalTo(contactCheckEquality.getMiddleName()));
        assertThat(contactList.getLastName(),equalTo(contactCheckEquality.getLastName()));
        //assertThat(contactList.getNickname(),equalTo(contactCheckEquality.getNickname()));
        //assertThat(contactList.getCompany(),equalTo(contactCheckEquality.getCompany()));
        assertThat(contactList.getAddress(), equalTo(contactCheckEquality.getAddress()));
        assertThat(contactList.getAllEmail(), equalTo(mergeEmails(contactCheckEquality)));
        assertThat(contactList.getAllPhones(), equalTo(mergePhones(contactCheckEquality)));
    }

    private String mergeEmails (ContactData contact){
        return Stream.of(contact.getFirstEmail(),contact.getSecondEmail(),contact.getThirdEmail()).filter((s -> !s.isEmpty()))
                .collect(Collectors.joining("\n"));
    }

    private String mergePhones(ContactData contact) {
        return Stream.of(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone()).filter((s -> !s.isEmpty()))
                .map(ContactEqualityOnFormsTest::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned (String phone){
        return phone.replaceAll("\\s","").replaceAll("[-()]","");
    }

}
