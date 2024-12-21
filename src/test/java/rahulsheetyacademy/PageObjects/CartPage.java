package rahulsheetyacademy.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends AbstarctComponents 
{
	WebDriver driver;

	public CartPage(WebDriver driver) 
	{
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// driver.findElements(By.cssSelector(".cart h3");
	// PageFactory
	@FindBy(css = ".cart h3")
	private List<WebElement> cartProducts;

	// driver.findElement(By.xpath("//button[text() = 'Checkout']")).click();
	@FindBy(xpath = "//button[text() ='Checkout']")
	WebElement CheckoutElement;

	public boolean VerifyProductDisplay(String ProductName) 
	{
		boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(ProductName));
		return match;
	}

	public void goToCheckout() 
	{
		CheckoutElement.click();
	}

}
