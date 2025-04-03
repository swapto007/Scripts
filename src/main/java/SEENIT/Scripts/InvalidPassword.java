package SEENIT.Scripts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class InvalidPassword {
	
	public static void main(String[] args) throws Exception {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver =new ChromeDriver();
		driver.get("https://app.humb.io");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		String trxHumbWalletPath= "TMzUwc8n5ExfSNZx7Nct4LLtpAvV1mRKcr";
		
		
		driver.findElement(By.xpath("//a[@href='/login']")).click();
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("mail2sramdasi@gmail.com");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Swap5007!@#");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		Thread.sleep(10000);
		
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		if (driver.findElement(By.xpath("//span[@class='text-danger f-12 d-block text-left mt-2']")).isDisplayed())
		{
			System.out.println("Messgae displayed ");
		}
		driver.findElement(By.xpath("//span[@class='text-danger f-12 d-block text-left mt-2']")).isDisplayed();

		Thread.sleep(20000);
		WebElement mainBalance= driver.findElement(By.xpath("//div[@class='wallet_balance_all']/child::p"));
		System.out.println(mainBalance.getText());	
		
		driver.navigate().to("https://app.humb.io/deposit");
//		WebElement deposit = driver.findElement(By.xpath("//button[text()='Deposit']"));
//		deposit.click();
//		Thread.sleep(5000);
//		driver.manage().timeouts().implicitlyWait(20, java.util.concurrent.TimeUnit.SECONDS);
////		WebElement withdraw = driver.findElement(By.xpath("//button[text()='Withdraw']"));
////		withdraw.click();
//		Thread.sleep(3000);
			
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		//WebElement dropDown = driver.findElement(By.xpath("//div[contains(@class, 'placeholder') and contains(text(), 'Select...')]"));
		WebElement dropDown = driver.findElement(By.xpath("//section/descendant::input"));
		
		dropDown.click();
		dropDown.sendKeys("TRX");
		
		Thread.sleep(5000);
		driver.manage().timeouts().implicitlyWait(10, java.util.concurrent.TimeUnit.SECONDS);
		Actions actions = new Actions(driver);
		actions.moveToElement(dropDown)
        .moveByOffset(0, 100) // Offset 100px below the element
        .click()  // Perform the click
        .build()
        .perform();
		
		
		
	}

}
