package rahulsheetyacademy.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductCatlogue extends AbstarctComponents 
{
	WebDriver driver;

	public ProductCatlogue(WebDriver driver) 
	{
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Retrieve the list of products displayed on the page
	// List<WebElement> productList = driver.findElements(By.cssSelector(".card"));
	// PageFactory
	@FindBy(css = ".card")
	List<WebElement> products;

	@FindBy(css = ".ng-animating")
	WebElement spinner;

	By productsBy = By.cssSelector(".card");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By ToastMessage = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {
		WaitForElementToAppear(productsBy);
		return products;
	}

	public WebElement getProductByName(String ProductName) 
	{
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst()
				.orElse(null);
		return prod;
	}

	public void addProductToCart(String ProductName) 
	{
		WebElement prod = getProductByName(ProductName);
		prod.findElement(addToCart).click();
		WaitForElementToAppear(ToastMessage);
		WaitForElementToDisappear(spinner);
	}
}
