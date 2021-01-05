package com.qa.PO.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.Tekarch.testProject.BaseClass.TestBase;

public class PO_cartCheckOut_page extends TestBase {
	public PO_cartCheckOut_page(WebDriver driver) {
		TestBase.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
  @FindBy(xpath="//body/div[@id='root']/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")
  WebElement promo_code ;
  @FindBy(xpath="//button[contains(text(),'Apply')]")
  WebElement apply_Code;
  @FindBy(xpath="//b[contains(text(),'Total After Discount :')]")
  WebElement Total_AfterDiscount;
  @FindBy(xpath="//button[contains(text(),'Place Order')]")
  WebElement Place_Order;
  
  public void PromoCode() throws Exception {
	  oBroUti.waitForElementVisible(driver, promo_code, oCons.WAIT_COMMON);
      oBroUti.ufSendKeys(driver, promo_code, "rahulshettyacademy", oCons.WAIT_COMMON);

	}
  public void applyCode() throws Exception {
		oBroUti.waitForElementVisible(driver, apply_Code, oCons.WAIT_COMMON);
		oBroUti.ufClick(driver, apply_Code, oCons.WAIT_COMMON);
  
}
  public void PlaceOrder() throws Exception {
		oBroUti.waitForElementVisible(driver, Place_Order, oCons.WAIT_COMMON);
		oBroUti.ufClick(driver, Place_Order, oCons.WAIT_COMMON);
  
}
}

