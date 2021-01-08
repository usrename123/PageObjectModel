package com.qa.Tekarch.TestProject;

import com.relevantcodes.extentreports.LogStatus;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.PO.Pages.POvegetable_Select;
import com.qa.Tekarch.testProject.BaseClass.TestBase;

public class Vegetable_chart extends TestBase {
	Logger log = Logger.getLogger(getClass().getSimpleName());
	POvegetable_Select poVegeCart;

	@BeforeClass
	public void setup() {

		poVegeCart = new POvegetable_Select(driver);
	}

	@Test(priority = 1)
	public void addTest() throws Exception  {
		
		driver.get(oCons.getAppURL());
		logger.log(LogStatus.PASS, "success launch of the application");
		log.info("The Application is successfuly launched");

		poVegeCart.add_items();
		logger.log(LogStatus.INFO, "items were added to the cart");
		log.info("Three Items were selcted in the cart ");
		Thread.sleep(3000);
	}
	@Test
	public void click_on_cart() throws Exception  {
		driver.get(oCons.getAppURL());
		logger.log(LogStatus.PASS, "success launch of the application");
		log.info("The Application is successfuly launched");
		poVegeCart.add_items();
		Thread.sleep(8000);
		logger.log(LogStatus.INFO, "items were added to the cart");
		log.info("Three Items were selcted in the cart ");
		poVegeCart.click_On_ChartIcon();
		
		logger.log(LogStatus.PASS, "Items are shown in the cart and button was clicked : success");
       log.info("Items are present in the cart and and button got clicked to  go to check out page");
		poVegeCart.click_On_ProceedToChectOut();
		Thread.sleep(5000);
		logger.log(LogStatus.INFO, "proceed to check out button was clicked and navigated to the next page");
        log.info("proceed to ckeck out button was clicked and navigated to the next page");
	}
		
	//need to commit again for config

}
