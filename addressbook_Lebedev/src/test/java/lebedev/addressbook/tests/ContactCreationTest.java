package lebedev.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import lebedev.addressbook.model.ContactData;
import lebedev.addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

    @BeforeMethod
    public void contactCreationPreconditionsCheck() {
        appManager.goTo().homePage();
    }

    @DataProvider
    public Iterator<Object[]> validContactsCSV() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/generatedContacts.csv"))) {
            List<Object[]> list = new ArrayList<>();
            String line = reader.readLine();
            while (line != null) {
                String[] split = line.split(";");
                list.add(new Object[]{(new ContactData().withFirstName(split[0]).withMiddleName(split[1]).withLastName(split[2])
                        .withNickname(split[3]).withCompany(split[4]).withAddress(split[5])
                        .withFirstEmail(split[6]).withSecondEmail(split[7]).withThirdEmail(split[8]).withGroup(split[9])
                        .withHomePhone(split[10]).withMobilePhone(split[11]).withWorkPhone(split[12]))});
                line = reader.readLine();
            }
            return list.iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validContactsXML() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/generatedContacts.xml"))) {
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xStream = new XStream();
            xStream.processAnnotations(ContactData.class);
            xStream.allowTypes(new Class[]{ContactData.class});
            xStream.allowTypesByWildcard(new String[]{"lebedev.addressbook"});
            List<ContactData> contacts = (List<ContactData>) xStream.fromXML(xml);
            return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validContactsJSON() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/generatedContacts.json"))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
            }.getType()); // List<ContactData>.class
            return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }
    }


    @Test (dataProvider = "validContactsJSON")
    public void contactCreation(ContactData contactThatWillBeCreated) {
        Contacts beforeContactList = appManager.contact().all();

        appManager.goTo().contactPage();
        appManager.contact().create(contactThatWillBeCreated);

        assertThat(appManager.contact().count(), equalTo(beforeContactList.size() + 1));
        Contacts afterContactList = appManager.contact().all();

        assertThat(afterContactList, equalTo(beforeContactList.withAdded(contactThatWillBeCreated.withId(afterContactList.stream()
                .mapToInt(ContactData::getId).max().getAsInt()))));
    }
}
