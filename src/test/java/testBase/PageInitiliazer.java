package testBase;

import sepidehPages.LoginPageElements;

public class PageInitiliazer extends BaseClass {

	public static LoginPageElements loginPage;
	
	
	
	public static void initialize() {
		
		loginPage= new LoginPageElements();
		
	}

}
