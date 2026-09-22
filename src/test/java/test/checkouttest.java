package test;

import java.io.IOException;

import org.testng.annotations.Test;

import base.baseclass;
import page.checkoutpage;
import page.loginpage;
import page.productpage;

public class checkouttest extends baseclass {

	loginpage log;
	productpage pdt;
	checkoutpage ckp;

	@Test(priority = 1)
	public void checkoutTest() throws InterruptedException, IOException {
		driver.get("https://www.saucedemo.com/");

		log = new loginpage(driver);
		pdt = new productpage(driver);
		ckp = new checkoutpage(driver);

		if (extend != null) {
			test = extend.createTest("Checkout Navigation Test");
		}

	
		log.setvalue("standard_user", "secret_sauce");

	
		pdt.additem();

		
		ckp.checkout();

		if (test != null) {
			test.pass("Successfully navigated to checkout step.");
		}
	}
}