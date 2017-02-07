package Functional_Testing;

import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;

public class Login_Validation {
	private WebDriver driver;

	@Test(dataProvider = "LoginInfo")
	public void Login(String id, String psw) {
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys(id);
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(psw);
		driver.findElement(By.xpath("//*[@id=\"loginfrm\"]/div[4]/button")).click();		
		try {
			driver.findElement(By.xpath("//*[@id=\"loginfrm\"]/div[1]/div"));
			System.out.println("Test Passed!");
		}
		catch (NoSuchElementException element){
			String ActualTitle = driver.getTitle();
			System.out.println(ActualTitle);
			if (Util.ExpectedTitlte.equals(ActualTitle)){
				System.out.println("Test Passed!");
			}
			else {
				System.out.println("Test Failed!");
			}

		}
	}

	@BeforeMethod
	public void Browser_Setup() {
		System.setProperty("webdriver.chrome.driver", Util.ChromeDrider);
		driver = new ChromeDriver();
		driver.get(Util.BaseUrl);
		driver.manage().timeouts().implicitlyWait(Util.WaitTime, TimeUnit.SECONDS);
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/ul[2]/li[2]/a")).click();
		driver.manage().timeouts().implicitlyWait(Util.WaitTime, TimeUnit.SECONDS);
		driver.findElement(By.partialLinkText("Login")).click();
	}

	@AfterMethod
	public void Browser_Terminate() {
		driver.quit();
	}


	@DataProvider
	public Object[][] LoginInfo() {
		return new Object[][] {
			new Object[] { Util.Valid_id, Util.Valid_psw },
			new Object[] { Util.Valid_id, Util.Invalid_psw },
			new Object[] { Util.Invalid_id, Util.Valid_psw },
			new Object[] { Util.Invalid_id, Util.Invalid_psw }
		};
	}
}
