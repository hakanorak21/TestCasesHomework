package tests.homework.testNGHomework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.BrowserFactory;
import utils.BrowserUtils;

public class TestCase8 {

    WebDriver driver = BrowserFactory.getDriver("chrome");

    @BeforeTest
    public void setup() {
        driver.get("https://practice-cybertekschool.herokuapp.com");
        driver.manage().window().maximize();
        BrowserUtils.wait(2);
    }

    @Test(description = "Test case #8")
    public void test(){
        driver.findElement(By.linkText("Autocomplete")).click();
        BrowserUtils.wait(2);
        driver.findElement(By.id("myCountry")).sendKeys("United States of America");
        BrowserUtils.wait(1);
        driver.findElement(By.xpath("//input[@type='button']")).click();
        BrowserUtils.wait(1);

        String expectedResult = "You selected: United States of America";
        String actualResult = driver.findElement(By.id("result")).getText();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @AfterTest
    public void teardown(){
        driver.quit();
    }
}
