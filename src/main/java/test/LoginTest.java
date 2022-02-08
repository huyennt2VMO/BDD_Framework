package test;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import listener.TestNGListener;
import page.Login;
import utils.keywords.WebKeywords;

public class LoginTest extends TestNGListener {

	Login objLogin;

//	public WebKeywords action;

	public LoginTest() {

	}

	public LoginTest(WebKeywords action) {
		super(action);
	}

	@Test(description = "ER01_Username cannot be empty")
	public void ER01_UsernameCannotBeEmpty() {
		objLogin = new Login(action);
		objLogin.inputUsername("");
		objLogin.pressTab();
		assertTrue(objLogin.shouldBeUserNameMessageErrorAs("Username cannot be blank."));
	}

	@Test(description = "ER02_Username cannot be empty 2")
	public void ER02_UsernameCannotBeEmpty2() throws Throwable {
		objLogin.inputUsername(" ");
		objLogin.pressTab();
		assertTrue(objLogin.shouldBeUserNameMessageErrorAs("Username cannot be blank."));
	}

	@Test(description = "ER03_Password cannot be empty")
	public void ER03_PasswordCannotBeEmpty() {
		objLogin.inputPassword("");
		objLogin.pressTab();
		assertTrue(objLogin.shouldBePasswordMessageErrorAs("Password cannot be blank."));
	}

	@Test(description = "ER04_Password cannot be empty2")
	public void ER04_PasswordCannotBeEmpty2() {
		objLogin.inputPassword(" ");
		objLogin.pressTab();
		assertTrue(objLogin.shouldBePasswordMessageErrorAs("Password cannot be blank."));
	}

	@Test(description = "SC_Login successfully")
	public void SC_LoginSuccessfully() throws Throwable {
		objLogin.inputUsername("super");
		objLogin.inputPassword("admin");
		objLogin.clickButtonSignUp();
	}

}
