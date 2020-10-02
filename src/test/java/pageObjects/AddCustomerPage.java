package pageObjects;

import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCustomerPage {
	private WebDriver localDriver;
	
	public AddCustomerPage(WebDriver driver) {
		localDriver = driver;
		PageFactory.initElements(localDriver, this);
	}
	
	By lnkCustomers_menu = By.cssSelector("body > div.wrapper > div.main-sidebar > div > ul > li:nth-child(4) > a > span");
	By lnkCustomers_menuitem = By.cssSelector("li.treeview:nth-child(4) > ul:nth-child(2) > li:nth-child(1) > a:nth-child(1) > span:nth-child(2)");
	By btnAddCustomer = By.cssSelector(".bg-blue");
	
	By txtEmail = By.cssSelector("#Email");
	By txtPwd = By.cssSelector("#Password");
	By txtFirstName = By.xpath("//*[@id=\"FirstName\"]");
	By txtLastName = By.xpath("//*[@id=\"LastName\"]");
	By rdMaleGender = By.xpath("//*[@id=\"Gender_Male\"]");
	By rdFemaleGender = By.xpath("//*[@id=\"Gender_Female\"]");
	By txtDoB = By.xpath("//*[@id=\"DateOfBirth\"]");
	
	
	By txtCustomerRoles = By.xpath("//*[@id=\"customer-info\"]/div[2]/div[1]/div[10]/div[2]/div/div[1]/div");
	By lstItemAdmininstrators = By.xpath("/html/body/div[6]/div/div[2]/ul/li[1]");
	By lstItemRegistered = By.xpath("/html/body/div[6]/div/div[2]/ul/li[4]");
	By lstItemGuests = By.xpath("/html/body/div[6]/div/div[2]/ul/li[3]");
	By lstItemVendors = By.xpath("/html/body/div[6]/div/div[2]/ul/li[5]");
	
	By drpMgrVendor = By.xpath("//*[@id=\"VendorId\"]");
	By txtAdminComment = By.xpath("//*[@id=\"AdminComment\"]");
	
	By btnSave = By.xpath("/html/body/div[3]/div[3]/div/form/div[1]/div/button[1]");
	
	//Action Methods
	
	public void clickOnCustomersMenu() {
		localDriver.findElement(lnkCustomers_menu).click();
	}
	public void clickOnCustomersMenuItem() {
		localDriver.findElement(lnkCustomers_menuitem).click();
	}
	public void clickOnAddNewCustomer() {
		localDriver.findElement(btnAddCustomer).click();
	}
	public void setEmail(String email) {
		localDriver.findElement(txtEmail).clear();
		localDriver.findElement(txtEmail).sendKeys(email);
	}
	public void setPwd(String password) {
		localDriver.findElement(txtPwd).clear();
		localDriver.findElement(txtPwd).sendKeys(password);
	}
	public void setCustomerRoles(String role) throws InterruptedException {		
		//if role is not vendors then it Register should not be deleted
		if(!role.equals("Vendors"))
			localDriver.findElement(By.xpath("//*[@id=\"SelectedCustomerRoleIds_taglist\"]/li/span[2]")).click();
		
		localDriver.findElement(txtCustomerRoles).click();
		Thread.sleep(2000);
		WebElement listItem;
		
		switch(role) {
		case "Administrators":
			listItem = localDriver.findElement(lstItemAdmininstrators);
			break;
		case "Guests":
			listItem = localDriver.findElement(lstItemGuests);
			break;
		case "Vendors":
			listItem = localDriver.findElement(lstItemVendors);
			break;
		case "Registered":
		default:
			listItem = localDriver.findElement(lstItemRegistered);
			listItem.click();
			break;
		}
		listItem.click();
		
		
		// This javascript code to be executed only if 
		  JavascriptExecutor js = (JavascriptExecutor)localDriver;
		  js.executeScript("arguments[0].click()", listItem);
		 
		
	}	
	public void setFirstName(String firstName) {
		localDriver.findElement(txtFirstName).clear();
		localDriver.findElement(txtFirstName).sendKeys(firstName);
	}
	public void setLastName(String lastName) {
		localDriver.findElement(txtLastName).clear();
		localDriver.findElement(txtLastName).sendKeys(lastName);
	}
	public void setGender(String gender) {
		switch(gender.toLowerCase(Locale.ROOT)) {
		case "female":
			localDriver.findElement(rdFemaleGender).click();
			break;
		case "male":
		default:
			localDriver.findElement(rdMaleGender).click();
			break;
		}
	}
	public void setDOB(String DOB) {
		localDriver.findElement(txtDoB).sendKeys(DOB);
	}
	public void setManagerOfVendor(String Vendor) {
		Select drpdwn = new Select(localDriver.findElement(drpMgrVendor));
		drpdwn.selectByVisibleText(Vendor);
	}
	public void setAdminComments(String comments) {
		localDriver.findElement(txtAdminComment).sendKeys(comments);
	}
	public void saveCustomer() {
		localDriver.findElement(btnSave).click();
	}
}
