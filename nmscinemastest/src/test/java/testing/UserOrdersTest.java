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

public class UserOrdersTest {
	 WebDriver driver;
	 @BeforeTest
		public void setUp() {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\lokesh\\Downloads\\chromedriver_win32\\chromeDriver.exe");
			driver =  new ChromeDriver(); 
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		    driver.manage().window().maximize();
			driver.get("http://localhost:4200/user/login");
		}
	
	 @Test
		public  void userOrders() throws InterruptedException {
			driver.findElement(By.name("username")).sendKeys("lokeshsunny@gmail.com");
			driver.findElement(By.name("password")).sendKeys("Sunny@2721");
			driver.findElement(By.id("UserLogin")).click();
			Thread.sleep(1000L);
			driver.get("http://localhost:4200/user-home");
		    WebElement Element=driver.findElement(By.name("userorders"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", Element);
			driver.findElement(By.name("userorders")).click();
			driver.findElement(By.name("vieworder")).click();
		Thread.sleep(1000L);

		}
	
	@AfterTest
	public void cleanUp() {
		driver.close();
	}

}