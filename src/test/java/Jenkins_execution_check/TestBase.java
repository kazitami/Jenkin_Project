package Jenkins_execution_check;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestBase {

	public WebDriver driver;
	public Properties prop;
	public FileInputStream ip;
	
	public TestBase() throws Exception {
		prop = new Properties();
		ip = new FileInputStream(System.getProperty("user.dir")+"/src/test/java/Jenkins_execution_check/config.properties");
		prop.load(ip);
	
	}
	
	public WebDriver initializeBrowserAndOpenApplication(String browserName) {
		
		if(browserName.equals("Chrome")) {
			driver = new ChromeDriver();
		}else if(browserName.equals("Firefox"))
		{
			driver = new FirefoxDriver();
		}else if(browserName.equals("Edge")) {
			driver = new EdgeDriver();	
		}else 
		{
			System.out.println("None of the browser matches");
		}
			
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(400));
		
		driver.get(prop.getProperty("url"));
		
		
		return driver;
	}
	
	public static String emailStamp() {
		Date date = new Date();
		String email = date.toString().replace(" ", "_").replace(":", "_");
		return "seleniumpanda" + email + "@gmail.com";
	}
	
}
