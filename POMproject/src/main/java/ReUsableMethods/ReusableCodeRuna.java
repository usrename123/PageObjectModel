package ReUsableMethods;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ReusableCodeRuna {
	
	static WebDriver driver;
	static ExtentReports reports;
	static ExtentTest logger;
	
	public static void selectValueFromDropDowntext(WebElement ele,String text) {
		Select s=new Select(ele);
		s.selectByVisibleText(text);;
	}
	public static void waitmethod() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	public static void selectValueFromDropDown(WebElement ele,String value) {
		Select s=new Select(ele);
		s.selectByValue(value);
		
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
	
	public static void InitializeReport() {
		reports = new ExtentReports(System.getProperty("user.dir") + "/report/" + 
				new SimpleDateFormat("'SalesForceReport_'YYYYMMddHHmmss'.html'").format(new Date()));
	}
	
	public static void InitializeDriver() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://login.salesforce.com");
	}
	
	public static void CloseDriverAndReport() {
		driver.close();
		reports.flush();
	}
	public static void CloseDriver() {
		driver.quit();
	}
	
	public static void entertext(WebElement ele, String textVal, String ObjectName) {
//		if(ele.isDisplayed() && ele.isEnabled()) {
//			logger.log(LogStatus.INFO, ObjectName + " field is found");
			ele.sendKeys(textVal);
		//}
//		else {
//			logger.log(LogStatus.FAIL, ObjectName + " field is not found or not editable");
//		}
	}
	public static void login(WebElement ele, String ObjectName) {
//		if(ele.isDisplayed() && ele.isEnabled()) {
//			logger.log(LogStatus.INFO, ObjectName + " button is found");
			ele.click();
//		}
//		else {
//			logger.log(LogStatus.FAIL, ObjectName + " button is not found");
//		}
	}
	public static void drop_down(WebElement ele,String ObjectName ) {
		if(ele.isDisplayed() && ele.isEnabled()) {
			logger.log(LogStatus.INFO, ObjectName +"DropDown is present");
			ele.click();
		}
		else {
			logger.log(LogStatus.FAIL, ObjectName +"DropDown is not displayed");
			
		}
	}
	
	public static void clickElement(WebElement ele, String ObjectName) {
//		if(ele.isDisplayed() && ele.isEnabled()) {
//			logger.log(LogStatus.INFO, ObjectName + " field is found");
			ele.click();;
		}
//		else {
//			logger.log(LogStatus.FAIL, ObjectName + " field is not found or not clickable");
//		}
//	}
	public static void logOut(WebElement ele, String ObjectName) {
		if(ele.isDisplayed() && ele.isEnabled()) {
			logger.log(LogStatus.INFO, ObjectName + " field is found");
			ele.click();
		}
		else {
			logger.log(LogStatus.FAIL, ObjectName + " field is not found or not clickable");
		}
	}

	public static void validate(WebElement ele,String s,String s2) {
		
      if(s.equalsIgnoreCase(s2)) {
    	  System.out.println("valdation success");
    	  logger.log(LogStatus.PASS, "validation is successfull");
    	  
      }
	}
		
	}

