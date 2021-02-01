package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import dataProvider.ConfigFileReader;
public class BitlyLandingPage {
	WebDriver driver;
	ConfigFileReader configFileReader;
	 
	 public BitlyLandingPage(WebDriver driver) {
	 this.driver = driver;
	 PageFactory.initElements(driver, this);
	 }
	 
	 public void navigateTo_BitlyLandingPage() {
		 configFileReader= new ConfigFileReader();
	 driver.get(configFileReader.getApplicationUrl());
	 }
}
