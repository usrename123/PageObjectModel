package com.qa.Tekarch.TestProject;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.PO.Pages.PO_cartCheckOut_page;
import com.qa.PO.Pages.POvegetable_Select;
import com.qa.Tekarch.testProject.BaseClass.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class CartCheckOutPage extends TestBase {
	Logger log = Logger.getLogger(getClass().getSimpleName());
	POvegetable_Select poVegeCart;
	PO_cartCheckOut_page poCheckout;

	@BeforeClass
	public void setup() {

		poVegeCart = new POvegetable_Select(driver);
	}
	@Test
	public void Navigate_To_Checkout() throws Exception {
		driver.get(oCons.getAppURL());
		logger.log(LogStatus.PASS, "success launch of the application");
		log.info("The Application is successfuly launched");
		poVegeCart.add_items();
		poVegeCart.click_On_ChartIcon();
		poVegeCart.click_On_ProceedToChectOut();
		
	}
	@Test(dependsOnMethods= {"Navigate_To_Checkout"})
	public void CheckOut_To_paymentGateway() throws Exception {
		driver.get(oCons.getAppURL());
		logger.log(LogStatus.PASS, "success launch of the application");
		log.info("The Application is successfuly launched");
		poVegeCart.add_items();
		poVegeCart.click_On_ChartIcon();
		poVegeCart.click_On_ProceedToChectOut();
		Thread.sleep(6000);
		WebElement promo_code =driver.findElement(By.xpath("//body/div[@id='root']/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]"));
		promo_code.sendKeys("rahulshettyacademy");
		//poCheckout.PromoCode();
		Thread.sleep(6000);
		//poCheckout.applyCode();
		WebElement applyCode =driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/button[1]"));
		applyCode.click();
		Thread.sleep(6000);
		WebElement Place_Order=driver.findElement(By.xpath("//button[contains(text(),'Place Order')]"));
		Place_Order.click();
		poCheckout.PlaceOrder();
		
		
	}

}
