package lebedev.addressbook.model;

import java.util.Objects;

import static java.lang.Integer.MAX_VALUE;

public record GroupData(int id, String name, String header, String footer) {

    public GroupData(String name, String header, String footer) {
        this(MAX_VALUE, name, header, footer);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        GroupData groupData = (GroupData) o;
        return Objects.equals(name, groupData.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return "GroupData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}


