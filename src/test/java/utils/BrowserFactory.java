package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {

    // We will create a method that will return driver object
    // This method will take one parameter = String browser
    // Based on the value of the browser parameter
    // method will return corresponding webdriver object
    // if browser = chrome, then return chromedriver object
    public static WebDriver getDriver(String browser){
        if(browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("safari")){
            //WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        }
        return null;
    }

}
