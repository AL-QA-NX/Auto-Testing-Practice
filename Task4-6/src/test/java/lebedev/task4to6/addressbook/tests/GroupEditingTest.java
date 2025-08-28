package lebedev.task4to6.addressbook.tests;


import lebedev.task4to6.addressbook.model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupEditingTest extends TestBase {

    @Test
    public void groupEditing () {
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().initGroupEditing();
        app.getGroupHelper().clearGroupForm();
        app.getGroupHelper().fillGroupForm(new GroupData("testName", "testHeader", "testFooter"));
        app.getGroupHelper().submitGroupEditing();
        app.getGroupHelper().returnToGroupPage();
    }
}
