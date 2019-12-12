package tests.homework.basicNavigationHomework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BrowserFactory;
import utils.BrowserUtils;
import utils.StringUtility;

public class TestCase4 {

    /**
    Step 1. Go to https://practice-cybertekschool.herokuapp.com
    Step 2. Click on “Registration Form”
    Step 3. Enter “123” into first name input box.
    Step 4. Verify that warning message is displayed: “first name can only consist of alphabetical letters”
     */
    public static void main(String[] args) {

        WebDriver driver = BrowserFactory.getDriver("chrome");
        driver.get("https://practice-cybertekschool.herokuapp.com/");
        driver.manage().window().maximize();
        BrowserUtils.wait(2);

        driver.findElement(By.linkText("Registration Form")).click();
        BrowserUtils.wait(1);

        driver.findElement(By.name("firstname")).sendKeys("123");
        BrowserUtils.wait(2);

        WebElement display = driver.findElement(By.xpath("//small[starts-with(text(),'first name can')]"));
        String expectedMessage = "first name can only consist of alphabetical letters";
        String actualMessage = display.getText();
        StringUtility.verifyEquals(expectedMessage, actualMessage);

        driver.quit();

    }
}
