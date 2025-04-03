package SEENIT.Scripts.core;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import SEENIT.Scripts.pageLayer.MarginPage;
import SEENIT.Scripts.utils.MasterClass;
import SEENIT.Scripts.utils.ReportHelper;
import ch.qos.logback.core.util.Duration;

public class Margin extends MasterClass
{

	public String percentageCount=null;
	public String updatedPercentageCount=null; 
	public WebDriverWait wait =null;


	public void verifyPercentageValues() throws Exception
	{	
		try {
			ReportHelper.createTestCase("Percentage Changed on Graph", "Verify that Perentage is Changing or not after 2 sconds");
			//ReportHelper.getScreenShot1("Before Changing Graph");
			Thread.sleep(9000);

			System.out.println(driver.getTitle());
			wait = new WebDriverWait(driver, 30);	
			ReportHelper.generateLog("On Margin Page");
			// Wait for the page to fully load by checking document.readyState
			wait.until(driver -> {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				return jsExecutor.executeScript("return document.readyState").equals("complete");
			});

			String frmeid= driver.findElement(By.xpath("//div[@id='tv_chart_container']/child::iframe")).getAttribute("id");
			driver.switchTo().frame(frmeid);
			////		WebElement element = wait.until(
			////	            ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='pane-legend-item-value-container']/child::span[@class='pane-legend-item-value-wrap']/child::span[text()='O']/parent::span/following-sibling::span[5]/child::span[2]"))
			////	        );
			////		percentageCount =driver.findElement(By.xpath("//div[@class='pane-legend-item-value-container']/child::span[@class='pane-legend-item-value-wrap'][6]/child::span[2]")).getText();
			Thread.sleep(2000);
			ReportHelper.getScreenShot1("Before Changing Values");
			percentageCount = marginPage.percentageCount().getText();	
			Thread.sleep(2000);

			ReportHelper.generateLog(percentageCount);
			System.out.println(percentageCount);
			Thread.sleep(70000); 
			updatedPercentageCount= marginPage.percentageCount().getText();
			//		 
			if(!percentageCount.equals(updatedPercentageCount))
			{
				ReportHelper.generateLog("Updated percentage is"+ updatedPercentageCount);
				ReportHelper.getScreenShot1(" After Chanaged Graph");
				Thread.sleep(2000);
				ReportHelper.onTestPass("Percentage changed frequently");
				testResults.put("Graph Percentage Changed", "Pass");
			}
			else
			{ 
				currentConnectionStatus=  driver.findElement(By.xpath("//div[@class='connection_status connected']/child::p")).getText();
				ReportHelper.getScreenShot1("Graph Without change");
				testResults.put("Graph Percentage Changed", "Fail");
				Thread.sleep(2000);
				ReportHelper.onTestFailure("Graph not chnaging");
				hitReport("Graph not chnaging", "High",currentReportPath, currentConnectionStatus);
			}	
			///End Of Code add the switch to default content
			driver.switchTo().defaultContent();
		} 
		catch (Exception e) {
			currentConnectionStatus=  driver.findElement(By.xpath("//div[@class='connection_status connected']/child::p")).getText();
			ReportHelper.getScreenShot1("Graph not Updating");
			testResults.put("Graph Percentage Changed", "Fail");
			Thread.sleep(2000);
			ReportHelper.onTestFailure("Graph not Updating");
			hitReport("Graph not chnaging", "High",currentReportPath,currentConnectionStatus);
			
		}
		
	}

