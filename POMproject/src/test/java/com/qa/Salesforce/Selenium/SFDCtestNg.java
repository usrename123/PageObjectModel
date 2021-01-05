package com.qa.Salesforce.Selenium;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SFDCtestNg {

	static WebDriver driver;
	static ExtentReports reports;
	static ExtentTest logger;

	public String takeScreenshot() throws IOException {
		TakesScreenshot srcShot = ((TakesScreenshot) driver);
		File srcFile = srcShot.getScreenshotAs(OutputType.FILE);
		String imagePath = System.getProperty("user.dir") + "/report/myreport.html" + "ScreenShots/"
				+ new SimpleDateFormat("'Image_'YYYYMMddHHmmss'.png'").format(new Date());
		File destFile = new File(imagePath);
		FileUtils.copyFile(srcFile, destFile);
		return imagePath;
	}

	@BeforeTest
	public static void InitializeDriver() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://login.salesforce.com");
	}

	@BeforeTest
	public static void InitializeReport() {
		reports = new ExtentReports(System.getProperty("user.dir") + "/report/"
				+ new SimpleDateFormat("'SalesForceReport_'YYYYMMddHHmmss'.html'").format(new Date()));
	}

	@BeforeTest
	public static void waitmethod() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterSuite
	public static void CloseReport() {

		reports.flush();
		driver.quit();
	}

	SoftAssert soft_assert = new SoftAssert();

	@Test(groups = {"sanity"})
	public void salesforce_TC5() throws InterruptedException {
		InitializeDriver();
		logger = reports.startTest(" TC 5");

		WebElement usernamefield = driver.findElement(By.xpath("//input[@id='username']"));
		usernamefield.sendKeys("123");

		WebElement passwordfield = driver.findElement(By.xpath("//input[@id='password']"));
		passwordfield.sendKeys("22131");
		WebElement login = driver.findElement(By.xpath("//input[@id='Login']"));
		login.click();

		WebElement errorMassage = driver.findElement(By.xpath("//div[@id='error']"));
		String actualerror = errorMassage.getText();
		String expectederror = "Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
		Assert.assertEquals(actualerror, expectederror);

		reports.endTest(logger);
		driver.close();

	}

	@Test(groups= {"sanity"})
	public void salesforce_TC1() throws InterruptedException, IOException {
    
		logger = reports.startTest(" TC 1");
		InitializeDriver();
		WebElement username = driver.findElement(By.xpath("//input[@id='username']"));
		username.sendKeys(" jack@mithun.com");
		WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
		password.sendKeys("24July1996#");

		WebElement login = driver.findElement(By.xpath("//input[@id='Login']"));
		login.click();
		logger.log(LogStatus.INFO, "Login Button Clicked");
		logger.log(LogStatus.PASS, " salesforce_TC1");

		logger.log(LogStatus.PASS, " My Login Test TC1 passed" + logger.addScreenCapture(takeScreenshot()));
		reports.endTest(logger);
		
		driver.close();
	}

	@Test(groups= {"sanity"})
	public void salesforce_TC4A() throws InterruptedException {
		InitializeDriver();
		logger = reports.startTest(" TC4A");
		WebElement username = driver.findElement(By.xpath("//input[@id='username']"));
		username.sendKeys("usrename");

		WebElement clearIt = driver.findElement(By.xpath("//input[@id='password']"));
		clearIt.clear();
		WebElement login = driver.findElement(By.xpath("//input[@id='Login']"));
		login.click();

		String actualErrormessage = driver.findElement(By.xpath("//div[@id='error']")).getText();

		String Expectedmessage = "Please enter your password.";
		assertEquals(actualErrormessage, Expectedmessage);

		WebElement clickit = driver.findElement(By.xpath("//a[@id='forgot_password_link']"));
		clickit.click();
		WebElement usernId = driver.findElement(By.xpath("//input[@id='un']"));
		usernId.sendKeys(" jack@mithun.com");

		WebElement passWdId = driver.findElement(By.xpath("//input[@id='continue']"));
		passWdId.click();
		logger.log(LogStatus.PASS, " salesforce_TC4A");
		reports.endTest(logger);
		driver.close();

	}
}
