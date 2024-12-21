package headlessTesting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class HeadlessTesting1 
{
	public static void main(String[] args) 
	{
		// Set the path for your ChromeDriver (make sure to download the appropriate
		// version for your OS)
		// System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

		// Set up Chrome options for headless mode
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless"); // Enable headless mode
		options.addArguments("--disable-gpu"); // Disable GPU hardware acceleration
		options.addArguments("--no-sandbox"); // Disable sandbox (useful for CI environments)
		options.addArguments("window-size=1920x1080"); // Set window size (optional)

		// Initialize the ChromeDriver with headless options
		WebDriver driver = new ChromeDriver(options);

		// Perform test actions
		driver.get("https://www.cricbuzz.com/");
		//driver.get("https://www.manforcecondoms.com/");
		System.out.println("Title of the page is: " + driver.getTitle());

		// Close the browser
		driver.quit();
	}
}
