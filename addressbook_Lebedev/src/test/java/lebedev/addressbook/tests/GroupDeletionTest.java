package lebedev.addressbook.tests;

import lebedev.addressbook.model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class GroupDeletionTest extends TestBase {

  @BeforeMethod
  public void groupDeletionPreconditionsCheck() {
    appManager.goTo().groupPage();
    if (appManager.group().all().isEmpty()){
      appManager.group().create(new GroupData().withName("GroupName").withHeader("GroupHeader").withFooter("GroupFooter"));
    }
  }

  @Test
  public void groupDeletion() {
    Set<GroupData> beforeGroupList = appManager.group().all();
    GroupData deletedGroup = beforeGroupList.iterator().next();

    appManager.group().delete(deletedGroup);

    Set<GroupData> afterGroupList = appManager.group().all();
    Assertions.assertEquals(afterGroupList.size(), beforeGroupList.size() - 1);

    appManager.goTo().homePage();

    beforeGroupList.remove(deletedGroup);
    Assertions.assertEquals(beforeGroupList, afterGroupList);
  }
}
