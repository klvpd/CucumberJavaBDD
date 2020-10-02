package stepDefinitions;

import java.util.Properties;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class BaseClass {
	public WebDriver driver;

	public LoginPage lp;
	public AddCustomerPage addCustomer;
	public SearchCustomerPage searchCust;
	public static Logger logger;
	public Properties configProp;

	public String localBrowser;

	// to generate a random unique email id
	public static String randomEmail() {
		String generatedEmail = RandomStringUtils.randomAlphabetic(5);
		return generatedEmail + "@gmail.com";
	}
}
