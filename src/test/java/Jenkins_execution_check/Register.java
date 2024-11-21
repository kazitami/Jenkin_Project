package Jenkins_execution_check;

import org.testng.annotations.*;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.openqa.selenium.By;


public class Register extends TestBase{
public Register() throws Exception {
		super();
	}
	
	@BeforeMethod
	public void setup() {
		
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();
		
	}
	
	@Test(priority=1)
	public void registerWithNoInputs() {
		
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Warning: You must agree to the Privacy Policy!')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'First Name must be between 1 and 32 characters!')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Last Name must be between 1 and 32 characters!')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'E-Mail Address does not appear to be valid!')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Telephone must be between 3 and 32 characters!')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Password must be between 4 and 20 characters!')]")).isDisplayed());
	
	}
	
	@Test(priority=2)
	public void registerWithMandatoryFields() {
		
		driver.findElement(By.id("input-firstname")).sendKeys("Tom");
		driver.findElement(By.id("input-lastname")).sendKeys("Cruise");
		driver.findElement(By.id("input-email")).sendKeys(emailStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("345633456");
		driver.findElement(By.id("input-password")).sendKeys("tom@123");
		driver.findElement(By.id("input-confirm")).sendKeys("tom@123");
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Your Account Has Been Created!']")).isDisplayed());
		
	}
	
	@Test(priority=3) 
	public void registerWithAllDetails() {
		
		driver.findElement(By.id("input-firstname")).sendKeys("Tom");
		driver.findElement(By.id("input-lastname")).sendKeys("Cruise");
		driver.findElement(By.id("input-email")).sendKeys(emailStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("345633456");
		driver.findElement(By.id("input-password")).sendKeys("tom@123");
		driver.findElement(By.id("input-confirm")).sendKeys("tom@123");
		driver.findElement(By.xpath("//input[@name='newsletter' and @value='1']")).click();
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Your Account Has Been Created!']")).isDisplayed());
		
	}
	
	@Test(priority=4)
	public void registerWithExistingEmail() {
		
		driver.findElement(By.id("input-firstname")).sendKeys("Tom");
		driver.findElement(By.id("input-lastname")).sendKeys("Cruise");
		driver.findElement(By.id("input-email")).sendKeys("seleniumpanda@gmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys("345633456");
		driver.findElement(By.id("input-password")).sendKeys("Selenium@123");
		driver.findElement(By.id("input-confirm")).sendKeys("Selenium@123");
		driver.findElement(By.xpath("//input[@name='newsletter' and @value='1']")).click();
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Warning: E-Mail Address is already registered!']")).isDisplayed());
		
	}
	
	@Test(priority=5)
	public void registerWithWrongConfirmPassword() {
		
		driver.findElement(By.id("input-firstname")).sendKeys("Tom");
		driver.findElement(By.id("input-lastname")).sendKeys("Cruise");
		driver.findElement(By.id("input-email")).sendKeys(emailStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("345633456");
		driver.findElement(By.id("input-password")).sendKeys("Selenium@123");
		driver.findElement(By.id("input-confirm")).sendKeys("tom@123456");
		driver.findElement(By.xpath("//input[@name='newsletter' and @value='1']")).click();
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Password confirmation does not match password!']")).isDisplayed());
	
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
