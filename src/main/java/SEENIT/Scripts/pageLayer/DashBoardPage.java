package SEENIT.Scripts.pageLayer;

import javax.xml.xpath.XPath;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SEENIT.Scripts.utils.MasterClass;

public class DashBoardPage extends MasterClass
{

	public DashBoardPage() 
	{
		PageFactory.initElements(driver, this);	
	}

	//a[@href='/spot-trading/']
	//a[@href='/margin-trading/']
	
	@FindBy (xpath="//a[@href='/spot-trading/']")
	private WebElement spot;
	
	public WebElement spot()
	{
		return spot;
	}
	
	@FindBy (xpath="//a[@href='/margin-trading/']")
	private WebElement margin;
	
	public WebElement margin()
	{
		return margin;
	}
	
	@FindBy (xpath = "//div[@class='hover_tooltip']/child::a[contains(text(), 'Trade')]")
	
	
	private WebElement trade;
	public WebElement trade()
	{
		return trade;
	}

}
