package steps;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import utils.CommonMethods;
import utils.ConfigsReader;

public class LoginPageSteps extends CommonMethods {

	
	@Given("I put valid username and password click login button")
	public void i_put_valid_username_and_password() {
		loginPage.loginToSystem();
	}
	@Given("click login button")
	public void click_login_button() {
		 click(loginPage.loginButton);
	}
	@Then("I should be able to see dashboard page")
	public void i_should_be_able_to_see_dashboard_page() {
		
	   Assert.assertTrue("Account name is not displayed!!",loginPage.accountName.isDisplayed());
	}

	@Given("I put invalid username and password")
	public void i_put_invalid_username_and_password() {
	    sendText(loginPage.username, "gdrg");
	    sendText(loginPage.password, "sdf");
	    click(loginPage.loginButton);
	}
	@Then("I should navigate to RETRY LOGIN page and see the error message")
	public void i_should_navigate_to_retry_login_page_and_see_the_error_message() {
	   Assert.assertTrue("Retry Login page is not displayed!!", loginPage.retryLoginPage.isDisplayed());
	}
	
	@Given("I leave the username box empty and send valid password")
	public void i_leave_the_username_box_empty_and_send_valid_password() {
	   sendText(loginPage.username, "");
	   sendText(loginPage.password, ConfigsReader.getProperty("password"));
	}
	
	@Then("I should be able to see USERNAME CANNOT BE EMPTY message")
	public void i_should_be_able_to_see_username_cannot_be_empty_message() {
	 Assert.assertTrue("Password cannot be empty message is not displayed!!", loginPage.emptyUsername.isDisplayed());
	}
	
	@Given("I leave the password box empty and send valid username")
	public void i_leave_the_password_box_empty_and_send_valid_username() {
	sendText(loginPage.username, ConfigsReader.getProperty("username"));
	sendText(loginPage.password,"");
	}
	@Then("I should be able to see PASSWORD CANNOT BE EMPTY message")
	public void i_should_be_able_to_see_password_cannot_be_empty_message() {
	    Assert.assertTrue("password cannot be empty message is not displayed!!", loginPage.emptyPass.isDisplayed());
	}
	
	@Given("I leave the password and username empty")
	public void i_leave_the_password_and_username_empty() {
	    sendText(loginPage.username, "");
	    sendText(loginPage.password, "");
	}
	
	@Then("I should be able to see CANNOT BE EMPTY messages")
	public void i_should_be_able_to_see_cannot_be_empty_messages() {
	    Assert.assertTrue("password cannot be empty message is not displayed!!", loginPage.emptyPass.isDisplayed());
		 Assert.assertTrue("Password cannot be empty message is not displayed!!", loginPage.emptyUsername.isDisplayed());

	}
	
}
