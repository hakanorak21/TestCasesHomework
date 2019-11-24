package tests.homework_3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BrowserFactory;
import utils.BrowserUtils;
import utils.StringUtility;

public class TestCase7 {

    /**
    Step 1. Go to https://practice-cybertekschool.herokuapp.com
    Step 2. Click on “Registration Form”
    Step 3. Enter “testers@email” into email input box.
    Step 4. Verify that warning message is displayed:
            “email address is not a validEmail format is not correct”
     */

    public static void main(String[] args) {

        WebDriver driver = BrowserFactory.getDriver("chrome");
        driver.get("https://practice-cybertekschool.herokuapp.com/");
        driver.manage().window().maximize();
        BrowserUtils.wait(2);

        driver.findElement(By.linkText("Registration Form")).click();
        BrowserUtils.wait(1);

        driver.findElement(By.name("email")).sendKeys("testers@email");
        BrowserUtils.wait(2);

        WebElement display = driver.findElement(By.xpath("//small[starts-with(text(),'email address is not')]"));
        String expectedMessage = "email address is not a valid";
        String actualMessage = display.getText();
        StringUtility.verifyEquals(expectedMessage, actualMessage);

        WebElement display2 = driver.findElement(By.xpath("//small[starts-with(text(),'Email format')]"));
        String expectedMessage2 = "Email format is not correct";
        String actualMessage2 = display2.getText();
        StringUtility.verifyEquals(expectedMessage2, actualMessage2);

        driver.quit();

    }
}
