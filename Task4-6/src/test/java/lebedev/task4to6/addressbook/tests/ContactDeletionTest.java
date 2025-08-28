package lebedev.task4to6.addressbook.tests;

import org.junit.jupiter.api.Test;

public class ContactDeletionTest extends TestBase {

  @Test
  public void contactDeletion() {
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactDeletion();
    app.getContactHelper().acceptContactDeletion();
  }

}
