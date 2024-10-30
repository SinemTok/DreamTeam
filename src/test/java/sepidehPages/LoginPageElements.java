package sepidehPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testBase.BaseClass;
import utils.CommonMethods;
import utils.ConfigsReader;

public class LoginPageElements extends CommonMethods{

	
	@FindBy(id= "txtUsername")
	public WebElement username;
	
	@FindBy(id="txtPassword")
	public WebElement password;
	
	@FindBy(xpath= "//button[text()='Login']")
	public WebElement loginButton;
	
	@FindBy(id="account-name")
	public WebElement accountName;
	
	@FindBy(xpath="//div[text()='Retry Login']")
	public WebElement retryLoginPage;
	
	@FindBy(id= "txtPassword-error")
	public WebElement emptyPass;
	
	@FindBy(id= "txtUsername-error")
	public WebElement emptyUsername;
	
	public LoginPageElements() {
		PageFactory.initElements(BaseClass.driver, this);
	}
	
	public static void loginToSystem()
	{
		  sendText(loginPage.username, ConfigsReader.getProperty("username"));
		  sendText(loginPage.password, ConfigsReader.getProperty("password"));
		  click(loginPage.loginButton);
	}
}
