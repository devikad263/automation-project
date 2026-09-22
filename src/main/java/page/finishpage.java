package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class finishpage {
	
	WebDriver driver;

	@FindBy(id = "first-name")
	WebElement firstName;

	@FindBy(id = "last-name")
	WebElement lastName;

	@FindBy(id = "postal-code")
	WebElement postalCode;

	@FindBy(id = "continue")
	WebElement continueBtn;

	@FindBy(id = "finish")
	WebElement finishBtn;

	@FindBy(className = "complete-header")
	WebElement completeHeader;

	public finishpage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterInformation(String fname, String lname, String pcode) {
		firstName.sendKeys(fname);
		lastName.sendKeys(lname);
		postalCode.sendKeys(pcode);
		continueBtn.click();
	}

	public void clickFinish() {
		finishBtn.click();
	}

	public WebElement orderVerification() {
		return completeHeader;
	}
	
	

}
