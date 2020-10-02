package stepDefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class Steps extends BaseClass {

	@Before
	public void setup() throws IOException {
		logger = Logger.getLogger("CucumberJavaBDD");
		PropertyConfigurator.configure("log4j.properties");

		configProp = new Properties();
		FileInputStream configFile = new FileInputStream("config.properties");
		configProp.load(configFile);

		localBrowser = configProp.getProperty("browser").toLowerCase(Locale.ROOT);

		logger.info("Launching browser " + localBrowser);

		switch (localBrowser) {
		case "firefox":
			System.setProperty("webdriver.gecko.driver", configProp.getProperty("firefoxpath"));
			driver = new FirefoxDriver();
			break;
		case "ie":// does not work in 64 bit.
			System.setProperty("webdriver.ie.driver", configProp.getProperty("iepath"));
			driver = new InternetExplorerDriver();
			break;
		case "edge":
			System.setProperty("webdriver.edge.driver", configProp.getProperty("edgepath"));
			driver = new EdgeDriver();
			break;
		case "chrome":// browser not installed on system.
			System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromepath"));
			driver = new ChromeDriver();
			break;
		default:
			System.err.println("Defined browser not available or invalid browser");
			System.exit(1);
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Given("User Launch browser")
	public void user_launch_browser() {
		lp = new LoginPage(driver);
	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {
		logger.info("Opening URL");
		driver.get(url);
	}

	@And("User enters valid user id {string} and valid password {string}")
	public void user_enters_valid_user_id_and_valid_password(String email, String password) {
		logger.info("Attempting Login");
		lp.setUid(email);
		lp.setPwd(password);
	}

	@When("User clicks login button")
	public void user_clicks_login_button() throws InterruptedException {
		lp.login();
		Thread.sleep(3000);
	}

	@Then("Page title should be {string}")
	public void page_title_should_be(String title) {
		Assert.assertEquals(title, driver.getTitle());
	}

	@When("User click on logout button")
	public void user_click_on_logout_button() throws InterruptedException {
		logger.info("Logging out");
		lp.logout();
		Thread.sleep(2000);
	}

	@And("close browser")
	public void close_browser() {
		driver.close();
	}

	// Customer feature step definitions ------------->

	@Then("User can view Dashboard")
	public void user_can_view_dashboard() {
		logger.info("Adding new customer");
		addCustomer = new AddCustomerPage(driver);
		Assert.assertEquals("Dashboard / nopCommerce administration", driver.getTitle());
	}

	@When("User click on customers menu")
	public void user_click_on_customers_menu() throws InterruptedException {
		addCustomer.clickOnCustomersMenu();
		Thread.sleep(1000);
	}

	@And("click on customer menu item")
	public void click_on_customer_menu_item() throws InterruptedException {
		addCustomer.clickOnCustomersMenuItem();
		Thread.sleep(1000);
	}

	@And("click on Add new button")
	public void click_on_add_new_button() throws InterruptedException {
		addCustomer.clickOnAddNewCustomer();
		Thread.sleep(1000);
	}

	@Then("User can view Add new customer page")
	public void user_can_view_add_new_customer_page() throws InterruptedException {
		Assert.assertEquals("Add a new customer / nopCommerce administration", driver.getTitle());
		Thread.sleep(1000);
	}

	@When("User enters customer info")
	public void user_enters_customer_info() throws InterruptedException {
		logger.info("******Entering new customer information*******");
		addCustomer.setEmail(randomEmail());
		addCustomer.setPwd("test123");
		addCustomer.setFirstName("Sample");
		addCustomer.setLastName("Test");
		addCustomer.setGender("Female");
		addCustomer.setCustomerRoles("Guest");
		addCustomer.setDOB("4/10/1985");
		addCustomer.setManagerOfVendor("Vendor 1");
		addCustomer.setAdminComments("Sample Test");
	}

	@And("click on Save button")
	public void click_on_save_button() throws InterruptedException {
		addCustomer.saveCustomer();
		Thread.sleep(2000);
	}

	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String msg) {
		Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
				.contains("The new customer has been added successfully."));
		logger.info("New Customer Added");
	}

	// Search Customer using email id features step definitions

	@When("Enter customer email {string}")
	public void enter_customer_email(String email) {
		logger.info("Searching Customer details...");
		searchCust = new SearchCustomerPage(driver);
		searchCust.setSearchEmail(email);
	}

	@And("Enter firstname {string}")
	public void enter_firstname(String fname) {
		searchCust.setFirstname(fname);
	}

	@And("Enter lastname {string}")
	public void enter_lastname(String lname) {
		searchCust.setLastname(lname);
	}

	@And("Click on search button")
	public void click_on_search_button() throws InterruptedException {
		searchCust.searchCustomer();
		Thread.sleep(3000);
	}

	@Then("User should find customer details with identifier {string} in search table")
	public void user_should_find_customer_details_in_search_table(String emailtxt) {
		boolean status = searchCust.searchCustomerByEmail(emailtxt);

		Assert.assertEquals(true, status);
		logger.info("Customer details found");
	}

	@Then("User should find customer details with identifier {string} or {string} or {string} in search table")
	public void user_should_find_customer_details_with_identifier_or_or_in_search_table(String email, String firstname,
			String lastname) {
		boolean status = searchCust.searchCustomerByDetails(email, firstname, lastname);

		Assert.assertEquals(true, status);
		logger.info("Customer details found");
	}

	@After
	public void tearDown(Scenario scenario) throws IOException, InterruptedException {

		if (scenario.isFailed()) {
			logger.error(scenario.getName() + " has failed");
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			final byte[] result = screenshot.getScreenshotAs(OutputType.BYTES);
			// embed into report
			scenario.attach(result, "Image/png", "Failed");
		}

		switch (localBrowser) {
		case "firefox":
			Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe");
			break;
		case "ie":
			Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
			break;
		case "edge":
			Runtime.getRuntime().exec("taskkill /F /IM msedgedriver.exe");
			break;
		case "chrome":
			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
			break;
		default:
			break;
		}
		Thread.sleep(3000);
	}
}