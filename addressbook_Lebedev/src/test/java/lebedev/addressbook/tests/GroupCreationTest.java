package lebedev.addressbook.tests;

import lebedev.addressbook.model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTest extends TestBase {
  @Test
  public void groupCreation() {
    appManager.getNavigationHelper().goToGroupPage();
    List<GroupData> beforeGroupList = appManager.getGroupHelper().getGroupList();
    GroupData groupForList = new GroupData(
            "GroupName",
            "GroupHeader",
            "GroupFooter"
    );
    appManager.getGroupHelper().createGroup(groupForList);
    List<GroupData> afterGroupList = appManager.getGroupHelper().getGroupList();
    Assertions.assertEquals(afterGroupList.size(), beforeGroupList.size() + 1);
    appManager.getNavigationHelper().goToHomePage();

      beforeGroupList.add(groupForList);
      Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::id);
      beforeGroupList.sort(byId);
      afterGroupList.sort(byId);
      Assertions.assertEquals(beforeGroupList, afterGroupList);
  }
}




