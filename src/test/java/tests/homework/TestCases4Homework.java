package tests.homework;

import com.google.common.base.Verify;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BrowserFactory;
import utils.BrowserUtils;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestCases4Homework {

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

    @Test(description = "Test case 1: Mouse hover")
    public void test1(){
        Actions actions = new Actions(driver);
        WebElement threeDots = driver.findElement(By.xpath("//table/tbody/tr//td[text()='Testers Meeting']/following-sibling::td[last()]"));
        actions.moveToElement(threeDots).perform();
        BrowserUtils.wait(2);

        List<WebElement> options = driver.findElements(By.xpath("//li[@class='launcher-item']/a"));
        String[] expected = {"View", "Edit", "Delete"};
        for (int i = 0; i < options.size(); i++){
            WebElement element = options.get(i);
            String actual = element.getAttribute("title");
            Assert.assertEquals(actual, expected[i] );
        }
    }

    @Test(description = "Test case 2: Grid options")
    public void test2(){
        //Click on “Grid Options” button
        driver.findElement(By.cssSelector("[class='fa-cog hide-text']")).click();
        BrowserUtils.wait(2);
        //Deselect all options except “Title”
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[data-role='renderable']"));
        for (int i = 1; i < checkboxes.size(); i++) {
            checkboxes.get(i).click();
        }
        BrowserUtils.wait(1);
        //Verify that “TITLE” column still displayed
        String expectedTtile = "TITLE";
        String actualTitle = driver.findElement(By.className("grid-header-cell__label")).getText();
        Assert.assertEquals(actualTitle, expectedTtile);
    }

    @Test(description = "Test case 3: Create calendar event button")
    public void test3(){
        //Click on “Create Calendar Event” button
        driver.findElement(By.cssSelector("[title='Create Calendar event']")).click();
        WebElement loadMask = driver.findElement(By.cssSelector("div[class='loader-mask shown']"));
        wait.until(ExpectedConditions.invisibilityOf(loadMask));
        BrowserUtils.wait(2);
        //Expand “Save And Close” menu
        driver.findElement(By.className("caret")).click();
        BrowserUtils.wait(1);
        //Verify that “Save And Close”, “Save And New” and “Save” options are available
        List<WebElement> option = driver.findElements(By.cssSelector("button[type=submit]"));
        String[] expectedOption = {"Save And Close", "Save And New", "Save"};
        for (int i = 1; i < option.size(); i++){
            String actualOption = option.get(i).getText();
            Assert.assertEquals(actualOption, expectedOption[i-1]);
        }
    }

    @Test(description = "Test case 4: Create calendar event and Cancel buttons")
    public void test4(){
        //Click on “Create Calendar Event” button
        driver.findElement(By.cssSelector("[title='Create Calendar event']")).click();
        WebElement loadMask = driver.findElement(By.cssSelector("div[class='loader-mask shown']"));
        wait.until(ExpectedConditions.invisibilityOf(loadMask));
        BrowserUtils.wait(2);
        //Then, click on “Cancel” button
        driver.findElement(By.cssSelector("[title=Cancel]")).click();
        BrowserUtils.wait(1);
        //Verify that “All Calendar Events” page subtitle is displayed
        String actualPageSubtitle = driver.findElement(By.className("oro-subtitle")).getText();
        String expectedPageSubtitle = "All Calendar Events";
        Assert.assertEquals(actualPageSubtitle, expectedPageSubtitle);
    }

    @Test(description = "Test case 5: Start and End times")
    public void test5(){
        //Click on “Create Calendar Event” button
        driver.findElement(By.cssSelector("[title='Create Calendar event']")).click();
        WebElement loadMask = driver.findElement(By.cssSelector("div[class='loader-mask shown']"));
        wait.until(ExpectedConditions.invisibilityOf(loadMask));
        BrowserUtils.wait(2);
        //Start time
        WebElement startTimeElement = driver.findElement(By.cssSelector("[id^='time_selector_oro_calendar_event_form_start']"));
        String startTime = startTimeElement.getAttribute("value");
        //End time
        WebElement endTimeElement = driver.findElement(By.cssSelector("[id^='time_selector_oro_calendar_event_form_end']"));
        String endTime = endTimeElement.getAttribute("value");
        //Breaking down the start time to hour, minute, and AM/PM
        String[] time = startTime.split(" ");
        int startHour = Integer.parseInt(time[0].substring(0, time[0].indexOf(":")));
        int startMinute = Integer.parseInt(time[0].substring(time[0].indexOf(":")+1, time[0].length()));
        String startAmPm = time[1];
        //Breaking down the end time to hour, minute, and AM/PM
        String[] time2 = endTime.split(" ");
        int endHour = Integer.parseInt(time2[0].substring(0, time2[0].indexOf(":")));
        int endMinute = Integer.parseInt(time2[0].substring(time2[0].indexOf(":")+1, time2[0].length()));
        String endAmPm = time[1];
        //Verify that difference between end and start time is exactly 1 hour
        Assert.assertEquals(startMinute, endMinute);
        if (startHour == 11) {
            Assert.assertEquals(endHour, 12);
            if (startAmPm == "AM")
                Assert.assertEquals(endAmPm, "PM");
            else if (startAmPm == "PM")
                Assert.assertEquals(endAmPm, "AM");
        } else{
            Assert.assertEquals(endHour-startHour, 1);
            Assert.assertEquals(endAmPm, startAmPm);
        }
    }

    @Test(description = "Test case 6: Select start time")
    public void test6(){
        //Click on “Create Calendar Event” button
        driver.findElement(By.cssSelector("[title='Create Calendar event']")).click();
        WebElement loadMask = driver.findElement(By.cssSelector("div[class='loader-mask shown']"));
        wait.until(ExpectedConditions.invisibilityOf(loadMask));
        BrowserUtils.wait(2);
        //Select “9:00 PM” as a start time
        driver.findElement(By.cssSelector("[id^='time_selector_oro_calendar_event_form_start']")).click();
        driver.findElement(By.xpath("//li[text()='9:00 PM']")).click();
        BrowserUtils.wait(1);
        //Verify that end time is equals to “10:00 PM”
        String actualEndTime = driver.findElement(By.cssSelector("[id^='time_selector_oro_calendar_event_form_end']")).getAttribute("value");
        String expectedEndTime = "10:00 PM";
        Assert.assertEquals(actualEndTime, expectedEndTime);
    }

    @Test(description = "Test case 7: All-Day Event")
    public void test7(){
        //Click on “Create Calendar Event” button
        driver.findElement(By.cssSelector("[title='Create Calendar event']")).click();
        WebElement loadMask = driver.findElement(By.cssSelector("div[class='loader-mask shown']"));
        wait.until(ExpectedConditions.invisibilityOf(loadMask));
        //Select “All-Day Event” checkbox
        driver.findElement(By.cssSelector("[data-name=field__all-day]")).click();
        BrowserUtils.wait(2);
        //Verify that start and end time input boxes are not displayed
        WebElement startTimeElement = driver.findElement(By.cssSelector("[id^='time_selector_oro_calendar_event_form_start']"));
        Assert.assertEquals(startTimeElement.isDisplayed(), false);
        WebElement endTimeElement = driver.findElement(By.cssSelector("[id^='time_selector_oro_calendar_event_form_end']"));
        Assert.assertEquals(endTimeElement.isDisplayed(), false);
        //Verify that start and end date input boxes are displayed
        WebElement startDate = driver.findElement(By.cssSelector("[id^='date_selector_oro_calendar_event_form_start']"));
        Assert.assertEquals(startDate.isDisplayed(), true);
        WebElement endDate = driver.findElement(By.cssSelector("[id^='date_selector_oro_calendar_event_form_end']"));
        Assert.assertEquals(endDate.isDisplayed(), true);
    }

    @Test(description = "Test case 8: Repeat checkbox")
    public void test8(){
        //Click on “Create Calendar Event” button
        driver.findElement(By.cssSelector("[title='Create Calendar event']")).click();
        WebElement loadMask = driver.findElement(By.cssSelector("div[class='loader-mask shown']"));
        wait.until(ExpectedConditions.invisibilityOf(loadMask));
        //Select “Repeat” checkbox
        WebElement repeatCheckbox = driver.findElement(By.cssSelector("[data-name='recurrence-repeat']"));
        repeatCheckbox.click();
        BrowserUtils.wait(2);
        //Verify that “Repeat” checkbox is selected
        Assert.assertEquals(repeatCheckbox.isSelected(), true);
        //Verify that “Daily” is selected by default...
        Select repeatsDropdown = new Select (driver.findElement(By.cssSelector("[data-name='recurrence-repeats']")));
        Assert.assertEquals(repeatsDropdown.getAllSelectedOptions().get(0).getText(), "Daily");
        //...and following options are available in “Repeats” drop-down:
        Assert.assertEquals(repeatsDropdown.getOptions().get(1).getText(), "Weekly");
        Assert.assertEquals(repeatsDropdown.getOptions().get(2).getText(), "Monthly");
        Assert.assertEquals(repeatsDropdown.getOptions().get(3).getText(), "Yearly");
    }

    @Test(description = "Test case 9: Repeat selections and summary")
    public void test9(){
        //Click on “Create Calendar Event” button
        driver.findElement(By.cssSelector("[title='Create Calendar event']")).click();
        WebElement loadMask = driver.findElement(By.cssSelector("div[class='loader-mask shown']"));
        wait.until(ExpectedConditions.invisibilityOf(loadMask));
        //Select “Repeat” checkbox
        WebElement repeatCheckbox = driver.findElement(By.cssSelector("[data-name='recurrence-repeat']"));
        repeatCheckbox.click();
        BrowserUtils.wait(2);
        //Verify that “Repeat” checkbox is selected
        Assert.assertEquals(repeatCheckbox.isSelected(), true);
        //Verify that “Repeat Every” radio button is selected
        List<WebElement> radioButtons = driver.findElements(By.cssSelector("[type='radio']"));
        Assert.assertEquals(radioButtons.get(0).isSelected(), true);
        //Verify that “Never” radio button is selected as an “Ends” option.
        Assert.assertEquals(radioButtons.get(2).isSelected(), true);
        //Verify that following message as a summary is displayed: “Summary: Daily every 1 day”
        WebElement summary = driver.findElement(By.cssSelector("[data-name='recurrence-summary']"));
        String expectedSummary = "Daily every 1 day";
        Assert.assertEquals(summary.getText(), expectedSummary);
    }

    @Test(description = "Test case 10: Occurences")
    public void test10(){
        //Click on “Create Calendar Event” button
        driver.findElement(By.cssSelector("[title='Create Calendar event']")).click();
        WebElement loadMask = driver.findElement(By.cssSelector("div[class='loader-mask shown']"));
        wait.until(ExpectedConditions.invisibilityOf(loadMask));
        //Select “Repeat” checkbox
        driver.findElement(By.cssSelector("[data-name='recurrence-repeat']")).click();
        BrowserUtils.wait(1);
        //Select “After 10 occurrences” as an “Ends” option.
        List<WebElement> radioButtons = driver.findElements(By.cssSelector("[type='radio']"));
        radioButtons.get(3).click();
        BrowserUtils.wait(1);
        driver.findElement(By.cssSelector("[data-related-field=occurrences]")).sendKeys("10", Keys.ENTER);
        BrowserUtils.wait(1);
        //Verify that following message as a summary is displayed: “Summary: Daily every 1 day, endafter 10 occurrences”
        WebElement summary = driver.findElement(By.cssSelector("[data-name='recurrence-summary']"));
        String expectedSummary = "Daily every 1 day, end after 10 occurrences";
        Assert.assertEquals(summary.getText(), expectedSummary);
    }

    @Test(description = "Test case 11: Ends by date")
    public void test11(){
        //Click on “Create Calendar Event” button
        driver.findElement(By.cssSelector("[title='Create Calendar event']")).click();
        WebElement loadMask = driver.findElement(By.cssSelector("div[class='loader-mask shown']"));
        wait.until(ExpectedConditions.invisibilityOf(loadMask));
        //Select “Repeat” checkbox
        driver.findElement(By.cssSelector("[data-name='recurrence-repeat']")).click();
        BrowserUtils.wait(1);
        //Select “By Nov 18, 2021” as an “Ends” option.
        List<WebElement> radioButtons = driver.findElements(By.cssSelector("[type='radio']"));
        radioButtons.get(4).click();
        BrowserUtils.wait(1);
        driver.findElement(By.cssSelector("input[placeholder='Choose a date'][class^=datepicker]")).click();
        BrowserUtils.wait(1);
        //Select month = new Select(driver.findElement(By.cssSelector("[data-handler='selectMonth']")));
        //Month has only December option
        Select year = new Select(driver.findElement(By.cssSelector("[data-handler='selectYear']")));
        year.selectByVisibleText("2021");
        driver.findElement(By.xpath("//a[@class='ui-state-default'][text()='18']")).click();
        BrowserUtils.wait(1);
        //Verify that following message as a summary is displayed: “Summary: Daily every 1 day, end by Nov 18, 2021”
        WebElement summary = driver.findElement(By.cssSelector("[data-name='recurrence-summary']"));
        String expectedSummary = "Daily every 1 day, end by Dec 18, 2021";
        Assert.assertEquals(summary.getText(), expectedSummary);
    }

    @Test(description = "Test case 12: Occurence on certain days of week")
    public void test12(){
        //Click on “Create Calendar Event” button
        driver.findElement(By.cssSelector("[title='Create Calendar event']")).click();
        WebElement loadMask = driver.findElement(By.cssSelector("div[class='loader-mask shown']"));
        wait.until(ExpectedConditions.invisibilityOf(loadMask));
        //Select “Repeat” checkbox
        driver.findElement(By.cssSelector("[data-name='recurrence-repeat']")).click();
        BrowserUtils.wait(1);
        //Select “Weekly” options as a “Repeat” option
        Select repeatsDropdown = new Select (driver.findElement(By.cssSelector("[data-name='recurrence-repeats']")));
        repeatsDropdown.getOptions().get(1).click();
        BrowserUtils.wait(1);
        //Select “Monday and Friday” options as a “Repeat On” options
        List<WebElement> dayOfWeek = driver.findElements(By.className("multi-checkbox-control__item"));
        dayOfWeek.get(1).click();
        dayOfWeek.get(5).click();
        BrowserUtils.wait(2);
        //Verify that “Monday and Friday” options are selected
        Assert.assertEquals(dayOfWeek.get(1).isEnabled(), true);
        Assert.assertEquals(dayOfWeek.get(5).isEnabled(), true);
        //Verify that following message as a summary is displayed: “Summary: Weekly every 1 week onMonday, Friday”
        WebElement summary = driver.findElement(By.cssSelector("[data-name='recurrence-summary']"));
        String expectedSummary = "Weekly every 1 week on Monday, Friday";
        Assert.assertEquals(summary.getText(), expectedSummary);
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }

}
