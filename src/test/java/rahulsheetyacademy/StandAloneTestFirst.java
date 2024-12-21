package rahulsheetyacademy;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulsheetyacademy.PageObjects.LandingPage;

public class StandAloneTestFirst 
{
	public static void main(String[] args) throws InterruptedException 
	{
		// Target product name to select
		String targetProductName = "ZARA COAT 3";

		// Set up ChromeDriver using WebDriverManager
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		// Configure browser window and timeouts
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Open the application URL
		driver.get("https://rahulshettyacademy.com/client");
		
		LandingPage landingpage = new LandingPage(driver);
		landingpage.LoginApplication("sb@gamail.com", "d!DAKFYAjzRNc9J");

		// Click on the login button
		driver.findElement(By.id("login")).click();
		Thread.sleep(3000);

		// Initialize WebDriverWait for explicit waits
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".card")));

		// Retrieve the list of products displayed on the page
		List<WebElement> productList = driver.findElements(By.cssSelector(".card"));

		// Search for the target product in the product list
		WebElement selectedProduct = productList.stream()
		        .filter(product -> product.findElement(By.cssSelector(".b"))
		                .getText().equals(targetProductName))
		        .findFirst()
		        .orElse(null);

		// Click on the "Add to Cart" button for the selected product
		selectedProduct.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		// Wait until the toast message appears and the loading animation disappears
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		// Click on the cart icon to view cart items
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();

		// Verify that the selected product is present in the cart
		List<WebElement> cartProductList = driver.findElements(By.cssSelector(".cart h3"));
		boolean isProductInCart = cartProductList.stream()
		        .anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(targetProductName));
		Assert.assertTrue(isProductInCart);

		// Click on the "Checkout" button
		driver.findElement(By.xpath("//button[text() = 'Checkout']")).click();

		// Select country using the Actions class
		Actions actions = new Actions(driver);
		actions.sendKeys(driver.findElement(By.cssSelector("[placeholder = 'Select Country']")), "india")
		        .build()
		        .perform();
		Thread.sleep(5000);

		// Wait until the country dropdown appears
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-item")));

		// Select the country from the dropdown list
		driver.findElement(By.xpath("//button[contains(@class, 'ta-item list-group-item ng-star-inserted')][2]")).click();

		// Click on the "Place Order" button
		driver.findElement(By.cssSelector(".action__submit")).click();

		// Validate the confirmation message
		String confirmationMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("Thankyou for the order."));

		// Close the browser
		Thread.sleep(5000);
		driver.quit();
	}
}
