package lebedev.task4to6.addressbook;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;

public class GroupCreationTest {
  JavascriptExecutor js;
  private WebDriver wd;
  private Map<String, Object> vars;

  @BeforeEach
  public void setUp() {
    wd = new FirefoxDriver();
    js = (JavascriptExecutor) wd;
    vars = new HashMap<String, Object>();
  }

  @AfterEach
  public void tearDown() {
    wd.quit();
  }

  @Test
  public void groupCreation() {

    // Test name: GroupCreation
    // Step # | name | target | value
    // 1 | open | http://localhost/addressbook/ |
    wd.get("http://localhost/addressbook/");
    // 2 | type | name=user | admin
    wd.findElement(By.name("user")).sendKeys("admin");
    // 3 | type | name=pass | secret
    wd.findElement(By.name("pass")).sendKeys("secret");
    // 4 | click | xpath=//input[@value='Login'] |
    wd.findElement(By.xpath("//input[@value=\'Login\']")).click();
    // 5 | click | linkText=groups |
    wd.findElement(By.linkText("groups")).click();
    // 6 | click | name=new |
    wd.findElement(By.name("new")).click();
    // 7 | type | name=group_name | FirstGroupName
    wd.findElement(By.name("group_name")).sendKeys("FirstGroupName");
    // 8 | type | name=group_header | FirstGroupHeader
    wd.findElement(By.name("group_header")).sendKeys("FirstGroupHeader");
    // 9 | type | name=group_footer | FirstGroupFooter
    wd.findElement(By.name("group_footer")).sendKeys("FirstGroupFooter");
    // 10 | click | name=submit |
    wd.findElement(By.name("submit")).click();
    // 11 | click | linkText=group page |
    wd.findElement(By.linkText("group page")).click();
    // 12 | click | linkText=Logout |
    wd.findElement(By.linkText("Logout")).click();
  }
}
