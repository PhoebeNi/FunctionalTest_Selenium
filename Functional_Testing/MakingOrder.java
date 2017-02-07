package Functional_Testing;

import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class MakingOrder {
	private WebDriver driver;
	
	@Test
	public void Hotel_Order() {
	}
	
	@AfterMethod
	public void Back_To_Homepage() {
		driver.get(Util.BaseUrl);
	}

	@BeforeTest
	public void Browser_Setup() {
		System.setProperty("webdriver.chrome.driver", Util.ChromeDrider);
		driver = new ChromeDriver();
		driver.get(Util.BaseUrl);
		driver.manage().timeouts().implicitlyWait(Util.WaitTime, TimeUnit.SECONDS);
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/ul[2]/li[2]/a")).click();
		driver.manage().timeouts().implicitlyWait(Util.WaitTime, TimeUnit.SECONDS);
		driver.findElement(By.partialLinkText("Login")).click();
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys(Util.Valid_id);
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(Util.Valid_psw);
		driver.findElement(By.xpath("//*[@id=\"loginfrm\"]/div[4]/button")).click();		
	}

	@AfterTest
	public void Browser_Terminate() {
		driver.quit();
	}

}
