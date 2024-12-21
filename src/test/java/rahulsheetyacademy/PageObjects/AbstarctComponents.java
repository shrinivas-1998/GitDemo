package rahulsheetyacademy.PageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstarctComponents {
	WebDriver driver;

	public AbstarctComponents(WebDriver driver) 
	{
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//button[@class = 'btn btn-custom'])[3]")
	WebElement CartHeader;

	// //button[@routerlink='/dashboard/myorders']
	@FindBy(xpath = ("//button[@routerlink='/dashboard/myorders']"))
	WebElement orderHeader;

	public void WaitForElementToAppear(By findBy) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	public void WaitForWebElementToAppear(WebElement findBy) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}

	public CartPage goToCartPage() 
	{
		CartHeader.click();
		CartPage cartpage = new CartPage(driver);
		return cartpage;
	}

	public OrderPage gotoOdersPage() 
	{
		orderHeader.click();
		OrderPage orderpage = new OrderPage(driver);
		return orderpage;
	}

	public void WaitForElementToDisappear(WebElement ele) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.invisibilityOf(ele));

	}
}
