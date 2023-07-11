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

public class userOperationsTest {
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
		public  void useroperations() throws InterruptedException {
			driver.findElement(By.name("username")).sendKeys("lokeshsunny@gmail.com");
			driver.findElement(By.name("password")).sendKeys("Sunny@2721");
			driver.findElement(By.id("UserLogin")).click();
			Thread.sleep(1000L);
			driver.get("http://localhost:4200/user-home");
		    driver.findElement(By.name("allmovies")).click();
		    driver.findElement(By.name("movies"));
		    driver.findElement(By.id("bookticket")).click();
		    driver.findElement(By.name("cart")).click();
		    driver.findElement(By.id("increase")).click();
		    Thread.sleep(1000L);
		    driver.findElement(By.name("pay")).click();

		   // entering userdetails with shipping address
		    driver.findElement(By.name("firstName")).sendKeys("lokesh");
		   driver.findElement(By.name("lastName")).sendKeys("sunny");
		    driver.findElement(By.name("address")).sendKeys("18-4-23/1,enginebowli,falaknuma");
		    driver.findElement(By.name("pinCode")).sendKeys("500053");
		    driver.findElement(By.name("district")).sendKeys("hyderabad");
		    WebElement Element=driver.findElement(By.name("state"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", Element);
			driver.findElement(By.name("state")).sendKeys("telangana");
			
			 WebElement Element1=driver.findElement(By.name("contact"));
				js.executeScript("arguments[0].scrollIntoView();", Element1);
				driver.findElement(By.name("contact")).sendKeys("9133723123");
				
				WebElement Element2=driver.findElement(By.name("paynow"));
				js.executeScript("arguments[0].scrollIntoView();", Element2);
				driver.findElement(By.name("paynow")).click();
			
		   Thread.sleep(1000L);
		   driver.findElement(By.name("cname")).sendKeys("lokeshsunny");
		   driver.findElement(By.name("cnumber")).sendKeys("9247538793148");
		   driver.findElement(By.name("cvv")).sendKeys("9289123421340987");
		   driver.findElement(By.name("remember")).click();
		   driver.findElement(By.id("lastpay")).click();
		String url=driver.getCurrentUrl();
		System.out.print(url);
	Thread.sleep(2000L);
		
		 driver.findElement(By.name("home")).click();
		   Thread.sleep(1000L);
		   
		   WebElement Element4=driver.findElement(By.name("userorders"));
			js.executeScript("arguments[0].scrollIntoView();", Element4);
			driver.findElement(By.name("userorders")).click();
		   Thread.sleep(2000L);
		   
		   driver.get("http://localhost:4200/user/get/all-orders/lokeshsunny@gmail.com");
		   Thread.sleep(1000L);
		  // driver.switchTo().alert().accept();
		 assertEquals(driver.getCurrentUrl(), "http://localhost:4200/user/get/all-orders/lokeshsunny@gmail.com");
		}
	
	@AfterTest
	public void cleanUp() {
		driver.close();
	}

}