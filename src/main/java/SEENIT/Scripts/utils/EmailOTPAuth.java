package SEENIT.Scripts.utils;
import javax.mail.*;
import javax.mail.search.FlagTerm;
import java.util.Properties;


public class EmailOTPAuth {
	
//	
//	public static void main(String[] args) throws Exception
//	{
//		
////		System.setProperty("webdriver.chrome.driver", "C:\\BrowserDrivers\\chromedriver.exe");	
////		ChromeDriver driver = new ChromeDriver();
////		driver.manage().window().maximize();
////		WebDriverWait wait = new WebDriverWait(driver, 20);
////		
////		
////		
////				 
////		driver.get("https://app.Humb.io/login");
////	//	WebDriverWait wait1 = new WebDriverWait(driver, 30)
////		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("kardesonalk@gmail.com");
////		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("GloryVV@1");
////		driver.findElement(By.xpath("//button[@type='submit']")).click();
////		System.out.println("Karde");
//		       
//       Thread.sleep(50000);
//       
//    // Connect to Gmail and get OTP
//       
//        clsEmailUtils clsEmailUtils = new clsEmailUtils();
//		String otp = clsEmailUtils.getOtpFromGmail("kardesonalk@gmail.com", "syir zoyk cceg jwvr");
//		System.out.println(otp);
//		int Len = otp.length();
//		int[] otpArray = new int[otp.length()];
//        for (int i = 0; i < otp.length(); i++)
//        {
//            otpArray[i] = Character.getNumericValue(otp.charAt(i));   	
//            System.out.println(otpArray[i]);
//            driver.findElement(By.xpath("//input[@aria-label='Please enter OTP character 6']")).sendKeys(String.valueOf(otpArray[i]));
//        }               
//        
//        driver.findElement(By.xpath("//button[@class='grad_btn2 grad_btn']")).click();
//        
//        Thread.sleep(20000);
//        
//        // Code to check if User is directed to Wallet page and success message is displayed
//        
//        
//      //   Get the title of the webpage and store in a variable
//
//    	String actualTitle = driver.getCurrentUrl();
//
//	//	Type in the expected title
//    	
//    	System.out.println(actualTitle);
//
//   		String expectedTitle = "https://app.humb.io/wallet";
//
//	//	Verify if both of them are equal
//
//   		if(actualTitle.equalsIgnoreCase(expectedTitle))
//   		{
//
//   			System.out.println("Title Matched");
//   		
//   		
//   	  // Click on Deposit button
//        WebElement depositButton = driver.findElement(By.xpath("//button[contains(text(),'Deposit')]"));
//        depositButton.click();
//        
//        //button[contains(text(),'Withdraw')]
//   		}
//
//  		else
//  		{
//
//  			System.out.println("Title didn't match");
//  			
//  			//Send mail mentioning error
//  			
//  			
//  		}
//
//		//	Alternatively,
//
//   	//	Assert.assertEquals(actualTitle, expectedTitle);
//   		
//   		*/
//   		
//   		
//    //   clsDepositfromWallet clsdeposit = new clsDepositfromWallet();
//  //    clsdeposit.DeposittoMetamaskWallet();
//     //   System.out.println(strSuccess);
//		
//		clsReport clsreport = new clsReport();
//		clsreport.verify();
//		
//		//Open Report
//		clsOpenExtendReport clsopenextendreport = new clsOpenExtendReport();
//		clsopenextendreport.OpenReport();
//   
//	//driver.close();  
//	}
//	
	}