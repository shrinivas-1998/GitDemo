package shrinivas.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulsheetyacademy.PageObjects.LandingPage;

public class BaseTest 
{
	public WebDriver driver;

	public LandingPage landingpage;

	public WebDriver InitializeDriver() throws IOException 
	{
		// Properties class
		Properties property = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//shrinivas//Resoruces//GlobalData.properties");
		property.load(fis);
		
		// This line is to take the global parameter given in mvn commands 
		String BrowserName = System.getProperty("browser")!= null ? System.getProperty("browser") : property.getProperty("browser");
		// String BrowserName = property.getProperty("browser").toLowerCase();
		//String BrowserName = property.getProperty("browser");
		
		// Assuming BrowserName is a String variable
		if (BrowserName.equalsIgnoreCase("chrome")) 
		{
		    //ChromeOptions options = new ChromeOptions();
		    WebDriverManager.chromedriver().setup();
		    WebDriver driver = new ChromeDriver();
		   // driver.manage().window().maximize();
		   //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		   // Your additional code here (e.g., navigation, actions, etc.)
		}
		
		// for HeadLess Testing
		if (BrowserName.contains("chrome")) 
		{
		    ChromeOptions options = new ChromeOptions();
		    WebDriverManager.chromedriver().setup();
		    options.addArguments("--headless"); // Added '--' prefix to headless argument
		    if(BrowserName.contains("headless"))
		    {
		    	 WebDriver driver = new ChromeDriver(options);
		    }
		    
		    // To maximize the browser by customized way 
		    driver.manage().window().setSize(new Dimension(1440, 900));
		   // driver.manage().window().maximize();
		   //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		    // Your additional code here (e.g., navigation, actions, etc.)
		}
		
		else if (BrowserName.equals("firefox")) 
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			// driver.manage().window().maximize();
			// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		} 
		else if (BrowserName.equals("edge")) 
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			// driver.manage().window().maximize();
			// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException 
	{
		// read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// String to HashMap- Jackson Databind

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {});
		return data;

		// {map, map}
	}

	public String getScreenShot(String testName, WebDriver driver) throws IOException 
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "//reports//" + testName + ".png");
		Files.copy(source, target);
		return System.getProperty("user.dir") + "//reports//" + testName + ".png";
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage LaunchApplication() throws IOException 
	{
		driver = InitializeDriver();
		landingpage = new LandingPage(driver);
		landingpage.Goto();
		return landingpage;
	}

	@AfterMethod(alwaysRun = true)
	public void TearDown() throws InterruptedException 
	{
		// Close the browser
		Thread.sleep(5000);
		driver.quit();
	}
}


