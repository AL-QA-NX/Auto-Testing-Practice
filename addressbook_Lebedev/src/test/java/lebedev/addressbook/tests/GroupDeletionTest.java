package lebedev.addressbook.tests;

import lebedev.addressbook.model.GroupData;
import lebedev.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
    Groups beforeGroupList = appManager.group().all();
    GroupData deletedGroup = beforeGroupList.iterator().next();

    appManager.group().delete(deletedGroup);

    Groups afterGroupList = appManager.group().all();

    assertThat(afterGroupList.size(),equalTo(beforeGroupList.size() - 1));

    appManager.goTo().homePage();

    assertThat(afterGroupList, equalTo(beforeGroupList.without(deletedGroup)));
  }
}
