package tests.TestNGHomework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.BrowserFactory;
import utils.BrowserUtils;

import java.util.List;

public class TestCases1to5 {

    WebDriver driver = BrowserFactory.getDriver("chrome");

    @BeforeTest
    public void setup() {
        driver.get("https://practice-cybertekschool.herokuapp.com/");
        driver.manage().window().maximize();
        BrowserUtils.wait(2);

        driver.findElement(By.linkText("Registration Form")).click();
        BrowserUtils.wait(1);
    }

    @Test(description = "Verify that warning message is displayed: 'The date of birth is not valid'")
    public void test1() {
        driver.findElement(By.name("birthday")).sendKeys("wrong_dob");
        BrowserUtils.wait(2);

        WebElement dob = driver.findElement(By.xpath("//small[text()='The date of birth is not valid']"));
        String expectedText = "The date of birth is not valid";
        String actualText = dob.getText();
        Assert.assertEquals(actualText, expectedText);
    }

    @Test(description = "Verify that following options for programming languages are displayed: c++, java, JavaScript")
    public void test2() {
        WebElement programmingLanguages = driver.findElement(By.xpath("//label[text()='Select programming languages']"));
        BrowserUtils.wait(2);

        String expectedDisplay = "Select programming languages";
        String actualDisplay = programmingLanguages.getText();
        Assert.assertEquals(actualDisplay, expectedDisplay);

        List<WebElement> programmingLanguage = driver.findElements(By.xpath("//div[@class='form-check form-check-inline']"));
        Assert.assertEquals("C++", programmingLanguage.get(0).getText());
        Assert.assertEquals("Java", programmingLanguage.get(1).getText());
        Assert.assertEquals("JavaScript", programmingLanguage.get(2).getText());

    }

    @Test(description = "Verify that warning message is displayed: 'first name must be more than 2 and less than 64 characters long'")
    public void test3() {
        driver.findElement(By.name("firstname")).sendKeys("a");
        BrowserUtils.wait(2);

        WebElement warningMessage = driver.findElement(By.xpath("//small[starts-with(text(),'first name must')]"));
        String expectedWarningMessage = warningMessage.getText();
        String actualWarningMessage = "first name must be more than 2 and less than 64 characters long";
        Assert.assertEquals(expectedWarningMessage, actualWarningMessage);
    }

    @Test(description = "Verify that warning message is displayed: 'The last name must be more than 2 and less than 64 characters long'")
    public void test4() {
        driver.findElement(By.name("lastname")).sendKeys("b");
        BrowserUtils.wait(2);

        WebElement warningMessage = driver.findElement(By.xpath("//small[starts-with(text(),'The last name must')]"));
        String expectedWarningMessage = warningMessage.getText();
        String actualWarningMessage = "The last name must be more than 2 and less than 64 characters long";
        Assert.assertEquals(expectedWarningMessage, actualWarningMessage);
    }

    @Test(description = "Verify that following success message is displayed: 'You've successfully completed registration!'")
    public void test5() {
        //Entering first name
        driver.findElement(By.name("firstname")).sendKeys("Hakan");
        BrowserUtils.wait(1);

        //Entering last name
        driver.findElement(By.name("lastname")).sendKeys("Orak");
        BrowserUtils.wait(1);

        //Entering username
        driver.findElement(By.name("username")).sendKeys("HakanOrak");
        BrowserUtils.wait(1);

        //Entering email
        driver.findElement(By.name("email")).sendKeys("email@email.com");
        BrowserUtils.wait(1);

        //Entering password
        driver.findElement(By.name("password")).sendKeys("123456789");
        BrowserUtils.wait(1);

        //Entering phone
        driver.findElement(By.name("phone")).sendKeys("123-456-7890");
        BrowserUtils.wait(1);

        //Choosing gender (radio button)
        List<WebElement> Gender = driver.findElements(By.name("gender"));
        Gender.get(0).click();
        BrowserUtils.wait(1);

        //Entering birthday
        driver.findElement(By.name("birthday")).sendKeys("12/08/2019");
        BrowserUtils.wait(1);

        //Selecting department
        WebElement department = driver.findElement(By.name("department"));
        Select departmentDropDown = new Select(department);
        departmentDropDown.selectByValue("DE");
        BrowserUtils.wait(1);

        //Selecting job type
        WebElement jobType = driver.findElement(By.name("job_title"));
        Select jobTypeDropDown = new Select(jobType);
        jobTypeDropDown.selectByVisibleText("Manager");
        BrowserUtils.wait(1);

        //Checking programming language
        List<WebElement> programmingLanguage = driver.findElements(By.className("form-check-input"));
        programmingLanguage.get(1).click();
        BrowserUtils.wait(1);

        //Clicking Sign Up
        driver.findElement(By.id("wooden_spoon")).click();
        BrowserUtils.wait(1);

        //Verify that following success message is displayed: “You've successfully completed registration!”
        WebElement successMessage = driver.findElement(By.xpath("//*[@id='content']/div/div/p"));
        String expectedSuccessMessage = "You've successfully completed registration!";
        String actualSuccessMessage = successMessage.getText();
        Assert.assertEquals(expectedSuccessMessage, actualSuccessMessage);
        BrowserUtils.wait(1);

    }

    @AfterTest
    public void teardown(){
        driver.quit();
    }
}
