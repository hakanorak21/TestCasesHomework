package tests.homework.basicNavigationHomework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BrowserFactory;
import utils.BrowserUtils;
import utils.StringUtility;

public class TestCase1 {
    /*
    Step 1. Go to https://practice-cybertekschool.herokuapp.com
    Step 2. Click on “Sign Up For Mailing List”
    Step 3. Enter any valid name
    Step 4. Enter any valid email
    Step 5. Click on “Sign Up” button
    Expected result: Following message should be displayed:
    “Thank you for signing up. Click the button below to return to the home page.” Home button should be displayed.
    */

    public static void main(String[] args) {

        WebDriver driver = BrowserFactory.getDriver("chrome");
        driver.get("https://practice-cybertekschool.herokuapp.com");
        driver.manage().window().maximize();

        //WebElement signUp = driver.findElement(By.xpath("//a[@href='/sign_up']"));
        WebElement signUp = driver.findElement(By.linkText("Sign Up For Mailing List"));
        signUp.click();
        BrowserUtils.wait(2);

        //WebElement fullName = driver.findElement(By.name("full_name"));
        //WebElement fullName = driver.findElement(By.xpath("//input[@name='full_name']"));
        WebElement fullName = driver.findElement(By.xpath("//input[@type='text']"));
        fullName.sendKeys("Hakan Orak");
        BrowserUtils.wait(1);

        //WebElement email = driver.findElement(By.name("email"));
        WebElement email = driver.findElement(By.xpath("//input[@type='email']"));
        email.sendKeys("hakanorak21@yahoo.com");
        BrowserUtils.wait(1);

        //driver.findElement(By.name("wooden_spoon")).click();
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        BrowserUtils.wait(2);

        String expected = "Thank you for signing up. Click the button below to return to the home page.";
        String actual = driver.findElement(By.name("signup_message")).getText();
        StringUtility.verifyEquals(expected, actual);

        if (driver.findElement(By.id("wooden_spoon")).getText().equals("Home"))
            System.out.println("Home button PASS");
        else
            System.out.println("No Home button");

        driver.quit();

    }
}
