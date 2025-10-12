package lebedev.addressbook.model;

import java.util.Objects;

public record ContactData(String firstName, String middleName, String lastName, String nickname, String company,
                          String email, String group) {

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