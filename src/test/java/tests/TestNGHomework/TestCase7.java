package tests.TestNGHomework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.BrowserFactory;
import utils.BrowserUtils;

public class TestCase7 {

    WebDriver driver = BrowserFactory.getDriver("chrome");

    @BeforeTest
    public void setup() {
        driver.get("https://practice-cybertekschool.herokuapp.com");
        driver.manage().window().maximize();
        BrowserUtils.wait(2);
    }

    @Test(description = "File upload practice")
    public void test(){
        driver.findElement(By.linkText("File Upload")).click();
        BrowserUtils.wait(2);

        WebElement chooseFile = driver.findElement(By.id("file-upload"));
        chooseFile.sendKeys("/Users/horak/Desktop/test.txt");
        BrowserUtils.wait(2);
        driver.findElement(By.id("file-submit")).click();
        BrowserUtils.wait(2);

        String expectedMessage = "File Uploaded!";
        String actualMessage = driver.findElement(By.xpath("//div[@id=\"content\"]/div/h3")).getText();
        Assert.assertEquals(expectedMessage, actualMessage);

        String expectedFileName = "test.txt";
        String actualFileName = driver.findElement(By.id("uploaded-files")).getText();
        Assert.assertEquals(expectedFileName, actualFileName);
    }

    @AfterTest
    public void teardown(){
        driver.quit();
    }
}
