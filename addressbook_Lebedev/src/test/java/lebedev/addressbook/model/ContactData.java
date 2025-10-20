package lebedev.addressbook.model;

import java.util.Objects;

public class ContactData {
    private String firstName;
    private String middleName;
    private String lastName;
    private String nickname;
    private String company;
    private String email;
    private String group;

    public String getFirstName() {return firstName;}
    public String getMiddleName() {return middleName;}
    public String getLastName() {return lastName;}
    public String getNickname() {return nickname;}
    public String getCompany() {return company;}
    public String getEmail() {return email;}
    public String getGroup() {return group;}

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

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;
        return Objects.equals(lastName, that.lastName) && Objects.equals(firstName, that.firstName);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(firstName);
        result = 31 * result + Objects.hashCode(lastName);
        return result;
    }
}
