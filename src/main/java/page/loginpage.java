package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginpage {
	
	@FindBy(id="user-name")
	WebElement username;
	
	@FindBy(id="password")
	WebElement pass;
	
	@FindBy(id="login-button")
	WebElement login;
	
	@FindBy(xpath="//*[@id=\"root\"]/div/div[1]")
	WebElement logo;

	public loginpage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		
		PageFactory.initElements(driver, this);
	}

	public WebElement logoverification()
	{
		return logo;
	}
	
	public WebElement buttontext()
	{
		return login;
	}
	
	public void setvalue(String user,String pwd)
	{
		username.sendKeys(user);
		pass.sendKeys(pwd);
		login.click();
	}

}
