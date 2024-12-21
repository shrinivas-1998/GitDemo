package rahulsheetyacademy.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage extends AbstarctComponents 
{
	WebDriver driver;

	public OrderPage(WebDriver driver) 
	{
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// driver.findElements(By.cssSelector(".cart h3");
	// PageFactory
	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> orderedProductNames;

	// driver.findElement(By.xpath("//button[text() = 'Checkout']")).click();
	@FindBy(xpath = "//button[text() ='Checkout']")
	WebElement CheckoutElement;

	public boolean VerifyOrderIsDisplay(String ProductName) 
	{
		boolean match = orderedProductNames.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(ProductName));
		return match;
	}

	public void goToCheckout() 
	{
		CheckoutElement.click();
	}

}
