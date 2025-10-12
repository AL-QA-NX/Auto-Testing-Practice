package lebedev.addressbook.tests;


import lebedev.addressbook.model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

public class GroupEditingTest extends TestBase {

    @Test
    public void groupEditing () {
        appManager.getNavigationHelper().goToGroupPage();
        if (!appManager.getGroupHelper().isGroupExist()){
            appManager.getGroupHelper().createGroup (new GroupData("GroupName","GroupHeader", "GroupFooter"));
        }
        List<GroupData> beforeGroupList = appManager.getGroupHelper().getGroupList();
        appManager.getGroupHelper().selectGroup(beforeGroupList.size() - 1);
        appManager.getGroupHelper().initGroupEditing();
        GroupData group = new GroupData(beforeGroupList.get(beforeGroupList.size() - 1).id(),"testName", "testHeader", "testFooter");
        appManager.getGroupHelper().fillGroupForm(group);
        appManager.getGroupHelper().submitGroupEditing();
        appManager.getGroupHelper().returnToGroupPage();
        List<GroupData> afterGroupList = appManager.getGroupHelper().getGroupList();
        Assertions.assertEquals(afterGroupList.size(), beforeGroupList.size());
        appManager.getNavigationHelper().goToHomePage();

        beforeGroupList.remove(beforeGroupList.size() - 1);
        beforeGroupList.add(group);
        Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::id);
        beforeGroupList.sort(byId);
        afterGroupList.sort(byId);
        Assertions.assertEquals(beforeGroupList, afterGroupList);
    }
}
