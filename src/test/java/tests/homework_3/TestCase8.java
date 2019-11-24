package tests.homework_3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BrowserFactory;
import utils.BrowserUtils;
import utils.StringUtility;

public class TestCase8 {

    /**
    Step 1. Go to https://practice-cybertekschool.herokuapp.com
    Step 2. Click on “Registration Form”
    Step 3. Enter “5711234354” into phone number input box.
    Step 4. Verify that warning message is displayed: “Phone format is not correct”
     */
    public static void main(String[] args) {

        WebDriver driver = BrowserFactory.getDriver("chrome");
        driver.get("https://practice-cybertekschool.herokuapp.com/");
        driver.manage().window().maximize();
        BrowserUtils.wait(2);

        driver.findElement(By.linkText("Registration Form")).click();
        BrowserUtils.wait(1);

        driver.findElement(By.name("phone")).sendKeys("5711234354");
        BrowserUtils.wait(2);

        WebElement display = driver.findElement(By.xpath("//small[starts-with(text(),'Phone format')]"));
        String expectedMessage = "Phone format is not correct";
        String actualMessage = display.getText();
        StringUtility.verifyEquals(expectedMessage, actualMessage);

        driver.quit();

    }
}
