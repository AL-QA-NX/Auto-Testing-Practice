package lebedev.addressbook.tests;

import lebedev.addressbook.model.GroupData;
import lebedev.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupEditingTest extends TestBase {

    @BeforeMethod
    public void groupEditingPreconditionsCheck() {
        appManager.goTo().groupPage();
        if (appManager.group().all().isEmpty()){
            appManager.group().create(new GroupData().withName("GroupName").withHeader("GroupHeader").withFooter("GroupFooter"));
        }
    }

    @Test
    public void groupEditing () {
        Groups beforeGroupList = appManager.group().all();
        GroupData editedGroup = beforeGroupList.iterator().next();
        GroupData group = new GroupData()
                .withId(editedGroup.getId()).withName("testName").withHeader("testHeader").withFooter("testFooter");

        appManager.group().edit(group);
        assertThat(appManager.group().count(), equalTo(beforeGroupList.size()));
        Groups afterGroupList = appManager.group().all();
        appManager.goTo().homePage();
        assertThat(afterGroupList, equalTo(beforeGroupList.without(editedGroup).withAdded(group)));
    }
}
