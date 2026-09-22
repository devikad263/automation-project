package test;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.Reporterclass;
import base.baseclass;
import page.checkoutpage;
import page.finishpage;
import page.loginpage;
import page.productpage;

public class finishtest extends baseclass {

	loginpage log;
	productpage pdt;
	checkoutpage ckp;
	finishpage fnp;

	@Test(priority = 1)
	public void finishOrderTest() throws InterruptedException, IOException {
		driver.get("https://www.saucedemo.com/");

		log = new loginpage(driver);
		pdt = new productpage(driver);
		ckp = new checkoutpage(driver);
		fnp = new finishpage(driver);

		if (extend != null) {
			test = extend.createTest("Finish Order Test");
		}

	
		log.setvalue("standard_user", "secret_sauce");

	
		pdt.additem();

	
		ckp.checkout();

		
		fnp.enterInformation("Devika", "Tester", "691001");
		fnp.clickFinish();

	
		WebElement confirmation = fnp.orderVerification();
		if (confirmation.isDisplayed() && confirmation.getText().contains("Thank you for your order")) {
			if (test != null) {
				test.pass("Order placed successfully! Message: " + confirmation.getText());
			}
		} else {
			String screenshotPath = Reporterclass.screenshotMethod(driver, "finish_order_failure");
			if (test != null) {
				test.addScreenCaptureFromPath(screenshotPath);
				test.fail("Order confirmation header was not displayed.");
			}
			Assert.fail("Order confirmation header was not displayed.");
		}
	}
}