	public void verifytradeValues() throws Exception
	{
		ReportHelper.createTestCase("Sell Values on Spot Trade", "Verify that Sell trade Count is changing or not");

		driver.switchTo().defaultContent(); 

		//////////// Sell Value///
		try {
			String sellFirstValue = driver.findElement(By.xpath("//div[@class='ob_table_body position-relative']/descendant::div[@id='sell'][1]/child::div[@class='row mx-auto']/child::div[@class='col-3 px-0']/child::p")).getText();
			ReportHelper.getScreenShot1("Value in Sell column  "+ sellFirstValue);
			Thread.sleep(3000);
			String updatedSellFirstValue = driver.findElement(By.xpath("//div[@class='ob_table_body position-relative']/descendant::div[@id='sell'][1]/child::div[@class='row mx-auto']/child::div[@class='col-3 px-0']/child::p")).getText();
			ReportHelper.getScreenShot1("Value in Sell column After 5 Seconds "+ updatedSellFirstValue);
			Thread.sleep(6000);
			if(!sellFirstValue.equals(updatedSellFirstValue))
			{

				ReportHelper.getScreenShot1("Changed Value in sell ");
				Thread.sleep(2000);
				ReportHelper.onTestPass("Sell value changing continuesly ");
				testResults.put("Sell Value Changed", "Pass");
			}
			else
			{
				currentConnectionStatus=  driver.findElement(By.xpath("//div[@class='connection_status connected']/child::p")).getText();
				ReportHelper.getScreenShot1("Remain unchanged Value");
				Thread.sleep(2000);
				ReportHelper.onTestFailure("Sell Values are not chnaging");
				testResults.put("Sell Value Changed", "Fail");
				hitReport("Values are not changing", "high",currentReportPath,currentConnectionStatus);
			}	
		} 
		
		catch (Exception e) {
		
			currentConnectionStatus=  driver.findElement(By.xpath("//div[@class='connection_status connected']/child::p")).getText();
			ReportHelper.getScreenShot1("Remain unchanged Value");
			Thread.sleep(2000);
			ReportHelper.onTestFailure("Sell Values are not chnaging");
			testResults.put("Sell Value Changed", "Fail");
			hitReport("Values are not changing", "high",currentReportPath,currentConnectionStatus);
			
		}
		

		
		
////////Buy Value /// 
		try {
			ReportHelper.createTestCase("Buy Values on Spot Trade", "Verify that Sell trade Count is changing or not");
			String buyFirstValue = driver.findElement(By.xpath("//div[@class='ob_table_profit ']/child::div[@id='buy']/descendant::p[1]")).getText();
			ReportHelper.getScreenShot1("Value in Buy column  "+ buyFirstValue);
			Thread.sleep(3000);
			String updatedBuyFirstValue = driver.findElement(By.xpath("//div[@class='ob_table_profit ']/child::div[@id='buy']/descendant::p[1]")).getText();
			ReportHelper.getScreenShot1("Value in Buy column After 5 Seconds "+ updatedBuyFirstValue);
			Thread.sleep(6000);
			if(!buyFirstValue.equals(updatedBuyFirstValue))
			{

				ReportHelper.getScreenShot1("Changed Value in Buy ");
				Thread.sleep(2000);
				ReportHelper.onTestPass("Buy value changing continuesly ");
				testResults.put("Buy Value Changed", "Pass");
			}
			else
			{
				currentConnectionStatus=  driver.findElement(By.xpath("//div[@class='connection_status connected']/child::p")).getText();
				ReportHelper.getScreenShot1("Remain unchanged Value");
				Thread.sleep(2000);
				ReportHelper.onTestFailure("Buy Values are not chnaging");
				testResults.put("Buy Value Changed", "Fail");
				hitReport("Buy Values are not changing", "high",currentReportPath,currentConnectionStatus);
				
			}
		} catch (Exception e) 
		{
			currentConnectionStatus=  driver.findElement(By.xpath("//div[@class='connection_status connected']/child::p")).getText();
			ReportHelper.onTestFailure("Buy Trade Values not found");
			testResults.put("Buy Trade Values not found", "Fail");
			hitReport("Buy Trade Values not found", "high",currentReportPath,currentConnectionStatus);
		}
		

		////////Recent Trade Value /// 
		try{
			ReportHelper.createTestCase("Recent Trade Values on Spot Trade", "Verify that Recent Trade Count is changing or not");
			String recentTradeFirstValue = driver.findElement(By.xpath("//div[@class='trade_table_body recent_trades  ']/descendant::p[1]")).getText();
			ReportHelper.getScreenShot1("Value in Sell column  "+ recentTradeFirstValue);
			Thread.sleep(6000);
			String updatedRecentTradeFirstValue = driver.findElement(By.xpath("//div[@class='trade_table_body recent_trades  ']/descendant::p[1]")).getText();
			ReportHelper.getScreenShot1("Value in Sell column After 5 Seconds "+ updatedRecentTradeFirstValue);
			Thread.sleep(3000);
			if(!recentTradeFirstValue.equals(updatedRecentTradeFirstValue))
			{

				ReportHelper.getScreenShot1("Changed Value in Buy ");
				Thread.sleep(2000);
				ReportHelper.onTestPass("Recent Trade value changing continuesly ");
				testResults.put("Recent Trade values Changed", "Pass");
			}
			else
			{
				currentConnectionStatus=  driver.findElement(By.xpath("//div[@class='connection_status connected']/child::p")).getText();
				ReportHelper.getScreenShot1("Recent Trade values are remain unchanged");
				Thread.sleep(2000);
				ReportHelper.onTestFailure("Recent Trade Values are not chnaging");
				testResults.put("Recent Trade values Changed", "Fail");
				hitReport("Recent Trade Values are not changing", "high",currentReportPath,currentConnectionStatus);
			}	
		}
		catch (Exception e) 
		{		
			currentConnectionStatus =  driver.findElement(By.xpath("//div[@class='connection_status connected']/child::p")).getText();
			ReportHelper.onTestFailure("Recent Trade Values not found");
			testResults.put("Recent Trade Values not found", "Fail");
			hitReport("Recent Trade Values not found", "high",currentReportPath,currentConnectionStatus);
		}		
	}
}


