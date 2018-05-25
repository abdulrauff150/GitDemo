package com.test.practice.registrationpage;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.test.practice.testbase.TestBase;
import com.test.practice.uiactions.Signup;

public class TC002_VerifySignUp extends TestBase{
	
	public static final Logger log = Logger.getLogger(TC002_VerifySignUp.class.getName());

	Signup signup1;
	
	@BeforeClass
	public void setup()
	{
		
		init();
		
	}
	
	@Test
	public void VerifySignUp()
	{
		log.info("===============Starting verifySignup Test========================");
		signup1 = new Signup(driver);
		signup1.registration_1("abdc123@gmail.com");
		
		
		log.info("===============Finished verifySignup Test========================");
				
	}

	@AfterClass
	public void endtest()
	{
		//driver.close();
	}
}
