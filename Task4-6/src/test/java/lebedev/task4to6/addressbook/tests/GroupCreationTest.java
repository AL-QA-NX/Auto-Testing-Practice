package lebedev.task4to6.addressbook.tests;

import lebedev.task4to6.addressbook.model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupCreationTest extends TestBase {
  @Test
  public void groupCreation() {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().createGroup(new GroupData("GroupName", "GroupHeader", "GroupFooter"));
  }
}




