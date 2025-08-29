package lebedev.task4to6.addressbook.tests;

import lebedev.task4to6.addressbook.model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupDeletionTest extends TestBase {

  @Test
  public void groupDeletion() {
    app.getNavigationHelper().goToGroupPage();
    if (!app.getGroupHelper().isGroupExist()){
      app.getGroupHelper().createGroup (new GroupData("GroupName","GroupHeader", "GroupFooter"));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().submitGroupDeletion();
    app.getGroupHelper().returnToGroupPage();
    app.getNavigationHelper().goToHomePage();
  }
}
