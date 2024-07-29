package com.qa;

import org.testng.annotations.Test;

import com.qa.utils.TestUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;

public class BaseTest {
	protected AppiumDriver driver;
	protected Properties props;
	InputStream inputStream;
	
	
	public BaseTest() {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@Parameters({"platformName","udid","deviceName","avd"})
  @BeforeTest
  public void beforeTest(String platformName,String udid,String deviceName ,String avd) throws Exception {
	  
	  try {
		  props= new Properties();
		  String propFileName="config.properties";
		  inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		  props.load(inputStream);
		  
		  DesiredCapabilities caps = new DesiredCapabilities();
	      caps.setCapability("platformName", platformName);
		  caps.setCapability("appium:deviceName", deviceName);
	      caps.setCapability("appium:automationName", props.getProperty("androidAutomation"));
	      caps.setCapability("appium:udid", udid);
	      caps.setCapability("appium:avd", avd);
	      caps.setCapability("appium:avdLaunchTimeout", "180000");
	      caps.setCapability("appium:appPackage", props.getProperty("androidAppPackage"));
	      caps.setCapability("appium:appActivity", props.getProperty("androidAppActivity"));
	      URL appURL=getClass().getClassLoader().getResource(props.getProperty("androidAppLocation"));
	      caps.setCapability("appium:app", appURL);
	      
	      URL url = new URL(props.getProperty("appiumURL"));
	      
	       driver = new AppiumDriver(url,caps);
	      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	      String sessionId= driver.getSessionId().toString();
	      System.err.println(sessionId);
	  }catch (Exception e) {
		  e.printStackTrace();
		  throw e;
		// TODO: handle exception
	}
	  
  }
	
	public void waitForVisibility(WebElement e) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TestUtils.wait));
		wait.until(ExpectedConditions.invisibilityOf(e));
	}
	
	public void click(WebElement e) {
		waitForVisibility(e);
		e.click();
	}
	
	public void sendKeys(WebElement e,String txt) {
		waitForVisibility(e);
		e.sendKeys(txt);
	}
	
	public String getAttribute(WebElement e,String attribute) {
		waitForVisibility(e);
		return e.getAttribute(attribute);
	}

  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
