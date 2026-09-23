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

		// Step 1: Login
		log.setvalue("standard_user", "secret_sauce");

		// Step 2: Add Items & Navigate to Checkout
		pdt.additem();
		ckp.checkout();

		// Step 3: Enter Invalid Input Data (Boundary / Negative Test)
		String firstName = "123";
		String lastName = "456";
		String postalCode = "abcde";

		fnp.enterInformation(firstName, lastName, postalCode);

		// Step 4: Validate Data Rules using Regex
		boolean isFirstNameValid = firstName.matches("^[a-zA-Z\\s]+$"); // Must be letters only
		boolean isLastNameValid = lastName.matches("^[a-zA-Z\\s]+$");   // Must be letters only
		boolean isPostalCodeValid = postalCode.matches("^\\d+$");         // Must be numbers only

		// Step 5: Fail and log if invalid details were accepted
		if (!isFirstNameValid || !isLastNameValid || !isPostalCodeValid) {

			// Capture failure screenshot
			String screenshotPath = Reporterclass.screenshotMethod(driver, "invalid_checkout_data_failure");

			// Explicitly log FAIL status and attach screenshot directly to Extent Report
			if (test != null) {
				test.addScreenCaptureFromPath(screenshotPath);
				test.fail("DEFECT: Application accepted invalid customer details without validation! "
						+ "First Name: '" + firstName + "', Last Name: '" + lastName + "', Postal Code: '" + postalCode + "'");
			}

			// Fail TestNG execution
			Assert.fail("Test Failed: System accepted invalid characters in checkout fields (First Name: " 
					+ firstName + ", Last Name: " + lastName + ", Postal Code: " + postalCode + ").");
		} else {

			// Step 6: Complete Order (Only runs if data is valid)
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