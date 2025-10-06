package lebedev.addressbook.tests;

import lebedev.addressbook.model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GroupDeletionTest extends TestBase {

  @Test
  public void groupDeletion() {
    appManager.getNavigationHelper().goToGroupPage();
    if (!appManager.getGroupHelper().isGroupExist()){
      appManager.getGroupHelper().createGroup (new GroupData("GroupName","GroupHeader", "GroupFooter"));
    }
    List<GroupData> beforeGroupList = appManager.getGroupHelper().getGroupList();
    appManager.getGroupHelper().selectGroup(beforeGroupList.size() - 1);
    appManager.getGroupHelper().submitGroupDeletion();
    appManager.getGroupHelper().returnToGroupPage();
    List<GroupData> afterGroupList = appManager.getGroupHelper().getGroupList();
    Assertions.assertEquals(afterGroupList.size(), beforeGroupList.size() - 1);
    appManager.getNavigationHelper().goToHomePage();

    beforeGroupList.remove(beforeGroupList.size() - 1);
    Assertions.assertEquals(beforeGroupList, afterGroupList);
  }
}
