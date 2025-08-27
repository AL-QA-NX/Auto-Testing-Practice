package lebedev.task4to6.addressbook;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ContactCreationTest {
    private WebDriver wd;

    @BeforeEach
    public void setUp() {
        wd = new FirefoxDriver();
        wd.get("http://localhost/addressbook/");
        login("admin","secret");
    }

    @Test
    public void contactCreation() {
        goToContactPage();
        fillContactForm(new ContactData("TestFirstName", "TestMiddleName", "TestLastName", "Test", "TestCompany", "test@mail.com"));
        submitContactCreation();
        returnToHomePage();
    }

    @AfterEach
    public void tearDown() {
        logout();
        wd.quit();
    }

    private void returnToHomePage() {
        wd.findElement(By.linkText("home page")).click();
    }

    private void submitContactCreation() {
        wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
    }

    private void fillContactForm(ContactData contactData) {
        wd.findElement(By.name("firstname")).sendKeys(contactData.firstName());
        wd.findElement(By.name("middlename")).sendKeys(contactData.middleName());
        wd.findElement(By.name("lastname")).sendKeys(contactData.lastName());
        wd.findElement(By.name("nickname")).sendKeys(contactData.nickname());
        wd.findElement(By.name("company")).sendKeys(contactData.company());
        wd.findElement(By.name("email")).sendKeys(contactData.email());
    }

    private void goToContactPage() {
        wd.findElement(By.linkText("add new")).click();
    }

    public void login(String username, String password) {
        wd.findElement(By.name("user")).sendKeys(username);
        wd.findElement(By.name("pass")).sendKeys(password);
        wd.findElement(By.xpath("//input[@value='Login']")).click();
    }

    private void logout() {
        wd.findElement(By.linkText("Logout")).click();
    }
}
