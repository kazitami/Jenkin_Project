package Jenkins_execution_check;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.*;

public class SearchProduct extends TestBase {

	public SearchProduct() throws Exception {
		super();
	}

	@BeforeMethod
	public void registerassertions() {
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
	}

	@Test(priority=1, retryAnalyzer=MyRetry.class)
	public void verifySearchWithValidProduct() {
		driver.findElement(By.name("search")).sendKeys("HP");
		driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='HP LP3065']")).isDisplayed());
	}

	@Test(priority = 2)
	public void verifySearchWithInvalidProduct() {

		driver.findElement(By.name("search")).sendKeys("Dell");
		driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[text()='There is no product that matches the search criteria.']"))
						.isDisplayed());
	}

	@Test(priority = 3)
	public void verifySearchWithNoProduct() {

		driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[text()='There is no product that matches the search criteria.']"))
						.isDisplayed());

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
