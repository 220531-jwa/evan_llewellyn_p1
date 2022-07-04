package dev.llewellyn.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dev.llewellyn.pages.HomePage;
import dev.llewellyn.pages.LoginPage;
import dev.llewellyn.runners.LoginRunner;
import dev.llewellyn.runners.LogoutRunner;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LogoutFeatureStepImpl {
	
	private static WebDriver driver = LoginRunner.driver;
	private static LoginPage loginPage = LoginRunner.loginPage;
	private static HomePage homePage = LogoutRunner.homePage;
	
	@BeforeAll
	public static void setup() {
		File chrome = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", chrome.getAbsolutePath());
		driver = new ChromeDriver();

		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
	}

	@Given("a user is already logged in and on the home page")
	public void a_user_is_already_logged_in_and_on_the_home_page() {
		driver.get("http://localhost:8080/login.html");
		loginPage.emailInput.sendKeys("el@gmail.com");
		loginPage.passwordInput.sendKeys("word");
		loginPage.loginButton.click();
	}

	@When("a user clicks the logout button")
	public void a_user_clicks_the_logout_button() {
		new WebDriverWait(driver, Duration.ofSeconds(10))
			.until(ExpectedConditions.elementToBeClickable(homePage.logoutButton));
	    
		homePage.logoutButton.click();
	}

	@Then("the user should be on the login page")
	public void the_user_should_be_on_the_login_page() {
	    assertEquals("ERS Login", driver.getTitle());
	}
	
	@AfterAll
	public static void teardown() {
		driver.quit();
	}

}
