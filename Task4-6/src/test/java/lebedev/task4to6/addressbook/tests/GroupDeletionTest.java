package lebedev.task4to6.addressbook.tests;

import org.junit.jupiter.api.Test;

public class GroupDeletionTest extends TestBase {

  @Test
  public void groupDeletion() {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().submitGroupDeletion();
    app.getGroupHelper().returnToGroupPage();
    app.getNavigationHelper().goToHomePage();
  }
}
