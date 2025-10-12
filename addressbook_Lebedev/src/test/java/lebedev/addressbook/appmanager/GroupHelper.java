package lebedev.addressbook.appmanager;

import lebedev.addressbook.model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public void selectGroup(int index) {
        webDriver.findElements(By.name("selected[]")).get(index).click();
    }

    public void initGroupEditing() {
        clickOnElement(By.name("edit"));
    }

    public void submitGroupEditing() {
        clickOnElement(By.name("update"));
    }

    public void createGroup(GroupData groupData) {
        initGroupCreation();
        fillGroupForm(groupData);
        submitGroupCreation();
        returnToGroupPage();
    }

    public boolean isGroupExist() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getGroupCount() {
        return webDriver.findElements(By.name("selected[]")).size();
    }

    public List<GroupData> getGroupList() {
        List<GroupData> groupsGetGroupList = new ArrayList <>();
        List<WebElement> elementsGroups = webDriver.findElements(By.cssSelector("span.group"));
        for (WebElement element: elementsGroups){
            String name = element.getText();
            int id = Integer.parseInt(Objects.requireNonNull(element.findElement(By.tagName("input")).getAttribute("value")));
            GroupData group = new GroupData(id, name, null, null);
            groupsGetGroupList.add(group);
        }
        return groupsGetGroupList;
    }
}
