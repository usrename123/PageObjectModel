package com.qa.PO.Pages;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.Tekarch.testProject.BaseClass.TestBase;

public class POvegetable_Select extends TestBase {
	public POvegetable_Select(WebDriver driver) {
		TestBase.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h4[@class='product-name']")
	WebElement productElement;
	@FindBy(xpath = "//div[@class='product-action']/button")
	WebElement action_button;
	@FindBy(xpath = "//a[@class='cart-icon']")
	WebElement cart_icon;
	@FindBy(xpath = "//div[@class='action-block']/button")
	WebElement proceed_to_chechout;

//==============================================
	public void add_items() throws InterruptedException {
		String[] listOfVegetables = { "Brocolli", "Cucumber", "Beetroot" };
		List<WebElement> products = driver.findElements(By.xpath("//h4[@class='product-name']"));
		int j = 0;
		for (int i = 0; i < products.size(); i++) {

			String[] name = products.get(i).getText().split("-");

			String formattedName = name[0].trim();

			List<String> listNeeded = Arrays.asList(listOfVegetables);
			if (listNeeded.contains(formattedName)) {
				j++;
				driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();

				if (j >= listOfVegetables.length) {
					break;

				}
			}
		}

	}

	public void click_On_ProceedToChectOut() throws Exception {
		oBroUti.waitForElementVisible(driver, proceed_to_chechout, oCons.WAIT_COMMON);
		oBroUti.ufClick(driver, proceed_to_chechout, oCons.WAIT_COMMON);

	}

	public void click_On_ChartIcon() throws Exception {
		oBroUti.waitForElementVisible(driver, cart_icon, oCons.WAIT_COMMON);
		oBroUti.ufClick(driver, cart_icon, oCons.WAIT_COMMON);

	}

}
