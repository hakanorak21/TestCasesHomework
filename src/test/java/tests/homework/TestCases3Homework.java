package tests.homework;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.BrowserFactory;
import utils.BrowserUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestCases3Homework {

    /**
     Test data
     url: https://qa1.vytrack.com/
     username: storemanager85
     password: UserUser123
     */

    private WebDriver driver;
    private WebDriverWait wait;

//  1.Go to “https://qa1.vytrack.com/"
//  2.Login as a store manager
//  3.Navigate to “Activities -> Calendar Events”
    @BeforeMethod
    public void setup(){
        driver = BrowserFactory.getDriver("chrome");
        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("https://qa1.vytrack.com/");
        driver.findElement(By.id("prependedInput")).sendKeys("storemanager85");
        driver.findElement(By.id("prependedInput2")).sendKeys("UserUser123", Keys.ENTER);

        WebElement activitiesElement = driver.findElement(By.linkText("Activities"));
        wait.until(ExpectedConditions.visibilityOf(activitiesElement));
        wait.until(ExpectedConditions.elementToBeClickable(activitiesElement));
        BrowserUtils.wait(2);
        activitiesElement.click();

        WebElement calendarEventsElement = driver.findElement(By.linkText("Calendar Events"));
        wait.until(ExpectedConditions.visibilityOf(calendarEventsElement));
        wait.until(ExpectedConditions.elementToBeClickable(calendarEventsElement));
        calendarEventsElement.click();

        WebElement loadMask = driver.findElement(By.cssSelector("div[class='loader-mask shown']"));
        wait.until(ExpectedConditions.invisibilityOf(loadMask));
        BrowserUtils.wait(3);
    }

    @Test(description = "Verify that page subtitle 'Options' is displayed")
    public void test1(){
        String expectedSubtitleOptions = "Options";
        String actualSubtitleOptions = driver.findElement(By.xpath("//div[contains(text(), 'Options')]")).getText().trim();
        Assert.assertEquals(actualSubtitleOptions, expectedSubtitleOptions);
        System.out.println("Sub title: " + actualSubtitleOptions);
    }

    @Test(description = "Verify that page number is equal to 1")
    public void test2(){
        String expectedPageNumber = "1";
        String actualPageNumber = driver.findElement(By.cssSelector("[type='number']")).getAttribute("value");
        Assert.assertEquals(actualPageNumber, expectedPageNumber);
        System.out.println("Page number: " + actualPageNumber);
    }

    @Test(description = "Verify that view per page number is equal to 25")
    public void test3(){
        String expectedViewPerPage = "25";
        String viewPerPageNumber = driver.findElement(By.cssSelector("[class='btn dropdown-toggle ']")).getText();
        Assert.assertEquals(viewPerPageNumber, "25", "Wrong View Per Page number!");
        System.out.println("View Per Page: " + viewPerPageNumber);
    }

    @Test(description = "Verify that number of calendar events (rows in the table) is equal to number of records")
    public void test4(){
        String numberOfRecords = driver.findElement(By.cssSelector("[class='dib']:nth-of-type(3)")).getText();
        int actualNumberOfRecords = driver.findElements(By.xpath("//table/tbody/tr")).size();
        Assert.assertEquals(numberOfRecords.contains(""+actualNumberOfRecords), true);
    }

    //button[@class='btn btn-default btn-small dropdown-toggle']/input
    @Test(description = "Verify that all calendar events were selected")
    public void test5(){
        driver.findElement(By.xpath("//button[@class='btn btn-default btn-small dropdown-toggle']/input")).click();
        BrowserUtils.wait(2);

        int actualNumberOfRecords = driver.findElements(By.xpath("//table/tbody/tr")).size();
        int numberOfSelectedRows = driver.findElements(By.cssSelector("[class='grid-row row-click-action row-selected']")).size();
        Assert.assertEquals(numberOfSelectedRows, actualNumberOfRecords);
    }

    @Test(description = "Verify testers meeting data")
    public void test6(){
        driver.findElement(By.xpath("//table/tbody/tr//td[text()='Testers Meeting'][1]")).click();
        WebElement mask = driver.findElement(By.cssSelector("div[class='loader-mask shown']"));
        wait.until(ExpectedConditions.invisibilityOf(mask));

        List<String> expectedData = new ArrayList<>(Arrays.asList(
                "Testers Meeting",
                "This is a a weekly testers meeting",
                "Nov 27, 2019, 9:30 PM",
                "Nov 27, 2019, 10:30 PM",
                "No",
                "Stephan Haley",
                "Tom Smith",
                "Weekly every 1 week on Wednesday",
                "No"));

        for(int i=0; i<expectedData.size(); i++) {
            String actualData = driver.findElements(By.cssSelector("[class='responsive-block']>div>div>div")).get(i).getText();
            if (i == 6) {
                Assert.assertEquals(actualData.replace(" - Required", ""), expectedData.get(i));
                System.out.println(actualData.replace(" - Required", ""));
            } else {
                Assert.assertEquals(actualData, expectedData.get(i));
                System.out.println(actualData);
            }
        }
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }

}
