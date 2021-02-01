package stepDefinations;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import dataProvider.ConfigFileReader;
import pageObjects.BitlyLandingPage;
import pageObjects.HomePage;
import managers.PageObjectManager;
public class ShortenYourLink {

	WebDriver driver;
	WebDriverWait wait;
	HomePage homePage;
	BitlyLandingPage bitlyLandingPage;
	PageObjectManager pageObjectManager;
	ConfigFileReader configFileReader;
	public String shortenLink1;
	public String shortenLink2;
	

	@SuppressWarnings("deprecation")
	@Given("^User go to bitly.com URL$")
	public void user_go_to_bitly_com_url() {
		configFileReader= new ConfigFileReader();
		System.setProperty("webdriver.chrome.driver",configFileReader.getDriverPath());
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		pageObjectManager = new PageObjectManager(driver);
		bitlyLandingPage = pageObjectManager.getBitlyLandingPage();
		bitlyLandingPage.navigateTo_BitlyLandingPage(); 
	}

	@When("^User is in the page$")
	public void user_is_in_the_page() {
		homePage = pageObjectManager.getHomePage();
		homePage.logIn();
		homePage.adWindowCloseBtn();
	}

	@Then("^User enter the actual URL in Shorten your link box$")
	public void user_enter_the_actuak_URL_in_Shorten_your_link_box() {
		homePage.shortenFeatureInput();
	}

	@And("^click Shorten button$")
	public void click_shorten_button() {
		homePage.shortenBtnClick();
	}

	@Then("^User can see the shorten link created$")
	public void user_can_see_the_shorten_link_created() {
		String shortenLink = homePage.shortenUrl();
		System.out.print(shortenLink);
	}

	@And("^validate the link created$")
	public void validate_the_link_created() throws InterruptedException {
		String windowHandle = driver.getWindowHandle();
		homePage.shortenFeatureValidation();
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		System.out.println(tabs.size());
		driver.switchTo().window(tabs.get(1));
		String actualTitle = driver.getCurrentUrl();
		System.out.println(actualTitle);
		String expectedTitle = configFileReader.getActualUrl();
		Assert.assertEquals(actualTitle, expectedTitle);
		driver.quit();
	}

	@Then("^User enter the same actual URL in Shorten your link box$") 
	public void user_enter_the_same_URL_created_different_shorten_Link() {
		homePage.shortenFeatureInput();
		homePage.shortenBtnClick();
		shortenLink2 = homePage.getShortenLinkBoxText();
		System.out.println(shortenLink2);
		homePage.refreshBrowser();
		homePage.shortenFeatureInput();
	}

	@SuppressWarnings("deprecation")
	@Then("^User can see the second shorten link created$")
	public void user_can_see_the_second_shorten_link_created() {
		shortenLink1 = homePage.shortenUrl();
		System.out.print(shortenLink1);
		
	}

	@And("^validate the link created should not equal to previous created link$")
	public void validate_link_created_are_not_same_as_previous_created_link( ) {
		System.out.println(shortenLink1);
		System.out.println(shortenLink2);
		Assert.assertNotEquals(shortenLink1, shortenLink2);
		driver.quit();
	}


	@Then("^User enter the invalid URL in Shorten your link box$")
	public void user_enter_the_invalid_URL_shorten_box() {
		homePage.shortenFeatureInvalidInput();
	}

	@Then("^Validate the user can see the invalid Url error message$")
	public void user_can_see_invalid_Url_error_message() {
		String errorMessage = homePage.getInvalidUrlErrorMessage();
		System.out.println(errorMessage);
		String expectedMessage = "Unable to shorten that link. It is not a valid url."; 
		Assert.assertEquals(errorMessage, expectedMessage);
		driver.quit();

	}
}
