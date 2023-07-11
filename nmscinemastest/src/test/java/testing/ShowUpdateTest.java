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

public class ShowUpdateTest {
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
		public  void ShowUpdate() throws InterruptedException {
			driver.findElement(By.name("username")).sendKeys("lokeshaditya@gmail.com");
			driver.findElement(By.name("password")).sendKeys("Lokesh@2721");
			driver.findElement(By.id("loginButton")).click();
			Thread.sleep(1000L); 
			driver.get("http://localhost:4200/admin-dashboard");
			Thread.sleep(1000L);
			WebElement Element=driver.findElement(By.id("allmovies"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", Element);
			driver.findElement(By.id("allmovies")).click();
			Thread.sleep(1000L);
			driver.findElement(By.name("products"));
			driver.findElement(By.name("update")).click();
			
			WebElement Element1=driver.findElement(By.name("price"));
			
			Element1.clear();
			driver.findElement(By.name("price")).sendKeys("294");
			Thread.sleep(1000L);
			driver.findElement(By.name("update")).click();
			driver.findElement(By.name("close")).click();
			Thread.sleep(1000L);
			driver.get("http://localhost:4200/admin/get/all/movies");
			assertEquals(driver.getCurrentUrl(), "http://localhost:4200/admin/get/all/movies");
		

		}
	
	@AfterTest
	public void cleanUp() {
		driver.close();
	}

}