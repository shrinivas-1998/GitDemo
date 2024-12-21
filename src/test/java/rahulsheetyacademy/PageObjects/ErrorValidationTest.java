package rahulsheetyacademy.PageObjects;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import shrinivas.TestComponents.BaseTest;
import shrinivas.TestComponents.Retry;

public class ErrorValidationTest extends BaseTest 
{

	@Test(groups = { "ErrorHandling" }, retryAnalyzer = Retry.class)
	public void LoginCredentialsValidation() throws InterruptedException, IOException 
	{
		// Target product name to select
		String ProductName = "ZARA COAT 3";

		// Open the application URL and log in
		landingpage.LoginApplication("sb@gamail.com", "d!DAKFYAjzRNc9J");
		// landingpage.getErrorMessage();
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());
	}

	@Test
	public void productionErrorValidation() throws InterruptedException, IOException 
	{
		// Target product name to select
		String ProductName = "ZARA COAT 3";

		// Open the application URL and log in
		ProductCatlogue productcatlogue = landingpage.LoginApplication("yogibaba321@gmail.com", "Shrinivas@#123");
		Thread.sleep(10000);
		// Getting list of products available on the site
		List<WebElement> products = productcatlogue.getProductList();
		productcatlogue.addProductToCart(ProductName);
		// Clicking on Cart Page
		productcatlogue.goToCartPage();
		// Verify that the selected product is present in the cart
		CartPage cartpage = new CartPage(driver);
		Boolean match = cartpage.VerifyProductDisplay(ProductName);
		Assert.assertTrue(match);
	}
}
