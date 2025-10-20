package lebedev.addressbook.tests;

import lebedev.addressbook.model.GroupData;
import lebedev.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTest extends TestBase {

  @BeforeMethod
  public void groupCreationPreconditionsCheck() {
    appManager.goTo().groupPage();
  }

  @Test
  public void groupCreation() {
    Groups beforeGroupList = appManager.group().all();
    GroupData groupForList = new GroupData().withName("GroupName").withHeader("GroupHeader").withFooter("GroupFooter");

    appManager.group().create(groupForList);

    Groups afterGroupList = appManager.group().all();
    assertThat(afterGroupList.size(), equalTo(beforeGroupList.size() + 1));
    appManager.goTo().homePage();

    assertThat(afterGroupList, equalTo(
            groupForList.withId(afterGroupList.stream().mapToInt(GroupData::getId).max().getAsInt())));
  }
}




