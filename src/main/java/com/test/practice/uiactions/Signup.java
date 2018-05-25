package com.test.practice.uiactions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Signup {

	public static final Logger log = Logger.getLogger(Signup.class.getName());
	
	WebDriver driver;
	
	@FindBy(xpath="//a[@class='login']")
	WebElement clickSignin;
	
	@FindBy(id="email_create")
	WebElement enterNewEmail;
	
	@FindBy(id="SubmitCreate")
	WebElement submitNewEmail;
	
	public Signup(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void registration_1(String email2register)
	{
		clickSignin.click();
		log.info("Clicked on Sign In to start registration and Object is "+ clickSignin.toString() );
		enterNewEmail.sendKeys(email2register);
		submitNewEmail.click();
	}
	
	
	
	
	
}
