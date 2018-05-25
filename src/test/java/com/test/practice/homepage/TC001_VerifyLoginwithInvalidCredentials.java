package com.test.practice.homepage;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.practice.testbase.TestBase;
import com.test.practice.uiactions.Homepage;

public class TC001_VerifyLoginwithInvalidCredentials extends TestBase {
	
	public static final Logger log = Logger.getLogger(TC001_VerifyLoginwithInvalidCredentials.class.getName());
	
	
	Homepage homepage;
	
	@BeforeClass
	public void setup()
	{
		
		init();
		
	}
	
	@Test
	public void verifyLoginwithInvalidCredentials()
	{
		log.info("===============Starting verifyLoginwithInvalidCredentials Test========================");
		homepage = new Homepage(driver);
		homepage.logintoApplication("abcd@gmail.com", "pass1234");
		Assert.assertEquals(homepage.getInvalidLoginText(), "Authentication failed.");
		
		log.info("===============Finished verifyLoginwithInvalidCredentials Test========================");
				
	}

	@AfterClass
	public void endtest()
	{
		driver.close();
	}
}
