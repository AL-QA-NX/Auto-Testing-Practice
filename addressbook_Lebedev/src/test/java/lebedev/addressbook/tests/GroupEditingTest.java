package lebedev.addressbook.tests;


import lebedev.addressbook.model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

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
        Set<GroupData> beforeGroupList = appManager.group().all();
        GroupData editedGroup = beforeGroupList.iterator().next();
        GroupData group = new GroupData()
                .withId(editedGroup.getId()).withName("testName").withHeader("testHeader").withFooter("testFooter");

        appManager.group().edit(group);

        Set<GroupData> afterGroupList = appManager.group().all();
        Assertions.assertEquals(afterGroupList.size(), beforeGroupList.size());

        appManager.goTo().homePage();

        beforeGroupList.remove(editedGroup);
        beforeGroupList.add(group);
        Assertions.assertEquals(beforeGroupList, afterGroupList);
    }
}
