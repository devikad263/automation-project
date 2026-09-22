package test;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.Reporterclass;
import base.baseclass;
import page.loginpage;


public class logintest extends baseclass {
	
	loginpage log;
	
	@DataProvider(name="loginData")
	public Object[][] getdata() throws IOException {
		
		String path="C:\\Users\\admin\\OneDrive\\Desktop\\selinium automation\\swag.xlsx";
		return utilitiespkg.utilities.getTestData(path, "sheet1");
		
	}
	
	@Test(dataProvider = "loginData",priority = 1)
	public void loginTest(String user, String password) throws IOException
	{
		driver.get("https://www.saucedemo.com/");
		test=extend.createTest("Login test -"+user);
		log=new loginpage(driver);
		log.setvalue(user, password);
		System.out.println("Executed for :"+user);
		String currenturl=driver.getCurrentUrl();
		
		if(currenturl.equals("https://www.saucedemo.com/inventory.html"))
		{
			test.pass("Login successful for :"+user);
			
		}
		else
		{
			String screenshotname="Login failed_"+user;
			Reporterclass.screenshotMethod(driver,screenshotname);
			test.addScreenCaptureFromPath("screenshot\\"+screenshotname+".png");
			test.fail("Login failed for :"+user);
			Assert.fail("Login failed for :"+user);				
		}
		
	}
	
	@Test(priority = 2)
	public void logoverification() throws IOException
	{
		driver.get("https://www.saucedemo.com/");
		test=extend.createTest("Logo validation test");
		log=new loginpage(driver);
		WebElement x=log.logoverification();
		
		if(x.isDisplayed())
		{
			test.pass("Logo displayed");
		}
		else
		{
			String screenshotname="logo_validation";
			Reporterclass.screenshotMethod(driver,screenshotname);
			test.addScreenCaptureFromPath("screenshot\\"+screenshotname+".png");
			test.fail("Logo not displayed");
			Assert.fail("Logo not displayed");				
		
		}
	}
	@Test(priority = 3)
	public void buttontext() throws IOException
	{
		driver.get("https://www.saucedemo.com/");
		test=extend.createTest("Button text validation test");
		log=new loginpage(driver);
		WebElement b=log.buttontext();
		
		if(b.isDisplayed())
		{
			test.pass("Button text displayed");
		}
		else
		{
			String screenshotname="Buttontext_validation";
			Reporterclass.screenshotMethod(driver,screenshotname);
			test.addScreenCaptureFromPath("screenshot\\"+screenshotname+".png");
			test.fail("Buttontext not displayed");
			Assert.fail("Buttontext not displayed");				
		
		}
	}
	
	
	

}
