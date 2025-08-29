package lebedev.task4to6.addressbook.tests;


import lebedev.task4to6.addressbook.model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupEditingTest extends TestBase {

    @Test
    public void groupEditing () {
        app.getNavigationHelper().goToGroupPage();
        if (!app.getGroupHelper().isGroupExist()){
            app.getGroupHelper().createGroup (new GroupData("GroupName","GroupHeader", "GroupFooter"));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupEditing();
        app.getGroupHelper().fillGroupForm(new GroupData("testName", "testHeader", "testFooter"));
        app.getGroupHelper().submitGroupEditing();
        app.getGroupHelper().returnToGroupPage();
    }
}
