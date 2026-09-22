package test;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import base.Reporterclass;
import base.baseclass;
import page.loginpage;
import page.productpage;

public class producttest extends baseclass {
	
	loginpage log;
	productpage pdt;
	
	@Test(priority = 1)
	public void logo() throws IOException
	{
		driver.get("https://www.saucedemo.com/");
		log = new loginpage(driver);
		pdt = new productpage(driver);
		
		log.setvalue("standard_user", "secret_sauce");
		
		test = extend.createTest("Logo displayed in product page");
		
		WebElement x = pdt.logoverify();
		if(x.isDisplayed())
		{
			test.pass("logo displayed in product page");
		}
		else
		{
			String path = Reporterclass.screenshotMethod(driver, "logo validation");	
			test.addScreenCaptureFromPath(path);
			test.fail("Logo not displayed");		
		}
	}
	
	@Test(priority = 2)
	public void additemstocart() throws InterruptedException
	{
		if (extend != null) {
			test = extend.createTest("Add Items to Cart Test");
		}

		pdt = new productpage(driver);
		pdt.additem();
		
		List<WebElement> titles = pdt.producttls();
		List<WebElement> descriptions = pdt.productDtls();
		
		System.out.println("Total number of products: " + titles.size());
		System.out.println("==================================================");
		
		// Pair each title directly with its description using an index loop
		for(int i = 0; i < titles.size(); i++)
		{
			System.out.println("Product " + (i + 1) + ": " + titles.get(i).getText());
			System.out.println("Description: " + descriptions.get(i).getText());
			System.out.println("--------------------------------------------------");
			
			// Optional: Attach formatted info to Extent Reports
			if (test != null) {
				test.info("<b>" + titles.get(i).getText() + "</b><br>" + descriptions.get(i).getText());
			}
		}

		if (test != null) {
			test.pass("Successfully fetched product details and added items to cart.");
		}
	}
}