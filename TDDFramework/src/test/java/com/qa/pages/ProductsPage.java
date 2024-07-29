package com.qa.pages;

import org.openqa.selenium.WebElement;

import com.qa.BaseTest;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ProductsPage extends BaseTest {
	
	@AndroidFindBy (xpath = "//android.widget.TextView[@text=\\\"PRODUCTS\\\"]") private WebElement productTitleTxt;

	public String getTitle() {
		return getAttribute(productTitleTxt,"text");
		
		
	}

}
