package lebedev.addressbook.model;

public record ContactData(String firstName, String middleName, String lastName, String nickname, String company,
                          String email, String group) {
}