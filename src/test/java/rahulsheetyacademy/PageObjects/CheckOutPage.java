package rahulsheetyacademy.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckOutPage extends AbstarctComponents 
{
	WebDriver driver;

	public CheckOutPage(WebDriver driver) 
	{
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// PageFactory
	// driver.findElement(By.cssSelector("[placeholder = 'Select Country']"))
	@FindBy(css = "[placeholder = 'Select Country']")
	WebElement Country;

	// driver.findElement(By.xpath("//button[contains(@class, 'ta-item
	// list-group-item ng-star-inserted')][2]"))

	@FindBy(xpath = "//button[contains(@class, 'ta-item list-group-item ng-star-inserted')][2]")
	WebElement SelectCountryIndia;

	// driver.findElement(By.cssSelector(".action__submit"))
	@FindBy(css = ".action__submit")
	WebElement SubmitBtn;

	// By.cssSelector(".ta-item");
	By results = By.cssSelector(".ta-item");

	public void SelectCountry(String CountryName) throws InterruptedException 
	{
		Actions actions = new Actions(driver);
		actions.sendKeys(Country, CountryName).build().perform();
		Thread.sleep(5000);

		// Wait until the country dropdown appears
		WaitForElementToAppear(results);

		// Click on the "Place Order" button
		SelectCountryIndia.click();
	}

	public void submitOrder()
	{
		SubmitBtn.click();
	}
}
