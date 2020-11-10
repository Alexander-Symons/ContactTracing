import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class RemoveContactTest {
    private WebDriver driver;
    private String path = "http://localhost:8080/Controller";

    @Before
    public void setUp() {
        //System.setProperty("webdriver.chrome.driver", "/Users/.../web3pers/chromedriver");
        // windows: gebruik dubbele \\ om pad aan te geven
        // hint: zoek een werkende test op van web 2 ...
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Alexander\\Documents\\Ucll\\TI2\\Web3\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(path+"?command=Register");
        submitForm("1", "Jan", "Janssens", "jan.janssens@hotmail.com" , "1234");
        driver.get(path+"?command=Contacts");
        submitForm("Alexander", "Symons", "2020-01-01", "22:22", "01234556789","Alexander.symons@gmail.com");
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_RemoveContact_AllFieldsFilledInCorrectly_ContactIsRemoved() {
        driver.findElement(By.id("RemoveContact")).click();
        submitForm("Alexander","Symons", "2020-01-01", "22:22");
        assertEquals("<th>Date</th><th>Hour</th><th>Name</th>", driver.findElement(By.cssSelector("table tr")));
    }

    @Test
    public void test_RemoveContact_FirstNameNotCorrect_ContactIsNotRemoved(){
        driver.findElement(By.id("RemoveContact")).click();
        submitForm("bert","Symons", "2020-01-01", "22:22");
        assertNotEquals("<th>Date</th><th>Hour</th><th>Name</th>", driver.findElement(By.cssSelector("table tr")));
    }

    @Test
    public void test_RemoveContact_LastNameNotCorrect_ContactIsNotRemoved(){
        driver.findElement(By.id("RemoveContact")).click();
        submitForm("Alexander","a", "2020-01-01", "22:22");
        assertNotEquals("<th>Date</th><th>Hour</th><th>Name</th>", driver.findElement(By.cssSelector("table tr")));
    }

    @Test
    public void test_RemoveContact_DateNotCorrect_ContactIsNotRemoved(){
        driver.findElement(By.id("RemoveContact")).click();
        submitForm("Alexander","Symons", "2020-02-01", "22:22");
        assertNotEquals("<th>Date</th><th>Hour</th><th>Name</th>", driver.findElement(By.cssSelector("table tr")));
    }

    @Test
    public void test_RemoveContact_HourNotCorrect_ContactIsNotRemoved(){
        driver.findElement(By.id("RemoveContact")).click();
        submitForm("Alexander","Symons", "2020-01-01", "22:00");
        assertNotEquals("<th>Date</th><th>Hour</th><th>Name</th>", driver.findElement(By.cssSelector("table tr")));
    }

    @Test
    public void test_RemoveContact_FirstNameEmpty_ContactIsNotRemoved(){
        driver.findElement(By.id("RemoveContact")).click();
        submitForm(null,"Symons", "2020-01-01", "22:22");
        assertNotEquals("<th>Date</th><th>Hour</th><th>Name</th>", driver.findElement(By.cssSelector("table tr")));
    }

    @Test
    public void test_RemoveContact_LastNameEmpty_ContactIsNotRemoved(){
        driver.findElement(By.id("RemoveContact")).click();
        submitForm("Alexander",null, "2020-01-01", "22:22");
        assertNotEquals("<th>Date</th><th>Hour</th><th>Name</th>", driver.findElement(By.cssSelector("table tr")));
    }

    @Test
    public void test_RemoveContact_DateEmpty_ContactIsNotRemoved(){
        driver.findElement(By.id("RemoveContact")).click();
        submitForm("Alexander","Symons", null, "22:22");
        assertNotEquals("<th>Date</th><th>Hour</th><th>Name</th>", driver.findElement(By.cssSelector("table tr")));
    }

    @Test
    public void test_RemoveContact_HourEmpty_ContactIsNotRemoved(){
        driver.findElement(By.id("RemoveContact")).click();
        submitForm("Alexander","Symons", "2020-01-01", null);
        assertNotEquals("<th>Date</th><th>Hour</th><th>Name</th>", driver.findElement(By.cssSelector("table tr")));
    }

    private void fillOutField(String name,String value) {
        WebElement field=driver.findElement(By.id(name));
        field.clear();
        field.sendKeys(value);
    }

    private void submitForm(String userid, String firstName, String lastName, String email, String password) {
        fillOutField("userid", userid);
        fillOutField("firstName", firstName);
        fillOutField("lastName",lastName);
        fillOutField("email", email);
        fillOutField("password", password);

        WebElement button=driver.findElement(By.id("signUp"));
        button.click();
    }

    private void submitForm(String firstName, String lastName, String date, String time, String gsm, String email) {
        
        fillOutField("firstName", firstName);
        fillOutField("lastName",lastName);
        fillOutField("date", date);
        fillOutField("time", time);
        fillOutField("gsm", gsm);
        fillOutField("email", email);
        
        WebElement button=driver.findElement(By.id("AddContact"));
        button.click();
    }

    private void submitForm(String firstName, String lastName, String date, String time) {
        fillOutField("firstName", firstName);
        fillOutField("lastName",lastName);
        fillOutField("date", date);
        fillOutField("time", time);

        WebElement button=driver.findElement(By.id("removeContactconfirm"));
        button.click();
    }
}
