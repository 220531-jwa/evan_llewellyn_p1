package dev.llewellyn.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dev.llewellyn.pages.LoginPage;
import dev.llewellyn.runners.LoginRunner;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginFeatureStepImpl {

	private static WebDriver driver = LoginRunner.driver;
	private static LoginPage loginPage = LoginRunner.loginPage;
	
	@BeforeAll
	public static void setup() {
		File chrome = new File("src/test/resources/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", chrome.getAbsolutePath());
		driver = new ChromeDriver();

		loginPage = new LoginPage(driver);
	}

	@Given("a user is on the login page")
	public void a_user_is_on_the_login_page() {
		driver.get("http://localhost:8080/login.html");
	}

	@When("a user types in their {string} and {string} and clicks the login button")
	public void the_user_types_in_their_email_and_password_and_clicks_the_login_button(String email, String password) {
		loginPage.emailInput.sendKeys(email);
		loginPage.passwordInput.sendKeys(password);
		loginPage.loginButton.click();
	}

	@Then("the user should be on the home page")
	public void the_user_should_be_on_the_home_page() {
		new WebDriverWait(driver, Duration.ofSeconds(10))
			.until(ExpectedConditions.titleContains("Home Page"));
		
		assertEquals("Home Page", driver.getTitle());
	}
	
	@AfterAll
	public static void teardown() {
		driver.quit();
	}
}
