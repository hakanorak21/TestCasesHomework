package tests.homework.testNGHomework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.BrowserFactory;
import utils.BrowserUtils;

public class TestCase6 {

    WebDriver driver = BrowserFactory.getDriver("chrome");

    @BeforeTest
    public void setup() {
        driver.get("https://www.tempmailaddress.com/");
        driver.manage().window().maximize();
        BrowserUtils.wait(2);
    }

    @Test (description = "Test case #6")
    public void test() {
        String email = driver.findElement(By.id("email")).getText();
        driver.navigate().to("https://practice-cybertekschool.herokuapp.com/");
        driver.findElement(By.linkText("Sign Up For Mailing List")).click();
        BrowserUtils.wait(1);
        driver.findElement(By.name("full_name")).sendKeys("Hakan Orak");
        driver.findElement(By.name("email")).sendKeys(email);
        BrowserUtils.wait(1);
        driver.findElement(By.name("wooden_spoon")).click();
        BrowserUtils.wait(2);
        String expectedMessage = "Thank you for signing up. Click the button below to return to the home page.";
        String actualMessage = driver.findElement(By.name("signup_message")).getText();
        Assert.assertEquals(actualMessage, expectedMessage);
        driver.navigate().to("https://www.tempmailaddress.com/");
        BrowserUtils.wait(2);

        WebElement inbox = driver.findElement(By.xpath("//tbody[@id='schranka']/tr[1]/td[1]"));
        String expectedInbox = " do-not-reply@practice.cybertekschool.com";
        String actualInbox = inbox.getText();
        Assert.assertEquals(actualInbox, expectedInbox);

        inbox.click();
        BrowserUtils.wait(2);
        String expectedFrom = "do-not-reply@practice.cybertekschool.com";
        String actualFrom = driver.findElement(By.id("odesilatel")).getText();
        Assert.assertEquals(actualFrom, expectedFrom);

        String expectedSubject = "Thanks for subscribing to practice.cybertekschool.com!";
        String actualSubject = driver.findElement(By.id("predmet")).getText();

    }

    @AfterTest
    public void teardown(){
        driver.quit();
    }

}
