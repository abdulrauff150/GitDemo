package com.test.practice.uiactions;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.practice.testbase.TestBase;

public class Homepage extends TestBase {
	
	public static final Logger log = Logger.getLogger(Homepage.class.getName());
	
	WebDriver driver;
	
	@FindBy(xpath="//a[@class='login']")
	WebElement clickSignin;
	
	@FindBy(xpath="//*[@id='email']")
	WebElement enterLogin;
	
	@FindBy(xpath="//*[@id='passwd']")
	WebElement enterPassword;
	
	@FindBy(xpath="//*[@id='SubmitLogin']")
	WebElement clickOnSubmit;
	
	@FindBy(xpath="//*[@id='center_column']/div[1]/ol/li")
	WebElement authenticationfailed;
	
	@FindBy(xpath="//*[@class='logout']")
	WebElement SignOut;

	public Homepage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void logintoApplication(String emailaddress, String loginpassword)
	{
		clickSignin.click();
		log.info("Clicked on Sign In and Object is "+ clickSignin.toString() );
		enterLogin.sendKeys(emailaddress);
		log.info("Entered Email address "+emailaddress+" and object is "+enterLogin.toString());
		enterPassword.sendKeys(loginpassword);
		log.info("Entered passwrod "+loginpassword+" and object is "+enterPassword.toString());
		clickOnSubmit.click();
		log.info("Clicked on Submit button and object is "+clickOnSubmit.toString());
		
	}
	
	public String getInvalidLoginText()
	{
		log.info("error message is "+authenticationfailed.getText());
		return authenticationfailed.getText();
		
		
	}
	
	public boolean verifySignoutisDisplayed()
	{
		try {
			//waitForElement(300, SignOut);
			SignOut.isDisplayed();
			log.info("SignOut button is displayed and the Object is:-" + SignOut.toString());
			return true;
		} catch (Exception e) {
			
			return false;
		}
		
		
	}
	
	public void clickonSignOut()
	{
		
		SignOut.click();
	}
}
