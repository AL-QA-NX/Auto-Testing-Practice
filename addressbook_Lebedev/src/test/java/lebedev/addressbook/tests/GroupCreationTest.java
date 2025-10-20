package lebedev.addressbook.tests;

import lebedev.addressbook.model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class GroupCreationTest extends TestBase {

  @BeforeMethod
  public void groupCreationPreconditionsCheck() {
    appManager.goTo().groupPage();
  }

  @Test
  public void groupCreation() {
    Set<GroupData> beforeGroupList = appManager.group().all();
    GroupData groupForList = new GroupData().withName("GroupName").withHeader("GroupHeader").withFooter("GroupFooter");

    appManager.group().create(groupForList);

    Set<GroupData> afterGroupList = appManager.group().all();
    Assertions.assertEquals(afterGroupList.size(), beforeGroupList.size() + 1);

    appManager.goTo().homePage();

    groupForList.withId(afterGroupList.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    beforeGroupList.add(groupForList);
    Assertions.assertEquals(beforeGroupList, afterGroupList);
  }
}




