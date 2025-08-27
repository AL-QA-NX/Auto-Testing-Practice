package lebedev.task4to6.addressbook;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GroupCreationTest {
  private WebDriver wd;

  @BeforeEach
  public void setUp() {
    wd = new FirefoxDriver();
    
    // 1 | open | http://localhost/addressbook/ |
    wd.get("http://localhost/addressbook/");
    login("admin", "secret");
  }

  @Test
  public void groupCreation() {

    // Test name: GroupCreation
    // Step # | name | target | value

    goToGroupPage();
    initGroupCreation();
    fillGroupCreationForm(new GroupData("GroupName", "GroupHeader", "GroupFooter"));
    submitGroupCreation();
    returnToGroupPage();

  }

  @AfterEach
  public void tearDown() {
    logout();
    wd.quit();
  }

  public void login(String username, String password) {
    // 2 | type | name=user | admin
    wd.findElement(By.name("user")).sendKeys(username);
    // 3 | type | name=pass | secret
    wd.findElement(By.name("pass")).sendKeys(password);
    // 4 | click | xpath=//input[@value='Login'] |
    wd.findElement(By.xpath("//input[@value='Login']")).click();
  }

  private void logout() {
    // 12 | click | linkText=Logout |
    wd.findElement(By.linkText("Logout")).click();
  }

  private void returnToGroupPage() {
    // 11 | click | linkText=group page |
    wd.findElement(By.linkText("group page")).click();
  }

  private void submitGroupCreation() {
    // 10 | click | name=submit |
    wd.findElement(By.name("submit")).click();
  }

  private void fillGroupCreationForm(GroupData groupData) {
    // 7 | type | name=group_name | FirstGroupName
    wd.findElement(By.name("group_name")).sendKeys(groupData.name());
    // 8 | type | name=group_header | FirstGroupHeader
    wd.findElement(By.name("group_header")).sendKeys(groupData.header());
    // 9 | type | name=group_footer | FirstGroupFooter
    wd.findElement(By.name("group_footer")).sendKeys(groupData.footer());
  }

  private void initGroupCreation() {
    // 6 | click | name=new |
    wd.findElement(By.name("new")).click();
  }

  private void goToGroupPage() {
    // 5 | click | linkText=groups |
    wd.findElement(By.linkText("groups")).click();
  }
}




