package page;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class productpage {
	
	@FindBy(xpath = "//*[text()='Add to cart']")
	List<WebElement> addtocart;
	
	@FindBy(className = "inventory_item_name")
	List<WebElement> productTitles;
	
	@FindBy(className="inventory_item_desc")
	List<WebElement> productdesc;
	
	
	@FindBy(className = "app_logo")
	WebElement logo;
	
	WebDriver driver;
	
	public productpage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

	public WebElement logoverify() {
		return logo;
	}
	
	
	public void additem() throws InterruptedException {
		int count = addtocart.size();

		for(int i = 0; i < count; i++) {
			addtocart.get(0).click();
			Thread.sleep(3000);
		}
	}
	
	
	public List<WebElement> producttls() {
		return productTitles;
	}
	
	public List<WebElement> productDtls() {
		return productdesc;
	}
	
	
}