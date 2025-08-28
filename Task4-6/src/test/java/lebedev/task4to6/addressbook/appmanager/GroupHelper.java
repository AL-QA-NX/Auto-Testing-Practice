package lebedev.task4to6.addressbook.appmanager;

import lebedev.task4to6.addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GroupHelper extends BaseHelper {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToGroupPage() {
      click(By.linkText("group page"));
    }

    public void submitGroupCreation() {
      click(By.name("submit"));
    }

    public void fillGroupCreationForm(GroupData groupData) {
        type(By.name("group_name"),groupData.name());
        type(By.name("group_header"),groupData.header());
        type(By.name("group_footer"),groupData.footer());
    }

    public void initGroupCreation() {
      click(By.name("new"));
    }

    public void submitGroupDeletion() {
      click(By.name("delete"));
    }

    public void selectGroup() {
      click(By.name("selected[]"));
    }
}
