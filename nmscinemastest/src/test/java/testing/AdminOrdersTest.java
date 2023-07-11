package testing;






import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AdminOrdersTest {
	 WebDriver driver;
	 @BeforeTest
		public void setUp() {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\lokesh\\Downloads\\chromedriver_win32\\chromeDriver.exe");
			driver =  new ChromeDriver(); 
		
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		    driver.manage().window().maximize();
			driver.get("http://localhost:4200/admin/login");
		}
	
	 @Test
		public  void Adminorders() throws InterruptedException {
			driver.findElement(By.name("username")).sendKeys("lokeshaditya@gmail.com");
			driver.findElement(By.name("password")).sendKeys("Lokesh@2721");
			driver.findElement(By.id("loginButton")).click();
			WebElement Element1=driver.findElement(By.name("home"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", Element1);
			driver.findElement(By.name("home")).click();
		  
			//driver.findElement(By.name("home")).click();
			WebElement Element=driver.findElement(By.name("allorders"));
			
			js.executeScript("arguments[0].scrollIntoView();", Element);
			driver.findElement(By.name("allorders")).click();
			Thread.sleep(1000L);
			driver.findElement(By.name("orders"));
			driver.findElement(By.name("details")).click();
			Thread.sleep(1000L);
		
		Thread.sleep(1000L);

		}
	
	@AfterTest
	public void cleanUp() {
		driver.close();
	}

}