package com.qa.Tekarch.testProject.BaseClass;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.qa.BrowserUtilities.BrowserUtilities;
import com.qa.Teharch.testProject.CommonUtillitie.commontUtil;
import com.qa.constants.Constants;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

@Listeners(com.qa.Testlisteners.TestNgListeners.class)

public class TestBase {

  public static WebDriver driver;
  public static commontUtil oCommUtil = new commontUtil();
  public static BrowserUtilities oBroUti = new BrowserUtilities();
  public static Constants oCons = new Constants();

  public static ExtentReports reports;
  public static ExtentTest logger;

  public  void InitializeReport() {
    System.out.println(System.getProperty("user.dir") + "/report/");
    // reports= new ExtentReports(System.getProperty("user.dir") +
    // "/report/myreport.html", true);
    reports = new ExtentReports(System.getProperty("user.dir") + "/report/"
        + new SimpleDateFormat("'Test_reports'YYYYMMddHHmmss'.html'").format(new Date()));
  }

  public String takeScreenshot() throws IOException {
    TakesScreenshot srcShot = ((TakesScreenshot) driver);
    File srcFile = srcShot.getScreenshotAs(OutputType.FILE);
    String imagePath = System.getProperty("user.dir") + "/report/myreport.html" + "ScreenShots/"
        + new SimpleDateFormat("'Image_'YYYYMMddHHmmss'.png'").format(new Date());
    File destFile = new File(imagePath);
    FileUtils.copyFile(srcFile, destFile);
    return imagePath;
  }

  @BeforeSuite
  public void InitializeDependencies() throws Exception {
    System.out.println(System.getProperty("user.dir"));
    oCommUtil.loadPropertyFiles(
        System.getProperty("user.dir") + "\\src\\main\\java\\com\\qa\\Tekarch\\testProject\\config\\property\\config.properties");

        oCommUtil.loadLog4jProperty(
        System.getProperty("user.dir") + "\\src\\main\\java\\com\\qa\\Tekarch\\testProject\\config\\property\\log4j.properties");

    driver = BrowserUtilities.launchBrowser(System.getProperty("Browser"));

    InitializeReport();
  }

  @AfterSuite
  public void ShuttingDownAllDependencies() {
    driver.close();
    driver.quit();
  }

}
	


