package SEENIT.Scripts.core;

import java.util.concurrent.TimeUnit;

import SEENIT.Scripts.pageLayer.DashBoardPage;
import SEENIT.Scripts.utils.GenericHelper;
import SEENIT.Scripts.utils.MasterClass;
import SEENIT.Scripts.utils.ReportHelper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashBoard extends MasterClass{
	
	public void goToMarginPage() throws Exception
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // Wait for the page's readyState to be 'complete'		
		if(dashBoardPage.trade().isDisplayed())
		{	
			
			Thread.sleep(2000);
			GenericHelper.logger.info("Logged in to System");
			ReportHelper.onTestPass("DashboardPage is displayed to the user");
			testResults.put("DashBoard Page Displayed", "Pass");    
			ReportHelper.getScreenShot1("On Dashboard Page");
			GenericHelper.logger.info("Dashboard Page Displayed");
		}
		else
		{
			currentConnectionStatus=  driver.findElement(By.xpath("//div[@class='connection_status connected']/child::p")).getText();
			ReportHelper.onTestFailure("Dashboard Page not displayed to the user");
			testResults.put("DashBoard Page Displayed", "Fail");
			ReportHelper.getScreenShot1("Can not reach On Dashboard Page");
			hitReport("Can not reach On Dashboard Page", "Very High", currentReportPath,currentConnectionStatus);
			driver.quit();
		}
		
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			dashBoardPage.trade().click();
			Thread.sleep(1000);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			//dashBoardPage.margin().click();
		} catch (Exception e) 
		{
			currentConnectionStatus=  driver.findElement(By.xpath("//div[@class='connection_status connected']/child::p")).getText();
			hitReport("Can not redirect to Margin Page", "High", currentReportPath,currentConnectionStatus);
			// TODO: handle exception
		}
		
	}
	
	
	
}
