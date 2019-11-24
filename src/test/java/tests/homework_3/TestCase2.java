package tests.homework_3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BrowserFactory;
import utils.BrowserUtils;

import java.util.List;

public class TestCase2 {

    public static void main(String[] args) {

        /*
        Step 1. Go to https://practice-cybertekschool.herokuapp.com
        Step 2. Verify that number of listed examples is equals to 48.
        Hint: use findElemnts() and size() methods.
         */
        WebDriver driver = BrowserFactory.getDriver("chrome");
        driver.get("https://practice-cybertekschool.herokuapp.com/");

        List<WebElement> example = driver.findElements(By.className("list-group-item"));
        BrowserUtils.wait(2);

        if(example.size() == 48) {
            System.out.println("PASS");
            System.out.println("48 examples");
        } else{
            System.out.println("FAILED");
            System.out.println("Number of expected examples: 48");
            System.out.println("Number of actual examples: " + example.size());
        }

        driver.quit();

    }
}
