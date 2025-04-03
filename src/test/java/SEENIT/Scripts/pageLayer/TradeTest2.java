package SEENIT.Scripts.pageLayer;


import org.testng.annotations.Test;

import SEENIT.Scripts.utils.MasterClass;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;

import java.util.HashMap;
import java.util.Map;


public class TradeTest2 extends MasterClass   
{ 
   
	@Test(priority = 1)          
	public void login() throws Exception         
	{			            
		login.loginToSystem();	                          
			            
		          
	}   
} 
  
