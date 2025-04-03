package SEENIT.Scripts.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import java.io.File;
import org.apache.commons.io.FileUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericHelper extends MasterClass{

	public static final Logger logger = LoggerFactory.getLogger(GenericHelper.class);
	
	
	//////////////// Screen Shot Code/////////////////////////
	public static void takeScreenshot(WebDriver driver, String filePath) 
	{
		try {

			TakesScreenshot ts = (TakesScreenshot) driver;


			File source = ts.getScreenshotAs(OutputType.FILE);


			File destination = new File(filePath);


			FileUtils.copyFile(source, destination);

			//System.out.println("Screenshot taken and saved at " + destination.getPath());

		} 
		catch (Exception e) 
		{
			System.out.println("Error while taking screenshot: " + e.getMessage());
		}

	}
	
	public static String getScreenshotWithTimestamp(WebDriver driver) 
	{
		SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
		String format = sdf.format(new Date());
		String fileName = "Screenshot_On_" + format + ".png";
		//String fullPath = screenShotFolderPath1 + File.separator + fileName;
		//String fullPath = "./ExtentReport/ScreenShots"+File.separator+fileName;
		String fullPath = new File("./ExtentReport/ScreenShots" + File.separator + fileName).getAbsolutePath();
		takeScreenshot(driver, fullPath);
		return fullPath;
	}

	////////////////Screen Shot Code END/////////////////////////
	


}
