package SEENIT.Scripts.core;

import org.apache.commons.compress.utils.TimeUtils;
import org.apache.xmlbeans.impl.tool.XSTCTester.TestCaseResult;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import SEENIT.Scripts.pageLayer.LoginPage;
import SEENIT.Scripts.utils.GenericHelper;
import SEENIT.Scripts.utils.MasterClass;
import SEENIT.Scripts.utils.ReportHelper;
import SEENIT.Scripts.utils.clsEmailUtils;
import ch.qos.logback.core.util.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
public class Login extends MasterClass{

	public WebDriverWait wait =null; 

	public void loginToSystem() throws Exception
	{	    
		try {
			
			ReportHelper.createTestCase("Login Page Displayed", "Verify that LoginPage is displayed or not.");	
			Thread.sleep(10000);
			homepage.loginButton().click();
			Thread.sleep(2000);

			/// Verify that "Welcome to Humb" text on login page is displayed or not
			if (!(driver.findElement(By.xpath("//h3[@class='inr_title']")).getText().trim().length() > 0)) {
				currentConnectionStatus=  driver.findElement(By.xpath("//div[@class='connection_status connected']/child::p")).getText();
				ReportHelper.onTestFailure("Login Page is not avaliable");    
				testResults.put("Login Page Displayed", "Fail");
				System.out.println("MAP Entry added LOGIN 35");
				currentConnectionStatus = driver.findElement(By.xpath("//div[@class='connection_status connected']/child::p")).getText();

				hitReport("LoginPage is not displayed to the user |" , "Very High", currentReportPath, currentConnectionStatus);
				Assert.assertTrue(false);
				driver.quit();
			} 
			else
			{
				ReportHelper.onTestPass("Login Page Displayed");
				testResults.put("Login Page Displayed", "Pass"); 
			}				
			Thread.sleep(5000);
			ReportHelper.getScreenShot1("User on the LoginPage");


		} catch (Exception e) {
			// TODO: handle exception
			currentConnectionStatus=  driver.findElement(By.xpath("//div[@class='connection_status connected']/child::p")).getText();
			hitReport("login Page is not avaliable", "Very High", currentReportPath,currentConnectionStatus);
			Assert.assertTrue(false);
		}

		try {
//			loginPage.emailAddress().sendKeys("kardesonalk@gmail.com");
//			loginPage.password().sendKeys("GloryVV@1");
			loginPage.emailAddress().sendKeys("mail2sramdasi@gmail.com");
			loginPage.password().sendKeys("Swap5007!@#");
			loginPage.submitButton().click();
			ReportHelper.createTestCase("OTP Window Displayed", "Verify that OTP popup is displayed or not.");
			wait = new WebDriverWait(driver, 50);

			/// Verify that model is popup model for OTP is displayed is or not
			
			WebElement otpModelPopup =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-content']")));
			if (!otpModelPopup.isDisplayed())
			{
				currentConnectionStatus=  driver.findElement(By.xpath("//div[@class='connection_status connected']/child::p")).getText();
				ReportHelper.onTestFailure("OTP Page is not avaliable");
				testResults.put("OTP Popup Displayed", "Fail");
				System.out.println("MAP Entry added LOGIN 73");
				ReportHelper.getScreenShot1("OTP Page is not avaliable");
				Thread.sleep(5000);
				hitReport("OTP Page is not avaliable", "Veri High", currentReportPath,currentConnectionStatus);
				Assert.assertTrue(false);
			}
			else
			{
				ReportHelper.getScreenShot1("OTP Page is displayed to the user");
				Thread.sleep(2000);
				ReportHelper.onTestPass("OTP Page is displayed to the user");
				testResults.put("OTP Popup Displayed", "Pass");
				System.out.println("MAP Entry added LOGIN 85");

			}
		} 
		catch (Exception e) 
		{
			currentConnectionStatus=  driver.findElement(By.xpath("//div[@class='connection_status connected']/child::p")).getText();
			hitReport("Failed to login in the System", "Very High", currentReportPath,currentConnectionStatus);
			driver.quit();
		}

		Thread.sleep(5000);
		ReportHelper.createTestCase("OTP received via E-mail", "Verify that OTP mail is received to the user or not");
		sendPassword();
		//GenericHelper.getScreenshotWithTimestamp(driver);
		Thread.sleep(1000);


	}

	public void sendPassword() throws Exception
	{
		try 
		{
			clsEmailUtils clsEmailUtils = new clsEmailUtils();
			//String otp = clsEmailUtils.getOtpFromGmail("mail2sramdasi@gmail.com", "yroj khpf xsxt umaw");
			String otp = clsEmailUtils.getOtpFromGmail("mail2sramdasi@gmail.com", "yroj khpf xsxt umaw");
			System.out.println(otp);
			int Len = otp.length();
			int[] otpArray = new int[otp.length()];
			for (int i = 0; i < otp.length(); i++)
			{
				otpArray[i] = Character.getNumericValue(otp.charAt(i));   	
				System.out.println(otpArray[i]);
				driver.findElement(By.xpath("//input[@aria-label='Please enter OTP character 6']")).sendKeys(String.valueOf(otpArray[i]));
				Thread.sleep(1000);
			}
			
			ReportHelper.createTestCase("Valid Password", "Verify that Password is correct or not");
			
			driver.findElement(By.xpath("//button[@class='grad_btn2 grad_btn']")).click();
						
			WebDriverWait wait = new WebDriverWait(driver, 30);
			WebElement toastifyMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='go3958317564']")));
			toastifyMessageForLogin =  toastifyMessageElement.getText();
						
			if (toastifyMessageForLogin != null && !toastifyMessageForLogin.isEmpty() && !toastifyMessageForLogin.isBlank()) 
			{
				if (toastifyMessageForLogin.equalsIgnoreCase("Login successful"))
				{
					ReportHelper.onTestPass("Login SuccessFull !!!");
					testResults.put("Login SuccessFull with Valid Password", "Pass");
					System.out.println("MAP Entry added LOGIN 129");
				}
				
				System.out.println("Toastify message for login displayed...");
			} 
			else 
			{
				if (driver.findElement(By.xpath("//span[@class='text-danger f-12 d-block text-left mt-2']")).isDisplayed())
				{
					currentConnectionStatus=  driver.findElement(By.xpath("//div[@class='connection_status connected']/child::p")).getText();
					ReportHelper.onTestFailure("Invalid Password Can not login ");
					testResults.put("Login SuccessFull", "Fail");
					hitReport("Login Failed on OTP Window invalid Password", "Very High",currentReportPath,currentConnectionStatus);
					GenericHelper.logger.info("Login Failed from OTP Window Invalid Password");
					Assert.assertTrue(false);
					driver.quit();			    

				}

			}
		}
		catch (Exception e) 
		{
			//currentConnectionStatus=  driver.findElement(By.xpath("//div[@class='connection_status connected']/child::p")).getText();
			//ReportHelper.onTestFailure("Test Case For login Failed ");
			//testResults.put("Login SuccessFull", "Fail");
			//hitReport("Login Failed On OPT Window", "Very High",currentReportPath,currentConnectionStatus);
			Assert.assertTrue(false);
			driver.quit();
		}

	}
}
