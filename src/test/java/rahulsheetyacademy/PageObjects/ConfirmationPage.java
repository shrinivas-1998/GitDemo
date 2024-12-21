package rahulsheetyacademy.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends AbstarctComponents 
{
	WebDriver driver;

	public ConfirmationPage(WebDriver driver) 
	{
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// driver.findElement(By.cssSelector(".hero-primary")
	@FindBy(css = ".hero-primary")
	WebElement ConfirmationMessage;

	public String getConfirmationMessage() 
	{
		return ConfirmationMessage.getText();
	}
}
