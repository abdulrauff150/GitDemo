package com.test.practice.homepage;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.practice.testbase.TestBase;
import com.test.practice.uiactions.Homepage;

public class TC003_VerifyLoginwithdifferentRecords extends TestBase {
	
	public static final Logger log = Logger.getLogger(TC003_VerifyLoginwithdifferentRecords.class.getName());
	
	
	Homepage homepage;
	//String emailaddress = "automation@gmail.com";
	//String loginpassword = "password";
	
	@BeforeClass
	public void setup()
	{
		
		init();
		
	}
	
	@Test(dataProvider="logindata")
	public void verifyLoginwithdifferentRecords(String emailaddress, String loginpassword, String runMode )
	{
	
		if(runMode.equalsIgnoreCase("n"))
				{
			     throw new SkipException("user marked this record as no run");
				}
		log.info("===============Starting VerifyLoginwithdifferentRecords Test========================");
		homepage = new Homepage(driver);
		homepage.logintoApplication(emailaddress, loginpassword);
		boolean status = homepage.verifySignoutisDisplayed();
		getScreenShot("verifyLoginwithdifferentRecords.." +emailaddress);
		if(status)
		{
			homepage.clickonSignOut();
		}
		//Assert.assertEquals(status, true);
		
		log.info("===============Finished VerifyLoginwithdifferentRecords Test========================");
				
	}

	@AfterClass
	public void endtest()
	{
		//driver.close();
	}
	
	@DataProvider(name="logindata")
	public String[][] getTestData()
	{
		String[][] testRecords = getData("TestData.xlsx", "Sheet1");
		return testRecords;
	}
}

