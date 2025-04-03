package SEENIT.Scripts.pageLayer;
import javax.xml.xpath.XPath;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import SEENIT.Scripts.utils.MasterClass;

public class MarginPage extends MasterClass{
	
	public MarginPage()
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='pane-legend-item-value-container']/child::span[@class='pane-legend-item-value-wrap']/child::span[text()='O']/following-sibling::span")
	private WebElement open;
	public WebElement open()
	{
		return open;
	}
	
	@FindBy(xpath="//div[@class='pane-legend-item-value-container']/child::span[@class='pane-legend-item-value-wrap']/child::span[text()='C']/following-sibling::span")
	private WebElement close;
	public WebElement close()
	{
		return close;
	}
	
	@FindBy(xpath="//div[@class='pane-legend-item-value-container']/child::span[@class='pane-legend-item-value-wrap']/child::span[text()='H']/following-sibling::span")
	private WebElement high;
	public WebElement high()
	{
		return high;
	}
	
	@FindBy(xpath="//div[@class='pane-legend-item-value-container']/child::span[@class='pane-legend-item-value-wrap']/child::span[text()='H']/following-sibling::span")
	private WebElement low;
	public WebElement low()
	{
		return low;
	}

	//div[@class='pane-legend-item-value-container']/child::span[@class='pane-legend-item-value-wrap']/child::span[text()='O']/parent::span/following-sibling::span[5]
	
	@FindBy(xpath="//div[@class='pane-legend-item-value-container']/child::span[@class='pane-legend-item-value-wrap']/child::span[text()='O']/parent::span/following-sibling::span[5]/child::span[2]")
	private WebElement percentageCount;
	public WebElement percentageCount()
	{
		return percentageCount;
	}
	
	
}
