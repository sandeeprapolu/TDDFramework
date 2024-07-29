package com.qa.pages;

import org.openqa.selenium.WebElement;

import com.qa.BaseTest;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPage extends BaseTest {

	@AndroidFindBy (accessibility = "test-Username") private WebElement userNameTxtField;
	@AndroidFindBy (accessibility = "test-Password") private WebElement passwordTxtField;
	@AndroidFindBy (accessibility = "test-LOGIN") private WebElement loginBtn;
	@AndroidFindBy (xpath = "//android.widget.TextView[@text=\\\"Username and password do not match any user in this service.\\\"]") private WebElement errTxt;
	
	public LoginPage enterUserName(String username) {
		sendKeys(userNameTxtField,username);
		return this;
		
	}
	
	public LoginPage enterPassword(String password) {
		sendKeys(passwordTxtField,password);
		return this;
		
	}
	
	public ProductsPage pressLoginBtn() {
		click(loginBtn);
		return new ProductsPage();
		
	}
	
	public String getErrTxt() {
		return getAttribute(errTxt, "text");
	}

}


