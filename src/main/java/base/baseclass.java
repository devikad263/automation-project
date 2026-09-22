package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class baseclass {
	
	public static ExtentReports extend;
	public static ExtentTest test;
	public static WebDriver driver;
	
	public String url="https://www.saucedemo.com/";
	
	@BeforeTest
	public void browser_launch()
	{
		extend=Reporterclass.sam();
		
		driver=new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		
	}
	
	@AfterSuite
	public void teardown()
	{
		driver.quit();
		extend.flush();
	}

	

}
