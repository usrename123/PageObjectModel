package com.qa.BrowserUtilities;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Function;
import com.qa.Tekarch.testProject.BaseClass.TestBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.FluentWait;

import io.github.bonigarcia.wdm.WebDriverManager;

//All our resuable code will be used from here.
public class BrowserUtilities extends TestBase {

    public static WebDriver launchBrowser(String browser) throws Exception {

        if (browser.toLowerCase().startsWith("ch")) {
          WebDriverManager.chromedriver().setup();
          ChromeOptions chromeOptions = new ChromeOptions();
          chromeOptions.addArguments("--disable-notifications");
          driver = new ChromeDriver(chromeOptions);
        } else if (
          browser.equalsIgnoreCase("ff") || browser.toLowerCase().startsWith("fi")
        ) {
          // create firefox instance
          WebDriverManager.firefoxdriver().setup();
          FirefoxOptions options = new FirefoxOptions();
          options.setProfile(new FirefoxProfile());
          options.addPreference("dom.webnotifications.enabled", false);
          driver = new FirefoxDriver(options);
        } else if (browser.equalsIgnoreCase("Edge")) {
          WebDriverManager.edgedriver().setup();
          driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("ie")) {
          WebDriverManager.iedriver().setup();
          driver = new EdgeDriver();
        } else {
          // If no browser passed throw exception
          throw new Exception ("Browser is not correct");
        }
        driver.manage().window().maximize();
        return driver;
      }
      @SuppressWarnings("deprecation")
  public boolean waitForElementVisible(WebDriver driver, final WebElement actualErrormessage, int iTimeInSeconds) {

    FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
      .withTimeout(iTimeInSeconds, TimeUnit.SECONDS)
      .pollingEvery(500, TimeUnit.MILLISECONDS)
      .ignoring(NoSuchElementException.class);
      wait.until(
        new Function<WebDriver, WebElement>() {
  
          public WebElement apply(WebDriver driver) {
            if (actualErrormessage.isDisplayed()) {
              return actualErrormessage;
            } else {
              return null;
            }
          }
        }
      );
  
      return actualErrormessage.isDisplayed();

      }

      public void ufSendKeys(WebDriver driver, WebElement ele, String keysToSend)
      throws Exception {
      ufSendKeys(driver, ele, keysToSend, oCons.WAIT_COMMON);
    }
  
    public void ufSendKeys(
      WebDriver driver,
      WebElement ele,
      String keysToSend,
      int iTimeInSeconds
    )
      throws Exception  {
      waitForElementVisible(driver, ele, iTimeInSeconds);
      ele.sendKeys(keysToSend);
    }

  public void ufClick(WebDriver driver, WebElement ele) throws Exception {
    ufClick(driver, ele, oCons.WAIT_COMMON);
  }

  public void ufClick(WebDriver driver, WebElement ele, int iTimeInSeconds)
    throws Exception {
    waitForElementVisible(driver, ele, iTimeInSeconds);
    ele.click();
  }


  
}
