package lebedev.addressbook.dataGenerators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import lebedev.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int contactDataCount;

    @Parameter(names = "-f", description = "Target file")
    public String contactDataFileAddress;

    @Parameter(names = "-t", description = "Data type")
    public String contactDataType;


    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommanderContactGenerator = new JCommander(generator);
        try {
            jCommanderContactGenerator.parse(args);
        } catch (ParameterException exception) {
            jCommanderContactGenerator.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contactData = generateContacts(contactDataCount);
        if (contactDataType.equals("csv")) {
            saveAsCSV(contactData, new File(contactDataFileAddress));
        } else if (contactDataType.equals("xml")) {
            saveAsXML(contactData, new File(contactDataFileAddress));
        } else if (contactDataType.equals("json")) {
            saveAsJSON(contactData, new File(contactDataFileAddress));
        } else {
            System.out.println("Unrecognized type " + contactDataType + ". Choose between csv, xml or json.");
        }
    }

    private void saveAsJSON(List<ContactData> contactData, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contactData);
        try (Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
    }

    private void saveAsXML(List<ContactData> contactData, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.processAnnotations(ContactData.class);
        String xml = xStream.toXML(contactData);
        try (Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }
    }

    private void saveAsCSV(List<ContactData> contactData, File file) throws IOException {
        try (Writer writer = new FileWriter(file)) {
            for (ContactData contact : contactData) {
                writer.write(String.format("%s; %s; %s; %s; %s; %s; %s; %s; %s; %s; %s; %s; %s\n", contact.getFirstName(),contact.getMiddleName(),contact.getLastName(),
                        contact.getNickname(),contact.getCompany(),contact.getAddress(),
                        contact.getFirstEmail(), contact.getSecondEmail(), contact.getThirdEmail(), contact.getGroup(),
                        contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone()));
            }
        }
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFirstName(String.format("GeneratedContactFirstName %s", i))
                    .withMiddleName(String.format("GeneratedContactMiddleName %s", i))
                    .withLastName(String.format("GeneratedContactLastName %s", i))
                    .withNickname(String.format("GeneratedContactNickname %s", i))
                    .withCompany(String.format("GeneratedContactCompany %s", i))
                    .withAddress(String.format("GeneratedContactAddress %s", i))
                    .withFirstEmail(generateEmail())
                    .withSecondEmail(generateEmail())
                    .withThirdEmail(generateEmail())
                    .withGroup("GroupName").withHomePhone(generatePhone(PhoneType.home))
                    .withMobilePhone(generatePhone(PhoneType.mobile))
                    .withWorkPhone(generatePhone(PhoneType.work)));
        }
        return contacts;
    }

    public enum PhoneType{
        home,
        mobile,
        work
    }

    private String generatePhone (PhoneType type) {
        Random random = new Random();

        switch (type) {
            case home:
                StringBuilder home = new StringBuilder("2");
                for (int i = 0; i < 6; i++) {
                    home.append(random.nextInt(10));
                }
                return home.toString();

            case mobile:
                StringBuilder mobile = new StringBuilder("+79");
                for (int i = 0; i < 9; i++) {
                    mobile.append(random.nextInt(10));
                }
                return mobile.toString();

            case work:
                StringBuilder work = new StringBuilder();
                for (int i=0; i < 6; i++){
                    work.append(random.nextInt(6));
                }
                return work.toString();
        }
        throw new IllegalArgumentException("Unknow phone type: " + type);
    }

    private String generateEmail() {
        String chars = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder localPart = new StringBuilder();

        // длина имени — от 5 до 12 символов
        int length = 5 + random.nextInt(8);

        for (int i = 0; i < length; i++) {
            localPart.append(chars.charAt(random.nextInt(chars.length())));
        }

        // список возможных доменов
        String[] domains = {"gmail.com", "yahoo.com", "mail.ru", "example.com", "outlook.com"};

        // выбираем случайный домен
        String domain = domains[random.nextInt(domains.length)];

        return localPart + "@" + domain;
    }
}
