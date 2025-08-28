package lebedev.task4to6.addressbook.appmanager;

import lebedev.task4to6.addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GroupHelper extends BaseHelper {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToGroupPage() {
      clickOnElement(By.linkText("group page"));
    }

    public void submitGroupCreation() {
      clickOnElement(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        typeIntoField(By.name("group_name"),groupData.name());
        typeIntoField(By.name("group_header"),groupData.header());
        typeIntoField(By.name("group_footer"),groupData.footer());
    }

    public void initGroupCreation() {
      clickOnElement(By.name("new"));
    }

    public void submitGroupDeletion() {
      clickOnElement(By.name("delete"));
    }

    public void selectGroup() {
      clickOnElement(By.name("selected[]"));
    }

    public void initGroupEditing() {
        clickOnElement(By.name("edit"));
    }

    public void submitGroupEditing() {
        clickOnElement(By.name("update"));
    }

    public void clearGroupForm () {
        clearField(By.name("group_name"));
        clearField(By.name("group_header"));
        clearField(By.name("group_footer"));
    }

}
