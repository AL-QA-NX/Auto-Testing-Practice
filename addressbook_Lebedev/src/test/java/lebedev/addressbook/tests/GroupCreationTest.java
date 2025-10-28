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
    GroupData groupThatWillBeCreated = new GroupData().withName("GroupName").withHeader("GroupHeader").withFooter("GroupFooter");

    appManager.group().create(groupThatWillBeCreated);

    assertThat(appManager.group().count(), equalTo(beforeGroupList.size() + 1));
    Groups afterGroupList = appManager.group().all();
    appManager.goTo().homePage();

    assertThat(afterGroupList, equalTo(beforeGroupList.withAdded(groupThatWillBeCreated.withId(afterGroupList.stream()
            .mapToInt(GroupData::getId).max().getAsInt()))));
  }

  @Test
  public void badGroupCreation() {
    Groups beforeGroupList = appManager.group().all();
    GroupData groupForList = new GroupData().withName("GroupName'").withHeader("GroupHeader").withFooter("GroupFooter");

    appManager.group().create(groupForList);
    assertThat(appManager.group().count(), equalTo(beforeGroupList.size()));
    Groups afterGroupList = appManager.group().all();
    appManager.goTo().homePage();
    assertThat(afterGroupList, equalTo(beforeGroupList));
  }
}




