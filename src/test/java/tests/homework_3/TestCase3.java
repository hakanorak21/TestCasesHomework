package tests.homework_3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BrowserFactory;
import utils.BrowserUtils;
import utils.StringUtility;

public class TestCase3 {

    /**
    Step 1. Go to https://practice-cybertekschool.herokuapp.com
    Step 2. Click on “Multiple Buttons”
    Step 3. Click on “Button 1”
    Verify that result message is displayed: “Clicked on button one!”
     */
    public static void main(String[] args) {

        WebDriver driver = BrowserFactory.getDriver("chrome");
        driver.get("https://practice-cybertekschool.herokuapp.com/");
        driver.manage().window().maximize();
        BrowserUtils.wait(2);

        driver.findElement(By.linkText("Multiple Buttons")).click();
        BrowserUtils.wait(1);

        driver.findElement(By.xpath("//button[@onclick='button1()']")).click();
        BrowserUtils.wait(1);

        WebElement result = driver.findElement(By.id("result"));
        String actual = result.getText();
        String expected = "Clicked on button one!";
        StringUtility.verifyEquals(expected, actual);

        driver.quit();

    }
}
