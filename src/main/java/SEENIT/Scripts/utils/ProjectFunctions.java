package SEENIT.Scripts.utils;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.ArrayList;
import java.util.Set;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import SEENIT.Scripts.core.DashBoard;
import SEENIT.Scripts.core.Home;
import SEENIT.Scripts.core.Login;
import SEENIT.Scripts.core.Margin;
import SEENIT.Scripts.pageLayer.DashBoardPage;
import SEENIT.Scripts.pageLayer.HomePage;
import SEENIT.Scripts.pageLayer.LoginPage;
import SEENIT.Scripts.pageLayer.MarginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;


public class ProjectFunctions {

	public static final String screenShotFolderPath= "./ExtentReport/ScreenShots";
	public static final String screenShotFolderPath1= "./ExtentReport/ScreenShots";
	public static final String projectName= " Humb.io ";
	public static final String qaName= "Test Engineer Swapnil";
	public static String toastifyMessageForLogin=null;
	public static String errorMessage=null;
	public static String severity =null;
	public static String currentReportPath=null;
	public static String currentConnectionStatus=null;
	public static Map<String, String> testResults = null;
	public static String trxHumbWalletPathForDepostit= "TMzUwc8n5ExfSNZx7Nct4LLtpAvV1mRKcr";

	/////// POM Pages

	public static WebDriver driver= null;

	public static HomePage homepage;
	public static LoginPage loginPage;
	public static MarginPage marginPage;
	public static DashBoardPage dashBoardPage;


	///// Core Pages
	public static Home home;
	public static Login login;
	public static Margin margin;
	public static DashBoard dashBoard;

	void pageInitilization(WebDriver driver)
	{
		////POM Pages 
		homepage = new HomePage(driver);
		loginPage = new LoginPage();
		dashBoardPage = new DashBoardPage();
		marginPage = new MarginPage();


		///Core Pages

		home= new Home();
		login= new Login();
		dashBoard = new DashBoard();
		margin= new Margin();

	}	

	void navigateToUrl() throws Exception
	{
		//// OLD CODE 
		 ChromeOptions options = new ChromeOptions();
     		options.addArguments("--headless");
        	options.addArguments("--disable-gpu");
        	options.addArguments("--no-sandbox");
		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver(options);
		try {

			driver.get("https://app.humb.io");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			String MainPagetitle = driver.getTitle();
			currentConnectionStatus=  driver.findElement(By.xpath("//div[@class='connection_status connected']/child::p")).getText();

			if(MainPagetitle.isBlank())
			{
				currentConnectionStatus=  driver.findElement(By.xpath("//div[@class='connection_status connected']/child::p")).getText();
				ReportHelper.onTestFailure("Humb.io Langing page is not working");
				hitReport("Humb.io Langing page is not working", "Very High", currentReportPath,currentConnectionStatus);				
				driver.quit();
			}

		} catch (Exception e) {

			currentConnectionStatus=  driver.findElement(By.xpath("//div[@class='connection_status connected']/child::p")).getText();
			ReportHelper.onTestFailure("Humb.io Langing page is not working");
			hitReport("Humb.io Langing page is not working", "Very High", currentReportPath,currentConnectionStatus);
			driver.quit();
		}
	}
	public static void hitReport(String errorMessage,String Severity,String extentReportPath,String connectionStatus)
	{
		/// Code For Email Shoot
		ReportHelper.tearDown();
		for (Map.Entry<String, String> entry : testResults.entrySet()) {
			System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
		} 
		// Mail extend report
		//			String toEmail = "sonal@seenit.co";
		//			String subject = "Extent Report";
		//			String body = "Hi,\n\nPlease find the attached Extent Report.\n\nBest regards,\nYour Automation Team";
		//			String filePath = "extent-report.html"; // Absolute path to your Extent Report

		//clsSendExtendReportviaEmail clssendreport = new clsSendExtendReportviaEmail();

		StringBuilder emailBody = new StringBuilder();
		emailBody.append("<html>")
		.append("<body>")
		//.append("Connection Type: "+connectionStatus)
		.append("<p><b>Connection Type: ").append(connectionStatus).append("</b></p>")
		.append("<h3>Daily Test Results:</h3>")
		.append("<table border='1' style='border-collapse: collapse; width: 50%; text-align: left;'>")
		.append("<tr>")
		.append("<th style='background-color: #000000; color: #FFFFFF;'>Test Name</th>") // Corrected
		.append("<th style='background-color: #000000; color: #FFFFFF;'>Result</th>") 
		.append("</tr>");


		for (Map.Entry<String, String> entry : testResults.entrySet()) 
		{
			String backgroundColor = entry.getValue().equals("Pass") ? "#aaec14" : "#F32F21"; // Green for Pass, Red for Fail
			String textColor = entry.getValue().equals("Pass") ? "black" : "white"; // Black for Pass, White for Fail
			emailBody.append("<tr>")
			.append("<td>").append(entry.getKey()).append("</td>")
			.append("<td style='background-color: ").append(backgroundColor)
			.append("; color: ").append(textColor).append(";'>")
			.append(entry.getValue())
			.append("</td>")
			.append("</tr>");
		}
		emailBody.append("</table>")
		.append("</body>")
		.append("</html>");


		
		//manoj@seenit.co
		clsSendExtendReportviaEmail.sendEmailWithAttachment("swapnil@humb.io,sonal@seenit.co", " Error Alert:- | "+Severity+" Severity | "+errorMessage, emailBody.toString(), currentReportPath);
		driver.quit();
	}



	public void navigateToSystem() throws Exception
	{	
		navigateToUrl();		
	} 

}
