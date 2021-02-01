package pageObjects;

import java.util.Set;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import dataProvider.ConfigFileReader;

public class HomePage {
	WebDriver driver;
	ConfigFileReader configFileReader;

	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}



	@FindBy(xpath = "(//div[@class ='nav-wrap']//li//a[@href ='https://bitly.com/a/sign_in'])")
	private WebElement btn_login;

	@FindBy(xpath = "(//input[@id ='shorten_url'])")
	private WebElement shorten_your_link_box;

	@FindBy(xpath = "(//input[@id ='shorten_btn'])")
	private WebElement shorten_btn;

	@FindBy(xpath = "(//div[@id ='shorten_actions']//ul//li//span[@class ='long-link'])")
	private WebElement actualLink;

	@FindBy(xpath = "(//div[@id ='shorten_actions']//ul//li//span[@class ='short-link'])[1]")
	private WebElement shortenLink1;

	@FindBy(xpath = "(//div[@id ='shorten_actions']//ul//li//span[@class ='short-link'])[2]")
	private WebElement shortenLink2;

	@FindBy(xpath = "(//div[@id ='shorten_actions']//ul//li//span[@class ='copy'])")
	private WebElement copyBtn;

	@FindBy(xpath = "//div[@id = 'wow-modal-window-2']//div[@id = 'wow-modal-close-2']")
	private WebElement adWindowCloseBtn;

	@FindBy (xpath = "//div[@id = 'shorten_actions']//ul//li[@class= 'error text-center align-center']//span")
	private WebElement invalidUrlError;

	//Unable to shorten that link. It is not a valid url.




	public void logIn() {
		btn_login.isDisplayed();
		btn_login.getText();
	}

	public void adWindowCloseBtn( ) {
		adWindowCloseBtn.isDisplayed();
		adWindowCloseBtn.click();
	}

	public void shortenFeatureInput() {
		configFileReader= new ConfigFileReader();
		shorten_your_link_box.sendKeys(configFileReader.getActualUrl());	
	}

	public void shortenBtnClick() {
		shorten_btn.click();
	}

	public String shortenUrl() {
		return shortenLink1.getText();			
	}

	public void shortenFeatureValidation() {
		shortenLink1.click();
	}

	public String shortenUrl2() {
		return shortenLink2.getText();
	}

	public void shortenFeatureInvalidInput() {
		configFileReader= new ConfigFileReader();
		shorten_your_link_box.sendKeys(configFileReader.getInvalidUrl());	
	}

	public String getInvalidUrlErrorMessage() {

		return invalidUrlError.getText();
	}

	public void refreshBrowser() {
		driver.navigate().refresh();
	}

	public String getShortenLinkBoxText( ) {
		return shorten_your_link_box.getText();
	}
}
