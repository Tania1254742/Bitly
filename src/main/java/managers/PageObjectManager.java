package managers;

import org.openqa.selenium.WebDriver;
import pageObjects.HomePage;
import pageObjects.BitlyLandingPage;

public class PageObjectManager {

	private WebDriver driver;
	private HomePage homePage;
    private BitlyLandingPage bitlyLandingPage;

	public PageObjectManager(WebDriver driver) {
		this.driver = driver;
	}

	public HomePage getHomePage(){
		return (homePage == null) ? homePage = new HomePage(driver) : homePage;
	}
	
	public BitlyLandingPage getBitlyLandingPage() {
		return (bitlyLandingPage == null) ? bitlyLandingPage = new BitlyLandingPage(driver) : bitlyLandingPage ;
	}
}
