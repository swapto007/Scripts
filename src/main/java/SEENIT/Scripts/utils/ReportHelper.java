package SEENIT.Scripts.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.text.html.HTML; 


public class ReportHelper extends  MasterClass
{
	private static ExtentReports extent;
	private static ExtentTest test;
	private static ExtentHtmlReporter htmlReporter;

	// Set up ExtentReports
	public static void initializeExtentReport() {
		
		String dateTime = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss-SSS").format(new Date()); 
		String reportPath = System.getProperty("user.dir") + "/ExtentReport/" + projectName + "_" + dateTime + "_Report.html";
		currentReportPath= reportPath;
		//String reportPath = System.getProperty("user.dir") + "/ExtentReport/ExtentReport.html";		
		htmlReporter = new ExtentHtmlReporter(reportPath);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		// Set system information (optional)
		extent.setSystemInfo("OS", "Windows");
		extent.setSystemInfo("Browser", "Chrome");
		
		htmlReporter.config().setDocumentTitle("Report For "+projectName);
		htmlReporter.config().setReportName("Regression Suite");
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd yyyy, hh:mm a '('zzz')'");
		
	}

	// Start a new test
	public static void createTestCase(String testName, String testDescription) {
		String testDescriptionWithCss = "<div style='background-color: #0d0f68; padding: 10px; border-radius: 5px;'>"
                + testDescription
                + "</div>";
		
		test = extent.createTest(testName, testDescriptionWithCss);
	}

	// Log the test steps
	public static void generateLog(String message) {
		//test.info(message);
		test.log(Status.INFO, MarkupHelper.createLabel(message, ExtentColor.ORANGE));
	}

	// Log failures with screenshots
	public static void onTestFailure(String message) {
		test.fail(" ");
		test.log(Status.FAIL, MarkupHelper.createLabel("Test Case Failed "+message, ExtentColor.RED));
	}
	
	// This is going to be after Method  
	public static void tearDown() {
        extent.flush();
    }
	
	public static void onTestPass(String msg) 
	{
        test.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed "+msg, ExtentColor.GREEN));
    }
	
//	public static void getScreenShot1(String messageWithScreenShot) throws Exception
//	{
//		test.log(Status.INFO, MarkupHelper.createLabel(messageWithScreenShot, ExtentColor.ORANGE));
//		String ss_Path= GenericHelper.getScreenshotWithTimestamp(driver);
//		System.out.println(ss_Path);
//		test.addScreenCaptureFromPath(ss_Path);
//		//test.log(Status.INFO, MarkupHelper.createLabel(messageWithScreenShot, ExtentColor.ORANGE));
//		//test.addScreencastFromPath(ss_Path);
//	}
	
	public static void getScreenShot1(String messageWithScreenShot) throws Exception
	{
		test.log(Status.INFO, MarkupHelper.createLabel(messageWithScreenShot, ExtentColor.ORANGE));
		String ss_Path= GenericHelper.getScreenshotWithTimestamp(driver);
		test.log(Status.INFO, MarkupHelper.createLabel(ss_Path, ExtentColor.BLUE));
		String imageHtml = "<img src='" + ss_Path + "' alt='screenshot' height='200' width='350' />";
		test.info(messageWithScreenShot+"<br>"+imageHtml, MediaEntityBuilder.createScreenCaptureFromPath(ss_Path).build());
		//test.addScreencastFromPath(ss_Path);
		Thread.sleep(3000);		
	}
	
}
