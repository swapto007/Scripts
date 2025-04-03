package SEENIT.Scripts.utils;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import SEENIT.Scripts.core.Home;
import SEENIT.Scripts.pageLayer.HomePage;
import SEENIT.Scripts.pageLayer.LoginPage;
import SEENIT.Scripts.pageLayer.MarginPage;
import java.util.HashMap;
import java.util.Map;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import java.time.Duration;

public class MasterClass extends ProjectFunctions
{
	@BeforeSuite
	public void preSuiteandler() throws Exception
	{
		navigateToUrl();
		pageInitilization(driver);
		ReportHelper.initializeExtentReport();		
		testResults = new HashMap<>();
		
	}
	
	@AfterMethod
	public void generateReport()
	{
		ReportHelper.tearDown();
		for (Map.Entry<String, String> entry : testResults.entrySet()) {
			System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
		}
		//driver.quit();
	}
}