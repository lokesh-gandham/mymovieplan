package testing;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RegistrationTest {
	 WebDriver driver;
	 @BeforeTest
		public void setUp() {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\lokesh\\Downloads\\chromedriver_win32\\chromeDriver.exe");

			driver =  new ChromeDriver(); 
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		    driver.manage().window().maximize();
			driver.get("http://localhost:4200/user/signup");
		}
	
	 @Test
		public  void Usersignup() throws InterruptedException {
			driver.findElement(By.name("username")).sendKeys("lokeshsunny@gmail.com");
			driver.findElement(By.name("password")).sendKeys("Sunny@2721");
			driver.findElement(By.name("firstName")).sendKeys("lokesh");
			driver.findElement(By.name("lastName")).sendKeys("sunny");
			driver.findElement(By.name("phone")).sendKeys("9133723123");
			Thread.sleep(2000L);
			Thread.sleep(2000L);
			WebElement Element=driver.findElement(By.cssSelector("button[id='signUpButton'"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", Element);
			driver.findElement(By.cssSelector("button[id='signUpButton'")).click();
			driver.findElement(By.id("close")).click();
			driver.get("http://localhost:4200/user/login");
			assertEquals(driver.getCurrentUrl(), "http://localhost:4200/user/login");

		}
	
	@AfterTest
	public void cleanUp() {
		driver.close();
	}

}