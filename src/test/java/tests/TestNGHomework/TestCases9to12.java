package tests.TestNGHomework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.BrowserFactory;
import utils.BrowserUtils;
import java.lang.String;

public class TestCases9to12 {

    WebDriver driver = BrowserFactory.getDriver("chrome");

    @BeforeMethod
    public void setup() {
        driver.get("https://practice-cybertekschool.herokuapp.com");
        driver.manage().window().maximize();
        BrowserUtils.wait(2);
    }

    @Test(dataProvider = "statusCode")
    public void test(String code){
        driver.findElement(By.linkText("Status Codes")).click();
        BrowserUtils.wait(2);
        driver.findElement(By.cssSelector("[href='status_codes/"+code+"']")).click();
        BrowserUtils.wait(1);
        String expectedMessage = "This page returned a "+code+" status code";
        WebElement message= driver.findElement(By.xpath("//p[contains(text(), "+code+")]"));
        String originalMessage = message.getText().trim();
        String partialMessage = originalMessage.substring(0, originalMessage.indexOf('.'));
        Assert.assertEquals(partialMessage, expectedMessage);
    }

    @DataProvider (name = "statusCode")
    public Object[][] statusCode(){
        return new Object[][]  {{"200"}, {"301"}, {"404"}, {"500"}};
    }


    @AfterTest
    public void teardown(){
        driver.quit();
    }

}
