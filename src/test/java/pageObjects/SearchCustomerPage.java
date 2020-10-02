package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import util.WaitHelper;

public class SearchCustomerPage {
	private WebDriver localDriver;

	private WaitHelper helper;

	public SearchCustomerPage(WebDriver driver) {
		localDriver = driver;
		PageFactory.initElements(localDriver, this);
		helper = new WaitHelper(driver);
	}

	@FindBy(how = How.ID, using = "SearchEmail")
	@CacheLookup
	WebElement email;

	@FindBy(how = How.ID, using = "SearchCompany")
	@CacheLookup
	WebElement company;

	@FindBy(how = How.ID, using = "SearchFirstName")
	@CacheLookup
	WebElement firstname;

	@FindBy(how = How.ID, using = "SearchLastName")
	@CacheLookup
	WebElement lastname;

	@FindBy(how = How.ID, using = "search-customers")
	@CacheLookup
	WebElement searchCustomerbtn;

	@FindBy(how = How.XPATH, using = "//table[@role='grid']")
	@CacheLookup
	WebElement tableSearchResults;

	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']")
	WebElement table;

	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']/tbody/tr")
	List<WebElement> tableRows;

	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']/tbody/tr/td")
	List<WebElement> tableColumns;

	public void setSearchEmail(String emailtxt) {
		helper.waitForElement(email, 30);
		email.clear();
		email.sendKeys(emailtxt);
	}

	public void setSearchCompany(String companytxt) {
		helper.waitForElement(company, 30);
		company.clear();
		company.sendKeys(companytxt);
	}

	public void setFirstname(String fname) {
		helper.waitForElement(firstname, 30);
		firstname.clear();
		firstname.sendKeys(fname);
	}

	public void setLastname(String lname) {
		helper.waitForElement(lastname, 30);
		lastname.clear();
		lastname.sendKeys(lname);
	}

	public void searchCustomer() {
		searchCustomerbtn.click();
	}

	public int getRowCount() {
		return tableRows.size();
	}

	public int getColumnCount() {
		return tableColumns.size();
	}

	public boolean searchCustomerByEmail(String emailtxt) {
		boolean flag = false;

		/*
		 * if (getRowCount() <= 1)// No rows are returned return flag;
		 */

		for (int i = 1; i <= getRowCount(); i++) {
			String emailID = table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr/td[2]")).getText();

			if (emailID.equals(emailtxt))
				flag = true;
		}

		return flag;
	}

	public boolean searchCustomerByDetails(String emailtxt, String fname, String lname) {
		boolean flag = false;

		if (getRowCount() <= 1)// No rows are returned
			return flag;

		for (int i = 0; i <= getRowCount(); i++) {
			String emailID = table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr/td[2]")).getText();
			String name = table.findElement(By.xpath("//table[@id='customers-grid']/tbody/tr/td[3]")).getText();

			if (emailID.equals(emailtxt) || name.contains(fname) || name.contains(lname))
				flag = true;
		}
		return flag;
	}
}