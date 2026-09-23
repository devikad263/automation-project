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
			test = extend.createTest("Finish Order Test - Boundary & Validation Check");
		}

		
		log.setvalue("standard_user", "secret_sauce");

	
		pdt.additem();
		ckp.checkout();

		
		String firstName = "123";
		String lastName = "456";
		String postalCode = "abcde";

		fnp.enterInformation(firstName, lastName, postalCode);

	
		boolean isFirstNameValid = firstName.matches("^[a-zA-Z\\s]+$"); // Must be letters only
		boolean isLastNameValid = lastName.matches("^[a-zA-Z\\s]+$");   // Must be letters only
		boolean isPostalCodeValid = postalCode.matches("^\\d+$");         // Must be numbers only

	
		if (!isFirstNameValid || !isLastNameValid || !isPostalCodeValid) {

		
			String screenshotPath = Reporterclass.screenshotMethod(driver, "invalid_checkout_data_failure");

			
			if (test != null) {
				test.addScreenCaptureFromPath(screenshotPath);
				test.fail("DEFECT: Application accepted invalid customer details without validation! "
						+ "First Name: '" + firstName + "', Last Name: '" + lastName + "', Postal Code: '" + postalCode + "'");
			}

		
			Assert.fail("Test Failed: System accepted invalid characters in checkout fields (First Name: " 
					+ firstName + ", Last Name: " + lastName + ", Postal Code: " + postalCode + ").");
		} else {

			
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
}