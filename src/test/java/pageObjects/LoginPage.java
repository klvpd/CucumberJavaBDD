package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	private WebDriver localDriver;

	public LoginPage(WebDriver remoteDriver) {
		super();
		localDriver = remoteDriver;
		PageFactory.initElements(remoteDriver, this);
	}
	
	@FindBy(how = How.CSS, using = "#Email")
	@CacheLookup
	WebElement uid;
	
	@FindBy(how = How.CSS, using = "#Password")
	@CacheLookup
	WebElement pwd;
	
	@FindBy(how = How.CSS, using = ".button-1")
	@CacheLookup
	WebElement loginBtn;
	
	@FindBy(how = How.LINK_TEXT, using = "Logout")
	WebElement logoutBtn;
	
	public void setUid(String userId) {
		uid.clear();
		uid.sendKeys(userId);
	}
	
	public void setPwd(String userPwd) {
		pwd.clear();
		pwd.sendKeys(userPwd);
	}
	
	public void login() {
		loginBtn.click();
	}
	
	public void logout() {
		logoutBtn.click();
	}
}