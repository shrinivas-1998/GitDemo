package rahulsheetyacademy.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstarctComponents 
{
	WebDriver driver;

	public LandingPage(WebDriver driver) 
	{
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// WebElement UserEmails = driver.findElement(By.id("userEmail"));
	// PageFactory
	@FindBy(id = "userEmail")
	WebElement UserEmails;

	@FindBy(id = "userPassword")
	WebElement PasswordTextfield;

	@FindBy(id = "login")
	WebElement Submit;
	
	//.ng-tns-c4-8.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
	
	@FindBy(xpath = "//div[@aria-label='Incorrect email or password.']")
	WebElement errorMessage;

	// Actions on Login page
	public ProductCatlogue LoginApplication(String Username, String Password) 
	{
		UserEmails.sendKeys(Username); 
		PasswordTextfield.sendKeys(Password);
		Submit.click();
		ProductCatlogue productcatlogue = new ProductCatlogue(driver);
		return productcatlogue;
	}
	
	public String getErrorMessage()
	{
		WaitForWebElementToAppear(errorMessage);
		String message = errorMessage.getText();
		return message;
		
	}

	// Luanching website "https://rahulshettyacademy.com/client"
	public void Goto() 
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	
}
