package rahulsheetyacademy.PageObjects;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import shrinivas.TestComponents.BaseTest;
import shrinivas.TestComponents.Retry;

public class StandAloneTest extends BaseTest 
{
	String ProductName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" }, retryAnalyzer = Retry.class)
	public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException 
	{
		// Target product name to select

		// Open the application URL and log in
		ProductCatlogue productcatlogue = landingpage.LoginApplication(input.get("email"), input.get("password"));
		Thread.sleep(10000);

		// Getting list of products available on the site
		List<WebElement> products = productcatlogue.getProductList();
		productcatlogue.addProductToCart(input.get("product"));

		// Clicking on Cart Page
		productcatlogue.goToCartPage();

		// Verify that the selected product is present in the cart
		CartPage cartpage = new CartPage(driver);
		Boolean match = cartpage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);

		// Click on the "Checkout" button
		cartpage.goToCheckout();

		Thread.sleep(10000);
		CheckOutPage checkoutpage = new CheckOutPage(driver);
		checkoutpage.SelectCountry("india");
		checkoutpage.submitOrder();

		ConfirmationPage confirmationpage = new ConfirmationPage(driver);
		String confirmationMessage = confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistory() 
	{
		ProductCatlogue productcatlogue = landingpage.LoginApplication("sb@gamail.com", "d!DAKFYAjzRNc9J");
		OrderPage orderpage = productcatlogue.gotoOdersPage();
		Assert.assertTrue(orderpage.VerifyOrderIsDisplay(ProductName));
	}

	// by using only @DataProvider with HasMap
	@DataProvider
	public Object[][] getData() throws IOException 
	{
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\shrinivas\\Data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

	// by using only @DataProvider
	/*
	 * @DataProvider() public Object[][] getData() { return new Object[][] { {
	 * "sb@gamail.com", "d!DAKFYAjzRNc9J", "ZARA COAT 3" }, {
	 * "yogibaba321@gmail.com", "Shrinivas@#123", "ADIDAS ORIGINAL" } }; }
	 */

	/*
	 * @DataProvider() public Object[][] getData() { HashMap<String, String> map =
	 * new HashMap<String, String>(); map.put("email", "sb@gamail.com");
	 * map.put("password", "d!DAKFYAjzRNc9J"); map.put("product", "ZARA COAT 3");
	 * 
	 * HashMap<String, String> map1 = new HashMap<String, String>();
	 * map1.put("email", "yogibaba321@gmail.com"); map1.put("password",
	 * "Shrinivas@#123"); map1.put("product", "ADIDAS ORIGINAL");
	 * 
	 * return new Object[][] { { map }, { map1 } }; }
	 */
}
