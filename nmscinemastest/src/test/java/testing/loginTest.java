package testing;


import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class loginTest {
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
		public  void loginadminr() throws InterruptedException {
			driver.findElement(By.name("username")).sendKeys("lokeshaditya@gmail.com");
			driver.findElement(By.name("password")).sendKeys("Lokesh@2721");
			driver.findElement(By.id("loginButton")).click();
			Thread.sleep(1000L);
			driver.findElement(By.name("home")).click();
			Thread.sleep(500L);
			driver.get("http://localhost:4200/admin-dashboard");
			assertEquals(driver.getCurrentUrl(), "http://localhost:4200/admin-dashboard");

		}
	
	@AfterTest
	public void cleanUp() {
		driver.close();
	}

}