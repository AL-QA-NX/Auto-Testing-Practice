package lebedev.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.Objects;

@XStreamAlias("contact")
public class ContactData {

    private int id;

    @Expose
    private String firstName;

    @Expose
    private String middleName;

    @Expose
    private String lastName;

    @Expose
    private String nickname;

    @Expose
    private String company;

    @Expose
    private String address;

    @Expose
    private String allEmail;

    @Expose
    private String firstEmail;

    @Expose
    private String secondEmail;

    @Expose
    private String thirdEmail;

    @Expose
    private String group;

    @Expose
    private String allPhones;

    @Expose
    private String homePhone;

    @Expose
    private String mobilePhone;

    @Expose
    private String workPhone;


    public int getId() {return id;}
    public String getFirstName() {return firstName;}
    public String getMiddleName() {return middleName;}
    public String getLastName() {return lastName;}
    public String getNickname() {return nickname;}
    public String getCompany() {return company;}
    public String getAddress() {return address;}
    public String getAllEmail() {return allEmail;}
    public String getFirstEmail() {return firstEmail;}
    public String getSecondEmail() {return secondEmail;}
    public String getThirdEmail() {return thirdEmail;}
    public String getGroup() {return group;}
    public String getAllPhones() {return allPhones;}
    public String getHomePhone() {return homePhone;}
    public String getMobilePhone() {return mobilePhone;}
    public String getWorkPhone() {return workPhone;}

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public ContactData withCompany(String company) {
        this.company = company;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withAllEmails(String allEmail) {
        this.allEmail = allEmail;
        return this;
    }

    public ContactData withFirstEmail(String firstEmail) {
        this.firstEmail = firstEmail;
        return this;
    }

    public ContactData withSecondEmail(String secondEmail) {
        this.secondEmail = secondEmail;
        return this;
    }

    public ContactData withThirdEmail(String thirdEmail) {
        this.thirdEmail = thirdEmail;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + Objects.hashCode(firstName);
        result = 31 * result + Objects.hashCode(lastName);
        return result;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nickname='" + nickname + '\'' +
                ", company='" + company + '\'' +
                ", address='" + address + '\'' +
                ", allEmail='" + allEmail + '\'' +
                ", firstEmail='" + firstEmail + '\'' +
                ", secondEmail='" + secondEmail + '\'' +
                ", thirdEmail='" + thirdEmail + '\'' +
                ", group='" + group + '\'' +
                ", allPhones='" + allPhones + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", workPhone='" + workPhone + '\'' +
                '}';
    }
}
