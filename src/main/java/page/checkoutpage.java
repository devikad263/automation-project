package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class checkoutpage {

	WebDriver driver;

	@FindBy(xpath = "//*[@id='shopping_cart_container']/a")
	WebElement cart;

	@FindBy(id = "checkout")
	WebElement checkoutBtn;

	public checkoutpage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void checkout() throws InterruptedException {
		cart.click();
		Thread.sleep(1000);
		checkoutBtn.click();
	}
}