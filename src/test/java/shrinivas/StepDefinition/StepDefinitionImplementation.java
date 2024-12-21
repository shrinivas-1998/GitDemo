package shrinivas.StepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulsheetyacademy.PageObjects.CartPage;
import rahulsheetyacademy.PageObjects.CheckOutPage;
import rahulsheetyacademy.PageObjects.ConfirmationPage;
import rahulsheetyacademy.PageObjects.LandingPage;
import rahulsheetyacademy.PageObjects.ProductCatlogue;
import shrinivas.TestComponents.BaseTest;

public class StepDefinitionImplementation extends BaseTest 
{
	public LandingPage landingPage;
	public ProductCatlogue productcatlogue;

	@Given("i landed on Ecommerce Page")
	public void I_landed_On_Ecommerce_Page() throws IOException 
	{
		landingPage = LaunchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username, String password) throws InterruptedException 
	{
		productcatlogue = landingpage.LoginApplication(username, password);
		Thread.sleep(10000);
	}

	@When("^I add product (.+) to cart$")
	public void I_add_product_to_cart(String productName) 
	{// Getting list of products available on the site
		List<WebElement> products = productcatlogue.getProductList();
		productcatlogue.addProductToCart(productName);
	}

	// @When("^Checkout (.+) and submit the order$")
	@And("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productName) throws InterruptedException 
	{
		productcatlogue.goToCartPage();
		CartPage cartpage = new CartPage(driver);
		Boolean match = cartpage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);

		// Click on the "Checkout" button
		cartpage.goToCheckout();
		Thread.sleep(10000);
		CheckOutPage checkoutpage = new CheckOutPage(driver);
		checkoutpage.SelectCountry("india");
		checkoutpage.submitOrder();
	}

	@Then("{string} message is displayed on ConfirmationPage")
	public void Message_Is_Displayed_On_ConfirmationPage(String string) throws InterruptedException 
	{
		ConfirmationPage confirmationpage = new ConfirmationPage(driver);
		String confirmationMessage = confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase(string));
		Thread.sleep(5000);
		driver.quit();
	}
	
	@Then("{string} message is displayed")
    public void Message_Is_Displayed(String errorMessage) throws InterruptedException 
	{
        Assert.assertEquals(errorMessage, landingPage.getErrorMessage());
        Thread.sleep(5000);
        driver.quit();
    }
}
