package com.test.practice.testbase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.test.practice.customListeners.Listeners;
import com.test.practice.excelreader.Excel_Reader;

public class TestBase {
	
	public static final Logger log = Logger.getLogger(TestBase.class.getName());
	
	public static WebDriver driver;
	String url = "http://automationpractice.com/index.php";
	String browser = "firefox";
	Excel_Reader excel;
	Listeners lis;
	
	public void init()
	{
		selectBrowser(browser);
		//lis = new Listeners(driver);
		getUrl(url);
		String log4jconfpath = "log4j.properties";
		PropertyConfigurator.configure(log4jconfpath);
	}
	
	public void selectBrowser(String browser)
	{
		if(browser.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/drivers/geckodriver.exe");
			log.info("Creating Object of " + browser);
			driver=new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
	    	driver=new ChromeDriver();
		}
		
	}

	public void getUrl(String url)
	{
		log.info("navigating to "+ url);
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
	}
	
	public String[][] getData(String excelName, String sheetName)
	{
		
		String path = System.getProperty("user.dir")+"/src/main/java/com/test/practice/data/"+excelName;
		excel = new Excel_Reader(path);
		String[][] data = excel.getDataFromSheet(sheetName, excelName);
		return data;
		
	}
	
	public void waitForElement(int timOutInSeconds, WebElement element )
	{
		WebDriverWait wait = new WebDriverWait(driver, timOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void getScreenShot(String name)
	{
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try {
		String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()+"/src/main/java/com/test/practice/screenshots/";
		File destFile = new File((String)reportDirectory + name + "__" + formater.format(calendar.getTime()) + ".png");
		FileUtils.copyFile(srcFile, destFile);
		
		//This will help us to link the screenshot in testNG Report
		Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height='100' width='100' /> </a>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
