package SEENIT.Scripts.pageLayer;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import SEENIT.Scripts.utils.MasterClass;

public class LoginPage extends MasterClass{

	public LoginPage()
	{
		PageFactory.initElements(driver, this);
	}

	@FindBy (xpath= "//input[@type='email']")
	private WebElement emailAddress;
	public WebElement emailAddress()
	{
		return emailAddress;
	}

	@FindBy (xpath= "//input[@type='password']")
	private WebElement password;
	public WebElement password()
	{
		return password;
	}
	//button[@type='submit']
	
	@FindBy (xpath= "//button[@type='submit']")
	private WebElement submitButton;
	public WebElement submitButton()
	{
		return submitButton;
	}
	
}